package edu.poly.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;


public class UploadUtils {
	public static String processUploadField(String fieldName , HttpServletRequest request,
			String storedFolder, String storedFilename) throws IOException, ServletException {
		Part filePart = request.getPart(fieldName);//Lấy thông tin của trường được upload
		
		if(filePart == null || filePart.getSize() == 0) {
			return "";
		}
		
//		Nếu có dữ liệu upload thì kiểm trả
//		Nếu tham số khác null thì giữ nguyên giá trị còn không thì để giá trị ngầm định
//		lưu các file vào upload
		if(storedFolder == null) {
			storedFolder = "/uploads";
		}
		
//		nếu storedFilename rỗng
		if(storedFilename == null) {
			storedFilename = Path.of(filePart.getSubmittedFileName()).getFileName().toString();// thì lấy tên của file được upload tới sv
		}else {
			storedFilename +="."+ FilenameUtils.getExtension(Path.of(filePart.getSubmittedFileName()).toString());//Ngược lại thì lấy tên file + phần mở rộng được upload tới sv
		}
		
		String uploadFolder = request.getServletContext().getRealPath(storedFolder);//lấy thư mục thực tế của thư mục lưu trữ
		
		Path uploadPath = Paths.get(uploadFolder);//lấy đôí tượng path 
		/*
		//và ktra đối tượng path có tồn tại hại hay không 
		nếu không thì tạo ra thư mục
		*/
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		filePart.write(Paths.get(uploadPath.toString(), storedFilename).toString());//ghi nội dung ra thư mục uploadPath
		
		return storedFilename;
	}
}
