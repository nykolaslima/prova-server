package br.com.provaServer.infrastructure.validation.util;


public class ValidationUtil {
	public static boolean notEmpty(String text) {
		return text != null && text.trim().length() > 0;
	}
}