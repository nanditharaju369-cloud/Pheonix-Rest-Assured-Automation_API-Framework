package com.api.tests;

import static com.api.utils.DateTimeUtil.gettimewithdaysago;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
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
import com.api.utils.SpecUtils;
import com.database.dao.CustomerDAO;
import com.database.dao.customeraddressDAO;
import com.db.model.CustomerDBmodel;
import com.db.model.customeraddressModel;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest_DBValidation {
	private CreateJob_Payload create_payload;
	
	private Customer customer;
	private CustomerAddress Address;
	@BeforeMethod(description="Setups the payload for create job API")
	public void setup()
	{
		 customer=new Customer("Nandy","Shetty","887655656","","nanditha76@gmail.com","");
		Address = new CustomerAddress("D 40","Vasath galaxy","Bangur nagar","Inorbut","Mumbai","67677","India","Maharastha");
		CustomerProduct CustomerProduct=new CustomerProduct(gettimewithdaysago(10),"2186543456763764","2186543456763764","2186543456763764",gettimewithdaysago(10),
				Product.NEXUS_2.getcode(),Model.NEXUS_2_BLUE.getcode());
		Problems problem = new Problems(Problemslist.SMARTPHONE_IS_RUNNING_SLOW.getcode(),"Battery Issue");

		List<Problems> problemsList = new ArrayList<>();
		problemsList.add(problem);
		create_payload=new CreateJob_Payload(ServiceLoc.SERVICE_LOCATION_A.getcode(),
				Platform.FRONT_DESK.getcode(),Warranty.IN_WARRANTY.getcode(),OEM.GOOGLE.getcode(),
				customer,Address,CustomerProduct,problemsList);
	}
	
	@Test(description = "verify of the Create JOb API is working for FD user ", groups = { "API", "Regression", "smoke" })
	public void createjobAPI() throws IOException, SQLException {
		
		int customerid=given()
		.spec(SpecUtils.requestspecwithroleandpayload(roles.FD, create_payload))
		//this 3 """ is coming from jaVa 15...this enhances readability in body
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtils.responsespec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CreateJob.json"))
		.body("message",Matchers.equalTo("Job created successfully. "))
		.body("data.job_number",Matchers.startsWith("JOB_"))
		.extract().body().jsonPath().getInt("data.tr_customer_id")		;
		
		System.out.println(customerid);
		
		CustomerDBmodel customerdatafromdb= CustomerDAO.getcustomerinfo(customerid);
		
		//checking payload we given is same presnt in DB
		Assert.assertEquals(customer.first_name(),customerdatafromdb.getFirst_name() );
		Assert.assertEquals(customer.last_name(),customerdatafromdb.getLast_name() );
		Assert.assertEquals(customer.email_id(),customerdatafromdb.getEmail_id() );
		Assert.assertEquals(customer.email_id_alt(),customerdatafromdb.getEmail_id_alt() );
		
		
		//Address assestions
		customeraddressModel custaddressfromdb=customeraddressDAO.getcustomeraddressinfo(customerdatafromdb.getTr_customer_address_id());
	//Assert.assertEquals(Address.flat_number(), custaddressfromdb.getFlat_number());
	Assert.assertEquals(Address.apartment_name(), custaddressfromdb.getApartment_name());
	Assert.assertEquals(Address.state(), custaddressfromdb.getState());
	Assert.assertEquals(Address.landmark(), custaddressfromdb.getLandmark());
	}
	
}
