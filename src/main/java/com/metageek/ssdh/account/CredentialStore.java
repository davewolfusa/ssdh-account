package com.metageek.ssdh.account;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.Synchronized;

public class CredentialStore {
	
	private static CredentialStore instance = null;
	final private Map<String, String> store;
	
	@Synchronized 
	public static CredentialStore instance() {
		if (instance == null) {
			instance = new CredentialStore();
		} 
		return instance;
	}
	
	private CredentialStore() {
		this.store = Collections.synchronizedMap(new HashMap<String, String>(1000));
	}
	
	public void add(String username, String passwordHash) {
		store.put(username, passwordHash);
	}
	
	public String get(String username) {
		String passwordHash = null;
		passwordHash = store.get(username);
		return passwordHash;
	}
	
	public String remove(String username) {
		String passwordHash = null;
		passwordHash = store.remove(username);
		return passwordHash;
	}
}
