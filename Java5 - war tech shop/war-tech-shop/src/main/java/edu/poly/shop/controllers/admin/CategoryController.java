 package edu.poly.shop.controllers.admin;

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
import edu.poly.shop.domain.Category;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.services.CategoryService;
import lombok.val;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;// khai báo đối tượng thể hiện của ctgrSv

	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDto());
		return "admin/categories/addOrEdit";
	}

	@GetMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {

		Optional<Category> opt = categoryService.findById(categoryId);
		CategoryDto dto = new CategoryDto();
		// Nếu có tồn tại ctg
		if (opt.isPresent()) {
			Category entity = opt.get(); // Layas thông tin của ctg

			BeanUtils.copyProperties(entity, dto);// copy entity sang cho dto
			dto.setIsEdit(true);

			model.addAttribute("category", dto);
			return new ModelAndView("admin/categories/addOrEdit", model);
		}

		model.addAttribute("message", "Category is not existed");

		return new ModelAndView("forward:/admin/categories/searchpaginated", model);

	}

	@GetMapping("delete/{categoryId}")
	public ModelAndView delete(ModelMap model, @PathVariable("categoryId") Long categoryId) {

		categoryService.deleteById(categoryId);

		model.addAttribute("message", "Category is deleted");

		return new ModelAndView("forward:/admin/categories/searchpaginated", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/categories/addOrEdit");
		}
		// Gọi cate trong pack domain
		Category entity = new Category();
//		Copy dto sang entity
		BeanUtils.copyProperties(dto, entity);
		categoryService.save(entity);

		model.addAttribute("message", "Category is saved!");

		return new ModelAndView("forward:/admin/categories/searchpaginated", model);
	}

	@RequestMapping("")
	public String list(ModelMap model) {
//		Trả về tất cả danh sách ctg
		List<Category> list = categoryService.findAll();

		model.addAttribute("categories", list);

		return "admin/categories/list";
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		List<Category> list = null;

		// Nếu name có nội dung
		if (StringUtils.hasText(name)) {
			list = categoryService.findByNameContaining(name);
		} else {
			list = categoryService.findAll();
		}

		model.addAttribute("categories", list);

		return "admin/categories/search";
	}

	@GetMapping("searchpaginated")
	public String search(ModelMap model, @RequestParam(name = "nameSrc", required = false) String nameSrc,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		
//		Người dùng không nhập giá trị thì giá trị mặc định là 1
		int currentPage = page.orElse(1);
//		Giá trị ngầm định 5 item trên 1 trang
		int pageSize = size.orElse(5);
		
		
		Pageable pageable = PageRequest.of(currentPage - 1,pageSize, Sort.by("name"));
		Page<Category> resultPage = null;
		
		
		// Nếu name có nội dung
		if (StringUtils.hasText(nameSrc)) { 
			resultPage = categoryService.findByNameContaining( nameSrc , pageable);
			model.addAttribute("nameSrc",nameSrc);
		} else {
			resultPage = categoryService.findAll(pageable);
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
		

		model.addAttribute("categoryPage", resultPage);

		return "admin/categories/searchpaginated";
	}
	
	@PostMapping("searchpaginated")
	public String searchPost(ModelMap model, @RequestParam(name = "nameSrc", required = false) String nameSrc,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		
//		Người dùng không nhập giá trị thì giá trị mặc định là 1
		int currentPage = page.orElse(1);
//		Giá trị ngầm định 5 item trên 1 trang
		int pageSize = size.orElse(5);
		
		
		Pageable pageable = PageRequest.of(currentPage - 1,pageSize, Sort.by("name"));
		Page<Category> resultPage = null;
		
		
		// Nếu name có nội dung
		if (StringUtils.hasText(nameSrc)) {
			resultPage = categoryService.findByNameContaining(nameSrc , pageable);
			model.addAttribute("nameSrc",nameSrc);
		} else {
			resultPage = categoryService.findAll(pageable);
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
		

		model.addAttribute("categoryPage", resultPage);

		return "admin/categories/searchpaginated";
	}

}
