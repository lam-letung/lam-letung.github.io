package edu.poly.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
//	Phương thức get lấy thông tin cookie
	public static String get(String name, HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		
//		Nếu tìm thấy cookies
		if(cookies != null) {
			for (Cookie cookie : cookies) {
//				nếu cookie trùng với tên truyền vào thì sẽ trả về giá trị cookie 
				if(cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
//	Phương thức add để lưu cookie
	public static Cookie add(String name, String value, int hours, HttpServletResponse response) {
		
		Cookie cookie = new Cookie(name, value);//		tạo ra đối tượng cookie
		cookie.setMaxAge(60 * 60 * hours); //Thiết lập thời gian tồn tại cho cookie 60s * 60p * hours
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return cookie; //trả lại giá trị cookie
	}
}
