package com.metageek.ssdh.account.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {
	private static ObjectMapper MAPPER = new ObjectMapper();

	public static <T> T readAsObjectOf(Class<T> clazz, String value) throws IOException, JsonMappingException {
		T resultValue = null;
		try {
			resultValue = MAPPER.readValue(value, clazz);
		} 
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			// LOGGER.error("{}, {}", e.getMessage(), e.fillInStackTrace());
		}
		return resultValue;
	}

}
