package com.metageek.ssdh.account.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class ViolationUtil {
	
	public static <T> List<String> getViolationInfo(Class<T> T, Set<ConstraintViolation<T>> violationSet) {
		ArrayList<String> violationTextList = new ArrayList<>();
		for (ConstraintViolation<?> violation : violationSet) {
			String text = violation.getPropertyPath().toString() + " : " + violation.getMessage();
			violationTextList.add(text);
		}
		return violationTextList;
	}
	
	private ViolationUtil() {
	}

}
