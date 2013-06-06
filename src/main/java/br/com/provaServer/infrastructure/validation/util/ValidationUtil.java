package br.com.provaServer.infrastructure.validation.util;

import java.util.Collection;

public class ValidationUtil {
	public static boolean notEmpty(String text) {
		return text != null && text.trim().length() > 0;
	}
	
	public static boolean notEmpty(Collection<?> collection) {
		return collection != null && collection.size() > 0;
	}
}