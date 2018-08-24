package org.santanu.santanubrains.whatflix.utility;

public class LanguageAbbrevation {

	public static String search(String language) {
		String abbrevation = null;
		switch (language.toLowerCase()) {
		case "english":
			abbrevation = "en";
			break;
		case "spanish":
			abbrevation = "es";
			break;
		default:
			abbrevation = "en";
		}
		return abbrevation;

	}
}
