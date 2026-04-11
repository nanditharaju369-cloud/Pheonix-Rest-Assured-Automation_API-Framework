package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.User_POJO;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class CSV_ReaderUtility {

	//constructor is privater
	//static-static methods
	
	private CSV_ReaderUtility() {
		
	}
	public static void  LoadCSV(String csvpath) {
		
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("Test_Data/loginCred.csv");
InputStreamReader reader=new InputStreamReader(is);
CSVReader csvreader = new CSVReader(reader);


//code to map CSV to POJO
CsvToBean<User_POJO> csvtobean=new CsvToBeanBuilder(csvreader)
		.withType(User_POJO.class)
		.withIgnoreEmptyLine(true)
		.build();

List<User_POJO> userlist= csvtobean.parse();
System.out.println(userlist.get(0).getUsername());


}

}
