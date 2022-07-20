package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.UploadUtils;
import edu.poly.dao.VideoDao;
import edu.poly.model.Video;

/**
 * Servlet implementation class VideosManagementServlet
 */
@WebServlet({"/Admin/VideosManagement", "/Admin/VideosManagement/create" 
	,"/Admin/VideosManagement/update","/Admin/VideosManagement/delete","/Admin/VideosManagement/reset", "/Admin/VideosManagement/edit" })

@MultipartConfig //Dành cho việc upload file
public class VideosManagementServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url  = request.getRequestURL().toString();
		if(url.contains("edit")) {
			edit(request,response);
			return;
		}
		if(url.contains("delete")) {
			delete(request,response);
			return;
		}
		if(url.contains("reset")) {
			reset(request,response);
			return;
		}
		Video video = new Video();
		video.setPoster("images/desktop.png");//thiết lập poster
		findAll(request, response);
		request.setAttribute("video", video);//thiết lập thông tin video
		
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url  = request.getRequestURL().toString();
		
		if(url.contains("create")) {
			create(request,response);
			return;
		}
		
		if(url.contains("delete")) {
			create(request,response);
			return;
		}
		if(url.contains("update")) {
			update(request,response);
			return;
		}
		if(url.contains("reset")) {
			reset(request,response);
			return;
		}
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("videoId");//lấy videoId do người dùng gửi tới
		
		//Nếu id == null
		if(id == null) {
			request.setAttribute("error", "Video id is required!");//Thông báo lỗi 
			PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//nạp view trả về phía người dùng
			return;
		}
		
		try {
			VideoDao dao = new VideoDao();
			Video video = dao.findById(id);
			
			if(video == null) {
				request.setAttribute("error", "Video id is not found!");//Thông báo lỗi 
				PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//nạp view trả về phía người dùng
				return;
			}
			
			dao.delete(id);
			request.setAttribute("message", "Video is deleted!");
			request.setAttribute("video", new Video());//thiết lập thông tin video thành rỗng
			
			findAll(request, response);//Hiển thị tất cả các video hiện có trong csdl
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: "+ e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//nạp view và view sẽ sử dụng thông tin video để hiển thị lên form
			
		}

	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		
		
		try {
			BeanUtils.populate(video, request.getParameterMap());//gọi bean utils để đổ dữ liệu từ form vào đối tượng video
			VideoDao dao = new VideoDao();
			Video oldVideo = dao.findById(video.getVideoId()); //tạo ra oldVideo = tìm kiếm thông tin video cũ theo id4
			
			/*
			 *Nếu người dùng không thay đổi hình ảnh thì sẽ lấy lại hình ảnh cũ
			 * và ngược lại
			 */
			if(request.getPart("cover").getSize()==0) {
				video.setPoster(oldVideo.getPoster());
			}else {
				video.setPoster("uploads/"+ UploadUtils.processUploadField("cover", request, 
						"/uploads", video.getVideoId()));
			}
			
			dao.update(video);//cập nhật lại video
			
			request.setAttribute("video", video);//Hiển thị lại thông tin video
			request.setAttribute("message", "Video is updated!");
			findAll(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: "+ e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//Hiển thị lại view
	}
	
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			
			VideoDao dao = new VideoDao();
			
			List<Video> list = dao.findAll();
			
			request.setAttribute("videos", list);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: "+ e.getMessage());
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoId");//lấy videoId do người dùng gửi tới
		
		//Nếu id == null
		if(id == null) {
			request.setAttribute("error", "Video is required!");//Thông báo lỗi 
			PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//nạp view trả về phía người dùng
			return;
		}
		
		try {
			VideoDao dao = new VideoDao();
			Video video = dao.findById(id);
			
			request.setAttribute("video", video);//thiết lập thông tin video nhận được
			findAll(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: "+ e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//nạp view và view sẽ sử dụng thông tin video để hiển thị lên form
		
	}
	
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		video.setPoster("images/desktop.png");
		request.setAttribute("video", video);
		findAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//nạp view và view sẽ sử dụng thông tin video để hiển thị lên form
		
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();//Khai báo đối tượng video
		
		try {
		 BeanUtils.populate(video, request.getParameterMap());//Đổ dữ liệu từ trên form vào cho đối tượng video
		 
		 video.setPoster("uploads/"+ UploadUtils.processUploadField("cover", request,
				 "/uploads", video.getVideoId()));//Thiết lập thông tin lưu trữ của poster 
		 
		 VideoDao dao = new VideoDao();//tạo ra lớp đao
		 dao.insert(video);//Lưu thông tin video vào csdl
		 
		 request.setAttribute("video", video);
		 request.setAttribute("message", "Video inserted!!!");//hiển thị tbao cho mng biết là insert thành công
		 
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());//Hiển thị thông báo lỗi 
		}
		findAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);//Hiển thị view video management
		
	}

}
