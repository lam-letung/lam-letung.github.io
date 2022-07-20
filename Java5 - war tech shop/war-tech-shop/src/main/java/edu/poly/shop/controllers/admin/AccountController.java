package edu.poly.shop.controllers.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Category;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.services.AccountService;
import edu.poly.shop.services.CategoryService;
import edu.poly.shop.services.CustomerService;
import edu.poly.shop.services.StorageService;
import lombok.val;

@Transactional
@Controller
@RequestMapping("admin/accounts")
public class AccountController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountService accountService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	StorageService storageService;

	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("account", new AccountDto());

		return "admin/accounts/addOrEdit";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") AccountDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/accounts/addOrEdit");
		}

		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		entity.setAdmin(true);
		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		entity.setRegisterDate(new Date());
		accountService.save(entity);

		model.addAttribute("message", "Account is saved!");

		return new ModelAndView("forward:/admin/accounts/searchpaginated", model);
//		if(!dto.getImageFile().isEmpty()) {
//			UUID uuid = UUID.randomUUID();
//			String uuString = uuid.toString();
//			
//			entity.setImage(storageService.getStoredFileName(dto.getImageFile(), uuString));
//			storageService.store(dto.getImageFile(), entity.getImage());
//		}
	}

	@RequestMapping("")
	public String list(ModelMap model) {
//		Trả về tất cả danh sách ctg
		List<Account> list = accountService.findAll();

		model.addAttribute("accounts", list);

		return "admin/accounts/list";
	}

	@GetMapping("edit/{username}")
	public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {

		Optional<Account> opt = accountService.findById(username);
		AccountDto dto = new AccountDto();
		// Nếu có tồn tại ctg
		if (opt.isPresent()) {
			Account entity = opt.get(); // Layas thông tin của ctg

			BeanUtils.copyProperties(entity, dto);// copy entity sang cho dto
			dto.setIsEdit(true);

			dto.setPassword("");

			model.addAttribute("account", dto);
			return new ModelAndView("admin/accounts/addOrEdit", model);
		}

		model.addAttribute("message", "Account is not existed");

		return new ModelAndView("forward:/admin/accounts", model);

	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);

	}
////
	@GetMapping("delete/{username}")
	public ModelAndView delete(ModelMap model, @PathVariable("username") String username) {

		accountService.deleteById(username);

		model.addAttribute("message", "Username is deleted");

		return new ModelAndView("forward:/admin/accounts/searchpaginated", model);
	}

////

//
//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
//		List<Category> list = null;
//
//		// Nếu name có nội dung
//		if (StringUtils.hasText(name)) {
//			list = accountService.findByNameContaining(name);
//		} else {
//			list = accountService.findAll();
//		}
//
//		model.addAttribute("accounts", list);
//
//		return "admin/accounts/search";
//	}
//
	@RequestMapping("searchpaginated")
	public String search(ModelMap model, @RequestParam(name = "namesrc", required = false) String namesrc,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

//		Người dùng không nhập giá trị thì giá trị mặc định là 1
		int currentPage = page.orElse(1);
//		Giá trị ngầm định 5 item trên 1 trang
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("username"));
		Page<Account> resultPage = null;

		// Nếu name có nội dung
		if (StringUtils.hasText(namesrc)) {
			resultPage = accountService.findByNameContaining(namesrc, pageable);
			model.addAttribute("namesrc", namesrc);
		} else {
			resultPage = accountService.findAll(pageable);
		}
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end = 5;
				else if (start == 1)
					end = start + 5;

			}
			List<Integer> pageNumbers = IntStream.range(start, end).boxed().collect(Collectors.toList());

			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("accountPage", resultPage);

		return "admin/accounts/searchpaginated";
	}
}
