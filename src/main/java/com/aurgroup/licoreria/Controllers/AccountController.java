package com.aurgroup.licoreria.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurgroup.licoreria.Models.Entities.Account;
import com.aurgroup.licoreria.Repositories.AccountRepository;

/**
 * AccountController
 */

@RestController
@RequestMapping("/api/account")
public class AccountController {
	@Autowired
	private AccountRepository accountRepository;

	@GetMapping
	public List<Account> getAllAccounts(){
		return accountRepository.findAll();
	}	
	
}
