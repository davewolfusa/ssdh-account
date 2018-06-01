package com.metageek.ssdh.account.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class UserAccount {
	
	private static final String PERSON_NAME_REGEX = "[A-z0-9\\s\\.\\-\\']+";
	
	@NotNull @Pattern(regexp = PERSON_NAME_REGEX) @Size(min = 2, max = 60)
	private String firstName;
	@NotNull @Pattern(regexp = PERSON_NAME_REGEX) @Size(min = 2, max = 60)
	private String lastName;
	@NotNull @Email
	private String emailAddress;
	@NotNull
	private UserCredential userCredential;

}
