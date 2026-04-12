package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		 Faker faker=new Faker(new Locale("en-IND"));
		 
String name=faker.name().firstName();
String lname=faker.name().lastName();
System.out.println(name+" "+lname);

String flatnum=faker.address().buildingNumber();
System.out.println(flatnum);

System.out.println(faker.internet().emailAddress());
	}

}
