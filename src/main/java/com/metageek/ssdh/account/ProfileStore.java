package com.metageek.ssdh.account;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.metageek.ssdh.account.model.Profile;

import lombok.Synchronized;

public class ProfileStore {
	
	private static ProfileStore instance = null;
	final private Map<String, Profile> store;
	
	@Synchronized 
	public static ProfileStore instance() {
		if (instance == null) {
			instance = new ProfileStore();
		} 
		return instance;
	}
	
	private ProfileStore() {
		this.store = Collections.synchronizedMap(new HashMap<String, Profile>(1000));
	}
	
	public void add(String username, Profile account) {
		store.put(username, account);
	}
	
	public Profile get(String username) {
		Profile account = null;
		account = store.get(username);
		return account;
	}
	
	public Profile remove(String username) {
		Profile account = null;
		account = store.remove(username);
		return account;
	}
}
