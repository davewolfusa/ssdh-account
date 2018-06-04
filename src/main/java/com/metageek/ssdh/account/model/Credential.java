package com.metageek.ssdh.account.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class Credential {
	public static final String USERNAME_REGEX = "[A-z0-9\\s\\.\\-\\']+";
	public static final String PASSWORD_REGEX = 
		"(?=(?:.*[a-z]){2})(?=(?:.*[A-Z]){2})(?=(?:.*\\d){2})" + 
	    "(?=(?:.*[\\.\\,\\;\\:!=@#$%^&*\\-\\_\\+\\(\\)\\\\/\\[\\]\\{\\}\\<\\>]){2}).{16,128}";
	
	@NotNull @Pattern(regexp = USERNAME_REGEX) @Size(min = 2, max = 60)
	private String userName;
	@NotNull @Pattern(regexp = PASSWORD_REGEX)
	private String password;
}
