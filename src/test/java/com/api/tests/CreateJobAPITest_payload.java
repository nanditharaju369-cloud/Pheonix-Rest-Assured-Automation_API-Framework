package com.api.tests;

import static com.api.utils.DateTimeUtil.gettimewithdaysago;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problemslist;
import com.api.constants.Product;
import com.api.constants.ServiceLoc;
import com.api.constants.Warranty;
import com.api.constants.roles;
import com.api.pojo.CreateJob_Payload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest_payload {
	private CreateJob_Payload create_payload;
	
	
	@BeforeMethod(description="Setups the payload for create job API")
	public void setup()
	{
		Customer customer=new Customer("Nandy","Shetty","887655656","","nanditha76@gmail.com","");
		CustomerAddress Address = new CustomerAddress("D 40","Vasath galaxy","Bangur nagar","Inorbut","Mumbai","67677","India","Maharastha");
		CustomerProduct CustomerProduct=new CustomerProduct(gettimewithdaysago(10),"2086543456763767","2086543456763767","2086543456763767",gettimewithdaysago(10),
				Product.NEXUS_2.getcode(),Model.NEXUS_2_BLUE.getcode());
		Problems problem = new Problems(Problemslist.SMARTPHONE_IS_RUNNING_SLOW.getcode(),"Battery Issue");

		List<Problems> problemsList = new ArrayList<>();
		problemsList.add(problem);
		create_payload=new CreateJob_Payload(ServiceLoc.SERVICE_LOCATION_A.getcode(),
				Platform.FRONT_DESK.getcode(),Warranty.IN_WARRANTY.getcode(),OEM.GOOGLE.getcode(),
				customer,Address,CustomerProduct,problemsList);
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
