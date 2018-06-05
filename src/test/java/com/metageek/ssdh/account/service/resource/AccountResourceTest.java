package com.metageek.ssdh.account.service.resource;

import javax.ws.rs.core.Response;

import org.junit.Test;

import junit.framework.TestCase;

public class AccountResourceTest extends TestCase {

	@Test
	public void test_type() throws Exception {
		// TODO auto-generated by JUnit Helper.
		assertNotNull(AccountResource.class);
	}

	@Test
	public void test_instantiation() throws Exception {
		// TODO auto-generated by JUnit Helper.
		AccountResource target = new AccountResource();
		assertNotNull(target);
	}

	@Test
	public void test_createAccount() throws Exception {
		// TODO auto-generated by JUnit Helper.
		AccountResource target = new AccountResource();
		String jsonRequest = 
  "{ " +
  "\"credential\" : { \"userName\" : \"johndoe\", \"password\" : \"LXfd6Ji7YCZHTW.pv/xk\" }, " + 
  "\"profile\" : { \"firstName\" : \"John\", \"lastName\" : \"Doe\", \"emailAddress\" : \"john.doe@gmail.com\" }" + 
  "}";

		Response result = target.createAccount(jsonRequest);
		assertNotNull(result);
		assertEquals(200, result.getStatus());
		assertEquals("{result=SUCCESS}", result.getEntity().toString());
	}

}