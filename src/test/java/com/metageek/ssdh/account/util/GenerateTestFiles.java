package com.metageek.ssdh.account.util;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metageek.ssdh.account.model.Account;

public class GenerateTestFiles {
	
	@Test
	public void geneateTestFile1() {
		String testOutputDirectory = System.getProperty("testOutputDirectory");
		if (testOutputDirectory == null) {
			testOutputDirectory = "src/test/resources/";
		}
		String testFile1Path = testOutputDirectory + "testAccount1.json";
		Account bean1 = BuildTestObjects.buildTestAccount();

		ObjectMapper mapper = new ObjectMapper();

	    try {  
	        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(testFile1Path), bean1);
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
}
