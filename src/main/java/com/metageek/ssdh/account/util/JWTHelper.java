package com.metageek.ssdh.account.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

import lombok.Synchronized;

public class JWTHelper {

	private static JWTHelper instance = null;
	private Key key = null;
	
	@Synchronized
	public static JWTHelper instance() {
		if (instance == null) {
			instance = new JWTHelper();
		}
		return instance;
	}
	
	private JWTHelper() {
		// Shortcut instead of generating a key and loading via configuration.
		this.key = MacProvider.generateKey();
	}
	
	public String generateJWS(String subject) {
        return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, key).compact();
    }
	
	public boolean verifyJWS(String subject, String jws) {
		boolean result = false;
		String jwsSubject = null;
		
		try {
			jwsSubject = Jwts.parser().setSigningKey(key)
				                      .parseClaimsJws(jws)
				                      .getBody().getSubject();
			result = subject.equals(jwsSubject);
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return result;
	}
		
}