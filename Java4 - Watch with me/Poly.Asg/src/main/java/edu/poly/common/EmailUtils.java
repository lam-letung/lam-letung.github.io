package edu.poly.common;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.poly.domain.Email;

public class EmailUtils {
	public static void send(Email email) throws Exception {
		Properties prop = new Properties();
		/*
		 * Cấu hình thông tin mail
		 */
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth" , "true");
		prop.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(prop , new Authenticator() {
		protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(email.getFrom(), email.getFromPassword());
		}
		
		});
		
		try {
			Message message = new MimeMessage(session);//tạo ra đối tượng message từ session
			
			message.setFrom(new InternetAddress(email.getFrom()));//thiết lập địa chỉ mail gửi đi
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));//thiết lập danh sách các người nhận
			message.setSubject(email.getSubject());//thiết lập tiêu đề mail
			message.setContent(email.getContent(), "text/html; charset=utf-8");//thiết lập nội dung mail
			
			Transport.send(message);//thiết lập pthuc send để gửi mail đi
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
