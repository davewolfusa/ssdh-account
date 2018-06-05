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
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.metageek.ssdh.account.CredentialStore;
import com.metageek.ssdh.account.model.Credential;
import com.metageek.ssdh.account.util.MapperUtil;
import com.metageek.ssdh.account.util.PasswordUtil;
import com.metageek.ssdh.account.util.ViolationUtil;


@Path("/authentication")
public class AuthenticationResource {
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator;
    
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response login(String jsonRequest) {
		
		validator = factory.getValidator();
		Map<String, String> response = new HashMap<>();
		int httpStatus;

		// Deserialize JSON into Credential Class
		Credential credential = null;
		try {
			credential = MapperUtil.readAsObjectOf(Credential.class, jsonRequest);
		} catch (IOException e) {
			response.put("result","FAILURE - Unable to deserialize credential JSON object.");
			httpStatus = 500;
			System.out.println("Failure message: " + e.getMessage());
		}
		
		// Validate Credential
		Set<ConstraintViolation<Credential>> credentialConstraintViolations = validator.validate( credential );
		ArrayList<String> violationTextList = new ArrayList<>();
		if (!credentialConstraintViolations.isEmpty()) {
			violationTextList.addAll(ViolationUtil.getViolationInfo(Credential.class, credentialConstraintViolations));
			
			response.put("result", "FAILURE");
			for (String violationText : violationTextList) {
				response.put("validation error", violationText);
			}
			httpStatus = 500;
		} else {
			String passwordHash = CredentialStore.instance().get(credential.getUserName());
			if (passwordHash != null) {
				// Verify against stored password hash.
    			boolean result = 
    				PasswordUtil.verify(passwordHash, credential.getPassword().toCharArray(), UTF_8);
    			if (result) {
        			response.put("result", "AUTHENTICATED");
        			httpStatus = 200;
    			} else {
        			response.put("result", "AUTHENTICATION FAILURE");
        			httpStatus = 200;
    			}
			} else {
    			response.put("result", "AUTHENTICATION FAILURE");
    			httpStatus = 200;
			}
		}
		
        return Response.status(httpStatus).entity(response).build();
	}
    
	/* Not Yet Implemented
	@DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response logout(String username) {
	}
	 */

}
