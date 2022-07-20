package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDao;
import edu.poly.domain.ChangePasswordForm;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePassword")
public class ChangePasswordServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request);//Lấy thông tin username của user đang đăng nhập
		//nếu rỗng là chưa đăng nhập
		if(username == null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}
		
		request.setAttribute("username", username);
		
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = SessionUtils.getLoginedUsername(request);//Lấy thông tin của username đang được đăng nhập
			
			ChangePasswordForm form = new ChangePasswordForm();
			BeanUtils.populate(form, request.getParameterMap());//đổ thông tin từ form vào đối tượng form
			
			request.setAttribute("username", username);//thiết lập lại thuộc tính username
			
			//Nếu trường confirmPassword và password không trùng nhau
			if(!form.getConfirmPassword().equals(form.getPassword())) {
				request.setAttribute("error", "New Password and new confirm password are not identical");
			}else {
				UserDao dao = new UserDao();
				dao.changePassword(form.getUsername(), form.getCurrentPassword(), form.getPassword() );
				request.setAttribute("message", "Password has been changed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: "+ e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHANGE_PASSWORD_PAGE);
	}

}
