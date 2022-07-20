package edu.poly.shop.inerceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor{

	@Autowired
	private HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("pre handle of request" + request.getRequestURI());
//		Nếu username có tồn tại thì trả về true
		if(session.getAttribute("username") != null) {
			return true ;

		}
//		Ngược lại thì thiết lập biến redirect-uri  ở trong session
		session.setAttribute("redirect-uri", request.getRequestURI());//redirect-uri: Người dùng đang truy cập đến uri nào 
//		Yếu cầu login
		response.sendRedirect("/alogin");
//		trả về false
		return false;
	}

}
