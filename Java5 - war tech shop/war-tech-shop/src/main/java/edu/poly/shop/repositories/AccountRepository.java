package edu.poly.shop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Product;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{


	List<Account> findByUsername(String name);

	Page<Account> findByUsername(String name, Pageable pageable);
	

	

}
