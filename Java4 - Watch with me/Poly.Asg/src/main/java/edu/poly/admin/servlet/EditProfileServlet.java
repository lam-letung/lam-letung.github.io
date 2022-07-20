package edu.poly.admin.servlet;

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
import edu.poly.model.User;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfile")
public class EditProfileServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request); //đọc thông tin username mà người dùng đã đăng nhập
		
//		Nếu username không tồn tại thì chuyển hướng đến trang login
		if(username == null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}
		
//		Ngược lại
		try {
			UserDao dao = new UserDao();
			User user = dao.findById(username);//Lấy và tìm kiếm theo username và trả về đối tượng user
			
			request.setAttribute("user", user);//Thiết lập thuộc tính user , hiển thị trên form cho người dùng chỉnh sửa
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);//Hiển thị trang edit
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User();//tạo đối tượng user
			BeanUtils.populate(user, request.getParameterMap());//đổ thông tin lấy từ form đổ vào user
			
			String username = SessionUtils.getLoginedUsername(request);//Lấy thông tin username đã được đăng nhập hiện tại
			UserDao dao = new UserDao();
			User oldUser = dao.findById(username);//Tìm kiếm username ở trong csdl hiện tại để trả về user
			
			user.setAdmin(oldUser.getAdmin());//Trường admin không thể chỉnh sửa
			dao.update(user);//cập nhật thông tin của user vào csdl
			request.setAttribute("message", "User profile updated!!!");// hiển thị thông báo cho người dùng cập nhật thành công
			
			request.setAttribute("user", user);//Thiết lập trở lại thông tin của user
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);//Hiển thị trang edit
	}

}
