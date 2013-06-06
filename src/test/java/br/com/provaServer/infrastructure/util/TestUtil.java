package br.com.provaServer.infrastructure.util;

import java.util.ResourceBundle;

import br.com.caelum.vraptor.core.SafeResourceBundle;

public class TestUtil {
	public static ResourceBundle bundle;
	
	static {
		TestUtil.bundle = new SafeResourceBundle(ResourceBundle.getBundle("messages"), true);
	}
	
	public static String i18n(String key) {
		return TestUtil.bundle.getString(key);
	}
}
