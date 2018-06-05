package com.metageek.ssdh.account.util;

import com.metageek.ssdh.account.model.Account;
import com.metageek.ssdh.account.model.Credential;
import com.metageek.ssdh.account.model.Profile;

public class BuildTestObjects {
	
	static public Credential buildTestCredential() {
		Credential credential = new Credential();
		credential.setUserName("davewolfusa");
		credential.setPassword("LXfd6Ji7YCZHTW.pv/xk");
		
		return credential;
	}
	
	static public Profile buildTestProfile() {
		Profile profile = new Profile();
		profile.setFirstName("Dave");
		profile.setLastName("Wolf");
		profile.setEmailAddress("dave.wolf@gmail.com");
		
		return profile;
	}
	
	static public Account buildTestAccount() {
		Account account = new Account();
		account.setCredential(buildTestCredential());
		account.setProfile(buildTestProfile());
		return account;
	}

}
