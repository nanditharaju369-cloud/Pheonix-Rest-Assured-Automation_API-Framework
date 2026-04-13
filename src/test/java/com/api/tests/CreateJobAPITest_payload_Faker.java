package com.api.tests;

import static com.api.utils.DateTimeUtil.gettimewithdaysago;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Record_Models.CreateJob_Payload;
import com.api.Record_Models.Customer;
import com.api.Record_Models.CustomerAddress;
import com.api.Record_Models.CustomerProduct;
import com.api.Record_Models.Problems;
import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problemslist;
import com.api.constants.Product;
import com.api.constants.ServiceLoc;
import com.api.constants.Warranty;
import com.api.constants.roles;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtils;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest_payload_Faker {
	private CreateJob_Payload create_payload;
	
	private final static String COUNTRY = "INDIAN";
	@BeforeMethod(description="Setups the payload for create job API")
	public void setup()
	{
	

			
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
			
			
			create_payload=new CreateJob_Payload(0, 2, 1, 1, customer, customeraddress, customerProduct, problemlist);

	}
	
	
	@Test(description = "verify of the Create JOb API is working for FD user ", groups = { "API", "Regression", "smoke" })
	public void createjobAPI() throws IOException {
		
		given()
		.spec(SpecUtils.requestspecwithroleandpayload(roles.FD, create_payload))
		//this 3 """ is coming from jaVa 15...this enhances readability in body
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtils.responsespec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CreateJob.json"))
		.body("message",Matchers.equalTo("Job created successfully. "))
		.body("data.job_number",Matchers.startsWith("JOB_"));
		
		
	}
	
}
