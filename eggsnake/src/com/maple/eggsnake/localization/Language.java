/** 
 * @description	: this is used as a dictionary for localization
 * @author		: 黄攀
 * @created		: 2012-1-3
 */


package com.maple.eggsnake.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

	private static ResourceBundle instance;
	
	public static String translate(String text) {
		if(instance == null)
			instance = ResourceBundle.getBundle("language");
		return instance.getString(text);
	}
	
	public static void setLanguage(Locale locale){
		Locale.setDefault(locale);
	}
}
