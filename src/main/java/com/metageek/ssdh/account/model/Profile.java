package com.metageek.ssdh.account.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class Profile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String PERSON_NAME_REGEX = "[A-z0-9\\s\\.\\-\\']+";
	
	@NotNull @Pattern(regexp = PERSON_NAME_REGEX) @Size(min = 2, max = 60)
	private String firstName;
	@NotNull @Pattern(regexp = PERSON_NAME_REGEX) @Size(min = 2, max = 60)
	private String lastName;
	@NotNull @Email
	private String emailAddress;

}
