package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.Record_Models.CreateJob_Payload;
import com.api.Record_Models.Customer;
import com.api.Record_Models.CustomerAddress;
import com.api.Record_Models.CustomerProduct;
import com.api.Record_Models.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
	private final static String COUNTRY = "INDIA";
	private static Faker faker = new Faker(new Locale("en-IND"));
	private static final int MST_SERVICE_LOCATION_ID = 0;
	private static final int MST_PLATFORM_ID = 2;
	private static final int MST_WARRANY_STATUS_ID = 1;
	private static final int MST_OEM_ID = 1;

	private FakerDataGenerator() {

	}

	public static CreateJob_Payload Generatefakecreatejobdata() {
		Customer customer = genearatefakecustomerdata();
		CustomerAddress customeraddress = generatefakecustomeraddressdata();
		CustomerProduct customerproduct = generataefakecustomerproductdata();
		List<Problems> problems = genratefakeproblemslist();

		CreateJob_Payload payload = new CreateJob_Payload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRANY_STATUS_ID, MST_OEM_ID, customer, customeraddress, customerproduct, problems);

		return payload;
	}
	
	public static Iterator<CreateJob_Payload> Generatefakecreatejobdata(int count) {
		List<CreateJob_Payload> payloadlist=new ArrayList<CreateJob_Payload>();
		for(int i=0;i<count;i++) {
		Customer customer = genearatefakecustomerdata();
		CustomerAddress customeraddress = generatefakecustomeraddressdata();
		CustomerProduct customerproduct = generataefakecustomerproductdata();
		List<Problems> problems = genratefakeproblemslist();

		CreateJob_Payload payload = new CreateJob_Payload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRANY_STATUS_ID, MST_OEM_ID, customer, customeraddress, customerproduct, problems);
		payloadlist.add(payload);

	}
		return payloadlist.iterator();}

	
	

	private static List<Problems> genratefakeproblemslist() {

		String fakeremark = faker.lorem().sentence(10);
		Random random = new Random();
		int problemID = random.nextInt(2) + 1;
		Problems problems = new Problems(problemID, fakeremark);
		List<Problems> problemlist = new ArrayList<Problems>();
		problemlist.add(problems);
		return problemlist;
	}

	private static CustomerProduct generataefakecustomerproductdata() {
		String dop = DateTimeUtil.gettimewithdaysago(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, 1, 1);
		return customerProduct;
	}

	private static CustomerAddress generatefakecustomeraddressdata() {
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.company().name();
		String streetName = faker.address().streetName();
		String landmark = faker.address().secondaryAddress();
		String area = faker.address().cityName();
		String pinCode = faker.numerify("######");

		String state = faker.address().state();
		CustomerAddress customeraddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,
				pinCode, COUNTRY, state);
		return customeraddress;
	}

	private static Customer genearatefakecustomerdata() {
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String Mobilenumber = faker.numerify("70########");
		String altnumber = faker.numerify("70########");
		String customeremailaddress = faker.internet().emailAddress();
		String customeraltemailaddress = faker.internet().emailAddress();
		Customer customer = new Customer(fname, lname, Mobilenumber, altnumber, customeremailaddress,
				customeraltemailaddress);
		return customer;
	}
}
