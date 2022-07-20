package edu.poly.shop.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.services.AccountService;
import groovy.lang.Binding;

@Controller
public class AdminLoginController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HttpSession session;
	@Autowired
	SessionFactory factory;
	
	
	@GetMapping("alogin")
	public String login(ModelMap model) {
		model.addAttribute("account" , new AdminLoginDto());
		return "admin/accounts/login";
	}
	
	@PostMapping("alogin")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") AdminLoginDto dto, BindingResult result
			 ){
		
//		Kiểm tra xem có lỗi xảy ra k
		if(result.hasErrors()) {
			return new ModelAndView("/admin/accounts/login", model);
		}	
		
//		Gọi login để trả về dtg account
		Account account = accountService.login(dto.getUsername(), dto.getPassword());
//		Nếu account rỗng thì tbao
		if(account == null) {
			model.addAttribute("message","Invalid username or password");
			return new ModelAndView("/admin/accounts/login",model);
		}
		session.setAttribute("username", account.getUsername());
		
//		Truyền redirect-uri trong session vào ruri
		Object ruri= session.getAttribute("redirect-uri");
//		Nếu trong ruri tồn tại thì truyền tới uri đó 
		if(ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
		
//		Ngược lại thiết lập thuộc tính username của session
		if(account.getAdmin() == true) {
			dto.setLogin(true);
			return new ModelAndView("/admin/fragments/indexAdmin",model);
		}
		dto.setLogin(true);
		return new ModelAndView("/site/fragments/indexSite",model);
		
	}
	
	
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/alogin";
	}
	
}
