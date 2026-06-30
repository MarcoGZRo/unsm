package com.aurgroup.licoreria.Models.ValueObjects.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * NameAccount
 */
@Embeddable
public class NameAccount {

	@Column(name = "name_account")
	
	private String nameAccount;

	protected NameAccount() {}

	public NameAccount(String name){

		if(name == null) {
			 throw new IllegalArgumentException("Name no puede ser null");
		}
		this.nameAccount = name;
	}

	public String getNameAccount() {
		return nameAccount;
	}

}
