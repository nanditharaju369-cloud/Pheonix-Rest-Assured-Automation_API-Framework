package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.Record_Models.CreateJob_Payload;
import com.api.Record_Models.Customer;
import com.api.Record_Models.CustomerAddress;
import com.api.Record_Models.CustomerProduct;
import com.api.Record_Models.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	private final static String COUNTRY = "INDIAN";

	public static void main(String[] args) {

		
		Faker faker = new Faker(new Locale("en-IND"));

		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String Mobilenumber = faker.numerify("70########");
		String altnumber = faker.numerify("70########");
		String customeremailaddress = faker.internet().emailAddress();
		String customeraltemailaddress = faker.internet().emailAddress();
		Customer customer = new Customer(fname, lname, Mobilenumber, altnumber, customeremailaddress,
				customeraltemailaddress);

		String flatNumber = faker.numerify("###");
		String apartmentName = faker.company().name();
		String streetName = faker.address().streetName();
		String landmark = faker.address().secondaryAddress();
		String area = faker.address().cityName();
		String pinCode = faker.numerify("######");

		String state = faker.address().state();
		CustomerAddress customeraddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,
				pinCode, COUNTRY, state);

		// CustomerProduct Fake Object
		String dop = DateTimeUtil.gettimewithdaysago(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, 1, 1);

		String fakeremark = faker.lorem().sentence(10);
		Random random = new Random();
		int problemID=random.nextInt(2) + 1;

		Problems problems = new Problems(problemID, fakeremark);
		
		List<Problems> problemlist=new ArrayList<Problems>();
		problemlist.add(problems);
		
		
		CreateJob_Payload payload=new CreateJob_Payload(0, 2, 1, 1, customer, customeraddress, customerProduct, problemlist);
System.out.println(payload);
	}
}