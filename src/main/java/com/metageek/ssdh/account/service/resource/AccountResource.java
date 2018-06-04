package com.metageek.ssdh.account.service.resource;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.metageek.ssdh.account.ProfileStore;
import com.metageek.ssdh.account.CredentialStore;
import com.metageek.ssdh.account.model.Account;
import com.metageek.ssdh.account.model.Credential;
import com.metageek.ssdh.account.model.Profile;
import com.metageek.ssdh.account.util.MapperUtil;
import com.metageek.ssdh.account.util.PasswordUtil;
import com.metageek.ssdh.account.util.ViolationUtil;

@Path("/account")
public class AccountResource {
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator;
    
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response createAccount(String jsonRequest) {
		
		validator = factory.getValidator();
		Map<String, String> response = new HashMap<>();
		int httpStatus = 200;

		// Deserialize JSON into Account Class
		Account account = null;
		try {
			account = MapperUtil.readAsObjectOf(Account.class, jsonRequest);
		} catch (IOException e) {
			response.put("result","FAILURE - Unable to deserialize Account JSON object.");
			System.out.println("Failure message: " + e.getMessage());
		}
		
		// Get & Validate Credential
		Credential credential = account.getCredential();
		Set<ConstraintViolation<Credential>> credentialConstraintViolations = validator.validate( credential );
		ArrayList<String> violationTextList = new ArrayList<>();
		if (!credentialConstraintViolations.isEmpty()) {
			violationTextList.addAll(ViolationUtil.getViolationInfo(Credential.class, credentialConstraintViolations));
		}
		
		// Get & Validate Profile
		Profile profile = account.getProfile();
		Set<ConstraintViolation<Profile>> profileConstraintViolations = validator.validate( profile );
		if (!profileConstraintViolations.isEmpty()) {
			violationTextList.addAll(ViolationUtil.getViolationInfo(Profile.class, profileConstraintViolations));
		}
		
		if (credentialConstraintViolations.isEmpty() && profileConstraintViolations.isEmpty()) {
			this.createCredential(credential);
			this.createProfile(credential.getUserName(), profile);
			response.put("result", "SUCCESS");
			httpStatus = 200;
		} else {
			
			response.put("result", "FAILURE");
			for (String violationText : violationTextList) {
				response.put("validation error", violationText);
			}
			httpStatus = 500;
		}
		
        return Response.status(httpStatus).entity(response).build();
	}

	private void createCredential(Credential credential) {
		// Store Credential with hashed password
		CredentialStore credentialStore = CredentialStore.instance();
		
		// Create and store a secure hash of the password
		credentialStore.add(credential.getUserName(),
							PasswordUtil.hash(credential.getPassword().toCharArray(), UTF_8));
	}

	private void createProfile(String username, Profile profile) {
		ProfileStore profileStore = ProfileStore.instance();
		profileStore.add(username, profile);
	}

}
