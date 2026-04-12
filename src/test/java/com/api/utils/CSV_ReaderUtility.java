package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class CSV_ReaderUtility {

	//constructor is privater
	//static-static methods
	
	private CSV_ReaderUtility() {
		
	}
	public static <T> Iterator<T>  LoadCSV(String csvpath, Class<T> bean) {
		
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(csvpath);
InputStreamReader reader=new InputStreamReader(is);
CSVReader csvreader = new CSVReader(reader);


//code to map CSV to POJO
CsvToBean<T> csvtobean=new CsvToBeanBuilder(csvreader)
		.withType(bean)
		.withIgnoreEmptyLine(true)
		.build();

List<T>list= csvtobean.parse();
//System.out.println(userlist.get(0).getUsername());
return list.iterator();


}

}
