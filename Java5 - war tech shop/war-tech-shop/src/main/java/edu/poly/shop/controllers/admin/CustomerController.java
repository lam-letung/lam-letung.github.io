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
import org.hibernate.SessionFactory;
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
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Category;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.services.CustomerService;
import edu.poly.shop.services.CategoryService;
import edu.poly.shop.services.CustomerService;
import edu.poly.shop.services.StorageService;
import lombok.val;
@Transactional
@Controller
@RequestMapping("admin/customers")
public class CustomerController {
	@Autowired
	SessionFactory factory;
	@Autowired
	CustomerService customerService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncode;
	@Autowired
	StorageService storageService;
		@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("isEdit", false);
		return "admin/customers/addOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("customer") Customer entity,
			@ModelAttribute("customerDto") CustomerDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("site/customers/addOrEdit");
		}
		
		// Gọi cate trong pack domain
//		Customer entity = new Customer();
//	Copy dto sang entity
		
		entity.setStatus(true);
		entity.getAccount().setAdmin(false);
		entity.getAccount().setStatus(true);
		entity.getAccount().setRegisterDate(new Date());
		entity.setRegisteredDate(new Date());
		entity.setEmail(entity.getAccount().getEmail());
		entity.getAccount().setPassword(bCryptPasswordEncode.encode(entity.getAccount().getPassword()));
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(entity.getAccount());
			Criteria cr = session.createCriteria(Account.class);
			cr.add(Restrictions.eq("username", entity.getAccount().getUsername()));
			Account tk = (Account)cr.uniqueResult();
			entity.setAccount(tk);
			session.save(entity);
			t.commit();
			model.addAttribute("message","Tạo thành công");
			return new  ModelAndView("forward:/admin/categories/searchpaginated",model);
		}catch (Exception e){
			t.rollback();
			System.out.println(e.getMessage());
			model.addAttribute("message", "Đăng ký tài khoản thất bại!");
			return new ModelAndView("admin/customers/addOrEdit", model);
		}finally {
			session.close();
		}
		}
	
	@PostMapping("edit")
	public ModelAndView edit(ModelMap model, @Valid @ModelAttribute("customer") Customer entity,
			@ModelAttribute("customerDto") CustomerDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("site/customers/addOrEdit");
		}
		
		// Gọi cate trong pack domain
//		Customer entity = new Customer();
//	Copy dto sang entity
		
		entity.setStatus(true);
		entity.getAccount().setAdmin(false);
		entity.getAccount().setStatus(true);
		entity.getAccount().setRegisterDate(new Date());
		entity.setRegisteredDate(new Date());
		entity.setEmail(entity.getAccount().getEmail());
		entity.getAccount().setPassword(bCryptPasswordEncode.encode(entity.getAccount().getPassword()));
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(entity.getAccount());
			Criteria cr = session.createCriteria(Account.class);
			cr.add(Restrictions.eq("username", entity.getAccount().getUsername()));
			Account tk = (Account)cr.uniqueResult();
			entity.setAccount(tk);
			session.save(entity);
			t.commit();
			model.addAttribute("message","Cập nhật thành công");
			return new  ModelAndView("forward:/admin/categories/searchpaginated",model);
		}catch (Exception e){
			t.rollback();
			System.out.println(e.getMessage());
			model.addAttribute("message", "Cập nhật tài khoản thất bại!");
			return new ModelAndView("admin/customers/edit", model);
		}finally {
			session.close();
		}
		}
		
	
	
	@RequestMapping("")
	public String list(ModelMap model) {
//		Trả về tất cả danh sách ctg
		List<Customer> list = customerService.findAll();

		model.addAttribute("customers", list);
		
		return "site/customers/list";
	}

	@GetMapping("edit/{customerId}")
	public ModelAndView edit(ModelMap model, @PathVariable("customerId") Long customerId) {

		Optional<Customer> opt = customerService.findById(customerId);
//		Customer entity = new Customer();
		// Nếu có tồn tại ctg
		if (opt.isPresent()) {
			Customer entity = opt.get(); // Layas thông tin của ctg

//			BeanUtils.copyProperties(entity, dto);// copy entity sang cho dto
			model.addAttribute("isEdit", true);
			
			entity.getAccount().setPassword("");

			model.addAttribute("customer", entity);
			return new ModelAndView("admin/customers/edit", model);
		}

		model.addAttribute("message", "Customer is not existed");

		return new ModelAndView("forward:/admin/customers/searchpaginated", model);

	}
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
		
	}
//
	@GetMapping("delete/{customerId}")
	public ModelAndView delete(ModelMap model, @PathVariable("customerId") Long customerId) {

		customerService.deleteById(customerId);

		model.addAttribute("message", "Username is deleted");

		return new ModelAndView("forward:/site/customers", model);
	}


//
	
//
//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
//		List<Category> list = null;
//
//		// Nếu name có nội dung
//		if (StringUtils.hasText(name)) {
//			list = customerService.findByNameContaining(name);
//		} else {
//			list = customerService.findAll();
//		}
//
//		model.addAttribute("customers", list);
//
//		return "site/customers/search";
//	}
//
	@RequestMapping("searchpaginated")
	public String search(ModelMap model, @RequestParam(name = "customerIdsrc", required = false) String customerIdsrc,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		
//		Người dùng không nhập giá trị thì giá trị mặc định là 1
		int currentPage = page.orElse(1);
//		Giá trị ngầm định 5 item trên 1 trang
		int pageSize = size.orElse(5);
		
		
		Pageable pageable = PageRequest.of(currentPage - 1,pageSize, Sort.by("customerId"));
		Page<Customer> resultPage = null;
		
		
		// Nếu name có nội dung
		if (StringUtils.hasText(customerIdsrc)) {
			resultPage = customerService.findByNameContaining(customerIdsrc , pageable);
			model.addAttribute("customerIdsrc",customerIdsrc);
		} else {
			resultPage = customerService.findAll(pageable);
		}
		int totalPages = resultPage.getTotalPages();
		if(totalPages > 0) {
			int start  = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if(totalPages > 5) {
				if(end == totalPages) start = end = 5;
				else if(start ==1 ) end = start + 5;
				
			}
			List<Integer> pageNumbers = IntStream.range(start, end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		

		model.addAttribute("customerPage", resultPage);

		return "admin/customers/searchpaginated";
	}

//	
	

}
