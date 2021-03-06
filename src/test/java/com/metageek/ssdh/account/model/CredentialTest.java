package com.metageek.ssdh.account.model;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class CredentialTest extends TestCase {
	private static Validator validator;
	
	@BeforeClass
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@Test
	public void test_type() throws Exception {
		// TODO auto-generated by JUnit Helper.
		assertNotNull(Credential.class);
	}

	@Test
    public void test_instantiation() throws Exception {
		// TODO auto-generated by JUnit Helper.
		Credential target = new Credential("", "");
		assertNotNull(target);
	}
	
	@Test
	public void test_validateNullPassword() throws Exception {
		// Instantiate Credential with null test data and validate
		// Expect failure
		Credential uc = new Credential(null, null);
		
		Set<ConstraintViolation<Credential>> constraintViolations = validator.validate( uc );

	    assertEquals( 2, constraintViolations.size() );
	    assertEquals("must not be null", constraintViolations.iterator().next().getMessage() );
	    assertEquals("must not be null", constraintViolations.iterator().next().getMessage() );
	}
	
	@Test
	public void test_validateSimplePassword() throws Exception {
		// Instantiate Credential with simple test data and validate
		// Expect failure
		Credential uc = new Credential("Bob Jones", "12345678");
		
		Set<ConstraintViolation<Credential>> constraintViolations = validator.validate( uc );

	    assertEquals(1, constraintViolations.size() );
	    assertThat(constraintViolations.iterator().next().getMessage(), containsString("must match")); 
	}
	
	@Test
	public void test_validateStrongPassword() throws Exception {
		// Instantiate Credential with simple test data and validate
		// Expect success
		Credential uc = new Credential("Bob Jones", "UCVJhUaz22V02MfCNN#a!*");
		
		Set<ConstraintViolation<Credential>> constraintViolations = validator.validate( uc );

	    assertEquals(0, constraintViolations.size());
	}
	
	@Test
	public void test_validateValidUserName() throws Exception {
		// Instantiate Credential with simple test data and validate
		// Expect success
		Credential uc = new Credential("bob.jones", "UCVJhUaz22V02MfCNN#a!*");
		
		Set<ConstraintViolation<Credential>> constraintViolations = validator.validate( uc );

	    assertEquals(0, constraintViolations.size());
	}
	
	@Test
	public void test_validateShortUserName() throws Exception {
		// Instantiate Credential with simple test data and validate
		// Expect failure
		Credential uc = new Credential("S", "UCVJhUaz22V02MfCNN#a!*");
		
		Set<ConstraintViolation<Credential>> constraintViolations = validator.validate( uc );

	    assertEquals(1, constraintViolations.size());
	    ConstraintViolation<Credential> violation = constraintViolations.iterator().next();
	    String fieldName = violation.getPropertyPath().toString();
	    assertThat(fieldName, containsString("userName"));
	    String message = violation.getMessage();
	    assertThat(message, containsString("size must be between")); 
	}
	
	@Test
	public void test_validateInvalidUserName() throws Exception {
		// Instantiate Credential with simple test data and validate
		// Expect failure
		Credential uc = new Credential("id = id", "UCVJhUaz22V02MfCNN#a!*");
		
		Set<ConstraintViolation<Credential>> constraintViolations = validator.validate( uc );

	    assertEquals(1, constraintViolations.size());
	    ConstraintViolation<Credential> violation = constraintViolations.iterator().next();
	    String fieldName = violation.getPropertyPath().toString();
	    assertThat(fieldName, containsString("userName"));
	    String message = violation.getMessage();
	    assertThat(message, containsString("must match")); 
	}
}
