package edu.poly.shop.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.model.RegisterDto;
import edu.poly.shop.services.AccountService;
import edu.poly.shop.services.CustomerService;
import groovy.lang.Binding;

@Transactional
@Controller
public class RegisterController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	SessionFactory factory;
	@GetMapping("register")
	public String login(ModelMap model) {
		model.addAttribute("customer" , new Customer());
		return "admin/accounts/register";
	}
	
	@PostMapping("register")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("customer") Customer entity, BindingResult result) {
//		Kiểm tra xem có lỗi xảy ra k
		
		
		if (result.hasErrors()) {
			return new ModelAndView("admin/accounts/register");
		}
		// Gọi cate trong pack domain
//		Customer entity = new Customer();
//		BeanUtils.copyProperties(dto, entity);
		entity.setStatus(true);
		entity.getAccount().setAdmin(false);
		entity.getAccount().setStatus(true);
		entity.getAccount().setRegisterDate(new Date());
		entity.setRegisteredDate(new Date());
		entity.setEmail(entity.getAccount().getEmail());
		entity.getAccount().setPassword(bCryptPasswordEncoder.encode(entity.getAccount().getPassword()));
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
			return new  ModelAndView("forward:/admin/index",model);
		}catch (Exception e){
			t.rollback();
			System.out.println(e.getMessage());
			model.addAttribute("message", "Đăng ký tài khoản thất bại!");
			return new ModelAndView("admin/accounts/register", model);
		}finally {
			session.close();
		}
		}
	}
	
	
//	@PostMapping("register")
//	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") acc dto, BindingResult result) {
////		Kiểm tra xem có lỗi xảy ra k
//		
//		
//		if (result.hasErrors()) {
//			return new ModelAndView("admin/accounts/register");
//		}
//		// Gọi cate trong pack domain
//		Customer entity = new Customer();
//	BeanUtils.copyProperties(dto, entity);
//			Optional<Customer> optExist = customerService.findById(dto.getUsername());
//			if(optExist.isEmpty() ) {
//				customerService.save(entity);
//				model.addAttribute("message","Tạo thành công");
//				return new  ModelAndView("forward:/index",model);
//			}else {
//			model.addAttribute("message","acc da ton tai");
//			return new ModelAndView("admin/accounts/register", model);
//		}
//	}
//}
//}
//}