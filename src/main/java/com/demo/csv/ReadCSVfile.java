package com.demo.csv;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import io.restassured.internal.support.FileReader;

public class ReadCSVfile {

	public static void main(String[] args) throws IOException, CsvException {
		// TODO Auto-generated method stub
		
	
		//File file = new File(
				//"C:\\Users\\Nanditha\\eclipse-workspace\\PheonixAutomationFramework_API\\src\\main\\resources\\Test_Data\\loginCred.csv");
		//java.io.FileReader filereader = new java.io.FileReader(file);
				InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("Test_Data/loginCred.csv");
		InputStreamReader reader=new InputStreamReader(is);
		CSVReader csvreader = new CSVReader(reader);
		
		
		List<String[]> datalist=csvreader.readAll();
		
		for(String[] dataarray:datalist) {
			for(String data:dataarray) {
				System.out.println(data);
			}
		}
		
		

	}

}
