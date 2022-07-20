package edu.poly.shop.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements Serializable{
	
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long accountId;
	@Id
	@Column(length = 30)
	private String username;
	@Column(length = 60 , nullable = false)
	private String password;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date registerDate;
	
	private Boolean admin;
	
	@Column(nullable = false)
	private Boolean status;
	@OneToMany(mappedBy="account",fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Customer> customers;
}
