package edu.poly.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	
//	Lưu thuộc tính ở trong đối tượng sesson
	public static void add(HttpServletRequest request, String name, Object value) {
		HttpSession session  = request.getSession();
		session.setAttribute(name, value);
	}
	
//	lấy thuộc tính được truyền vào trong đối tượng sesson
	public static Object get(HttpServletRequest request, String name) {
		HttpSession session  = request.getSession();
		
		return session.getAttribute(name);
	}
	
//	Huỷ bỏ sesson
	public static void invalidate(HttpServletRequest request) {
		HttpSession session  = request.getSession();
		session.removeAttribute("username");//HUỷ giá trị username ra khỏi sesson
		session.invalidate();
		
	}
	
//	Kiểm tra người dùng đã đăng nhập hay chưa
	public static boolean isLogin(HttpServletRequest request) {
		return get(request, "username") !=null;
	}
	
//	Trả về giá trị của username đã được đăng nhập vào trong hệ thống
	public static String getLoginedUsername(HttpServletRequest request) {
		Object username  = get(request, "username");
		return username == null? null : username.toString();
	}
}
