package edu.poly.shop.model;



import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto  implements Serializable{
	@NotEmpty
	@Length(min = 6)
	private String username;
	@NotEmpty
	@Length(min = 6)
	private String password;
	@Email
	private String email;
	private Date registerDate;
	private Boolean admin = true;
//	private String image;
//	private MultipartFile imageFile;
	private Boolean status = true;
//	private Date birthDay;
	
	private Boolean isEdit = false;
}
