package com.aurgroup.licoreria.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurgroup.licoreria.Models.Entities.Account;

/**
 * AccountRepository
 */
public interface AccountRepository extends JpaRepository<Account, Long>{

	
}
