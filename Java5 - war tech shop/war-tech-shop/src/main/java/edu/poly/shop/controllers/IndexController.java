package edu.poly.shop.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Category;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.services.AccountService;
import edu.poly.shop.services.CategoryService;
import lombok.val;

@Controller

public class IndexController {
	
	@Autowired
	AccountService accountService;
	

	@GetMapping("index")
	public String index(Model model) {
		
		return "index";
	}
	
	@PostMapping("index")
	public String indexPost(Model model) {
		
		return "index";
	}
	
//	@PostMapping("saveOrUpdate")
//	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") AccountDto dto,
//			BindingResult result) {
//		if (result.hasErrors()) {
//			return new ModelAndView("admin/accounts/addOrEdit");
//		}
//		// Gọi cate trong pack domain
//		Account entity = new Account();
////		Copy dto sang entity
//		BeanUtils.copyProperties(dto, entity);
//		accountService.save(entity);
//
//		model.addAttribute("message", "Account is saved!");
//
//		return new ModelAndView("forward:/admin/accounts", model);
//	}
//	
//	@RequestMapping("")
//	public String list(ModelMap model) {
////		Trả về tất cả danh sách ctg
//		List<Account> list = accountService.findAll();
//
//		model.addAttribute("accounts", list);
//
//		return "admin/accounts/list";
//	}
//
//	@GetMapping("edit/{username}")
//	public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {
//
//		Optional<Account> opt = accountService.findById(username);
//		AccountDto dto = new AccountDto();
//		// Nếu có tồn tại ctg
//		if (opt.isPresent()) {
//			Account entity = opt.get(); // Layas thông tin của ctg
//
//			BeanUtils.copyProperties(entity, dto);// copy entity sang cho dto
//			dto.setIsEdit(true);
//			
//			dto.setPassword("");
//
//			model.addAttribute("account", dto);
//			return new ModelAndView("admin/accounts/addOrEdit", model);
//		}
//
//		model.addAttribute("message", "Account is not existed");
//
//		return new ModelAndView("forward:/admin/accounts", model);
//
//	}
////
//	@GetMapping("delete/{username}")
//	public ModelAndView delete(ModelMap model, @PathVariable("username") String username) {
//
//		accountService.deleteById(username);
//
//		model.addAttribute("message", "Username is deleted");
//
//		return new ModelAndView("forward:/admin/accounts", model);
//	}


//
	
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
//	@GetMapping("searchpaginated")
//	public String search(ModelMap model, @RequestParam(name = "usernamesrc", required = false) String usernamesrc,
//			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
//		
////		Người dùng không nhập giá trị thì giá trị mặc định là 1
//		int currentPage = page.orElse(1);
////		Giá trị ngầm định 5 item trên 1 trang
//		int pageSize = size.orElse(5);
//		
//		
//		Pageable pageable = PageRequest.of(currentPage - 1,pageSize, Sort.by("username"));
//		Page<Account> resultPage = null;
//		
//		
//		// Nếu name có nội dung
//		if (StringUtils.hasText(usernamesrc)) {
//			resultPage = accountService.findByNameContaining(usernamesrc , pageable);
//			model.addAttribute("usernamesrc",usernamesrc);
//		} else {
//			resultPage = accountService.findAll(pageable);
//		}
//		int totalPages = resultPage.getTotalPages();
//		if(totalPages > 0) {
//			int start  = Math.max(1, currentPage - 2);
//			int end = Math.min(currentPage + 2, totalPages);
//			
//			if(totalPages > 5) {
//				if(end == totalPages) start = end = 5;
//				else if(start ==1 ) end = start + 5;
//				
//			}
//			List<Integer> pageNumbers = IntStream.range(start, end)
//					.boxed()
//					.collect(Collectors.toList());
//			
//			model.addAttribute("pageNumbers", pageNumbers);
//		}
//		
//
//		model.addAttribute("accountPage", resultPage);
//
//		return "admin/accounts/searchpaginated";
//	}
//
//	
//	@PostMapping("searchpaginated")
//	public String searchPost(ModelMap model, @RequestParam(name = "usernamesrc", required = false) String usernamesrc,
//			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
//		
////		Người dùng không nhập giá trị thì giá trị mặc định là 1
//		int currentPage = page.orElse(1);
////		Giá trị ngầm định 5 item trên 1 trang
//		int pageSize = size.orElse(5);
//		
//		
//		Pageable pageable = PageRequest.of(currentPage - 1,pageSize, Sort.by("username"));
//		Page<Account> resultPage = null;
//		
//		
//		// Nếu name có nội dung
//		if (StringUtils.hasText(usernamesrc)) {
//			resultPage = accountService.findByNameContaining(usernamesrc , pageable);
//			model.addAttribute("usernamesrc",usernamesrc);
//		} else {
//			resultPage = accountService.findAll(pageable);
//		}
//		int totalPages = resultPage.getTotalPages();
//		if(totalPages > 0) {
//			int start  = Math.max(1, currentPage - 2);
//			int end = Math.min(currentPage + 2, totalPages);
//			
//			if(totalPages > 5) {
//				if(end == totalPages) start = end = 5;
//				else if(start ==1 ) end = start + 5;
//				
//			}
//			List<Integer> pageNumbers = IntStream.range(start, end)
//					.boxed()
//					.collect(Collectors.toList());
//			
//			model.addAttribute("pageNumbers", pageNumbers);
//		}
//		
//
//		model.addAttribute("accountPage", resultPage);
//
//		return "admin/accounts/searchpaginated";
//	}

}
