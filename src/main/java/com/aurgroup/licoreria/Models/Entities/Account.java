package com.aurgroup.licoreria.Models.Entities;

import com.aurgroup.licoreria.Models.ValueObjects.Account.NameAccount;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Account
 */

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_account")
	private Long id;
	@Embedded
	private NameAccount name;

}
