package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.roles;
import com.api.pojo.CreateJob_Payload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest_payload {

	
	
	@Test
	public void createjobAPI() throws IOException {
		Customer customer=new Customer("Nandy","Shetty","887655656","","nanditha76@gmail.com","");
		CustomerAddress Address = new CustomerAddress("D 40","Vasath galaxy","Bangur nagar","Inorbut","Mumbai","67677","India","Maharastha");
		CustomerProduct Product=new CustomerProduct(gettimewithdaysago(10),"1076543456763767","1076543456763767","1076543456763767",gettimewithdaysago(10),1,1);
		Problems problem = new Problems(1,"Battery Issue");

		List<Problems> problemsList = new ArrayList<>();
		problemsList.add(problem);
		CreateJob_Payload create_payload=new CreateJob_Payload(0,2,1,1,customer,Address,Product,problemsList);
		
		
		
		
		
		
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
