package com.metageek.ssdh.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class Account {
	private Credential credential;
	private Profile profile;

}
