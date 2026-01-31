package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ConfigManager {
	private static Properties prop = new Properties();
	private static String env;
	static File file ;
	//Private constructor to restrict object creation outside class
	private ConfigManager() {
		
	}
	static {//executes only once
		//Static block will be execuetd only once during class loading
		//System.getProperty() is used to read JVM system properties passed at runtime using the -D flag
		env=System.getProperty("env","qa");//if nothing pass in terminal bydefault it takes qa which we passed here
		env=env.toLowerCase().trim();
		switch(env) {
		case "dev"->//arrow operator from java 14 takes care of break statement
		
			//File.separator is platform independent
			 file = new File(System.getProperty("user.dir") + File.separator+
					"src"+File.separator+"test"+File.separator+"resources"+File.separator+
					"config"+File.separator+"Config_DEV.properties");
		
		
		case "qa"->
			 file = new File(System.getProperty("user.dir") + File.separator+
					"src"+File.separator+"test"+File.separator+"resources"+File.separator+
					"config"+File.separator+"Config_QA.properties");
	
		
		case "uat"->
			 file = new File(System.getProperty("user.dir") + File.separator+
					"src"+File.separator+"test"+File.separator+"resources"+File.separator+
					"config"+File.separator+"Config_UAT.properties");
		
		
		default->
			 file = new File(System.getProperty("user.dir") + File.separator+
						"src"+File.separator+"test"+File.separator+"resources"+File.separator+
						"config"+File.separator+"Config_QA.properties");
		
		}
		
	
		FileReader fr = null;
		try {
			fr = new FileReader(file);
			prop.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static String getproperty(String key)  throws IOException {


	
	return prop.getProperty(key);
	
	
}
}