package com.api.tests.datadriven;

import static com.api.utils.DateTimeUtil.gettimewithdaysago;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest_Datadriven_faker {
	private CreateJob_Payload create_payload;

	@Test(description = "verify of the Create JOb API is working for FD user ", 
			groups = { "API", "Regression","datadriven" }, 
			dataProviderClass = com.dataproviders.DataProviderUtils.class, 
			dataProvider = "CreateJobAPIFakerDataProvider")
	public void createjobAPI(CreateJob_Payload createjobpayload) throws IOException {

		given().spec(SpecUtils.requestspecwithroleandpayload(roles.FD, createjobpayload))
				// this 3 """ is coming from jaVa 15...this enhances readability in body
				.when().post("/job/create").then().spec(SpecUtils.responsespec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CreateJob.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
