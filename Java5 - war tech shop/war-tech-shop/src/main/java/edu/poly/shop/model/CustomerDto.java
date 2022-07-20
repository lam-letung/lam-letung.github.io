package edu.poly.shop.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import edu.poly.shop.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable{
	
	
	
//	private Long customerId;
//	@NotEmpty
//	private String username;
	
//	@NotEmpty
//	private String password;
//	@NotEmpty
//	private String name;
//	
//	@Length(min = 10)
//	private String phone;
//	private String address;
//	@NotEmpty
//	@Email
//	private String email;
//	private Boolean status;
//	private Boolean admin = false;
	private Boolean isEdit = false;
}
