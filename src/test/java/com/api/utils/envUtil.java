package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class envUtil {

	
	public static Dotenv dotenv;
	
	static {
		dotenv=Dotenv.load();
		
	}
	private envUtil() {
		
	}
	
	public static String getvalue(String varname) {
		return dotenv.get(varname);
	}
	
}
