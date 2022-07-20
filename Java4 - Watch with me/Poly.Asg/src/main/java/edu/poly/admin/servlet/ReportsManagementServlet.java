package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.FavoriteDao;
import edu.poly.dao.VideoDao;
import edu.poly.domain.FavoriteReport;
import edu.poly.domain.FavoriteUserReport;
import edu.poly.model.Video;

/**
 * Servlet implementation class ReportsManagementServlet
 */
@WebServlet("/ReportsManagementServlet")
public class ReportsManagementServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		reportFavoritesByVideos(request, response); 
		reportFavoriteUsersByVideo(request, response);
		
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void reportFavoritesByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String videoUserId = request.getParameter("videoUserId");//Lấy video mà người dùng lựa chọn trong cbb
			
			VideoDao vdao = new VideoDao();
			List<Video> vList = vdao.findAll();//lấy ra danh sách tất cả các video hiện có trong csdl hiển thị lên cbb
			/*Nếu videoUserId == null thì ng dùng chưa lựa chọn và danh sách >0 
			 * thì thiết lập giá trị ngầm định lấy mã của video đầu tiên gán cho videoUserId
			 */
			
			if(videoUserId == null && vList.size()>0) {
				videoUserId = vList.get(0).getVideoId();
			}
			
			FavoriteDao dao = new FavoriteDao();//tạo ra đối tượng favoritedao 
			List<FavoriteUserReport> list = dao.reportFavoriteUsersByVideos(videoUserId);//trả lại danh sách người dùng yêu thích video
			
			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vidList", vList);
			request.setAttribute("favUsers", list);
		} catch (Exception e) {
			request.setAttribute("error", "Error: "+ e.getMessage());
		}
	}
	
	protected void reportFavoriteUsersByVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			FavoriteDao dao = new FavoriteDao();
			List<FavoriteReport> list = dao.reportFavoritesByVideos();
			
			request.setAttribute("favList", list);
		} catch (Exception e) {
			request.setAttribute("error", "Error: "+ e.getMessage());
		}
	}

}
