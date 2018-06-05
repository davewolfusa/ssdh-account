package com.metageek.ssdh.account.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Credential credential;
	@NotNull
	private Profile profile;

}
