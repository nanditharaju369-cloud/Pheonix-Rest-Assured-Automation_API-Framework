package com.api.tests;

import static  io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtils;

public class MasterAPITest {

	@Test(description = "verify of the Master API is working for FD user ", groups = { "API", "Regression", "smoke" })
	public void MasterAPI() throws IOException {
		given()
		.spec(SpecUtils.requestspecwithAuth(roles.FD))
		.when()
		.post("master")//default content-type application/url-formencoded
		.then()
		.spec(SpecUtils.responsespec_OK())
		.body("message",Matchers.equalTo("Success"))
		.body("data",Matchers.notNullValue())
		.body("data",Matchers.hasKey("mst_oem"))
		.body("$",Matchers.hasKey("message"))//outer array
		.body("$",Matchers.hasKey("data"))
		.body("data.mst_oem.size()",Matchers.equalTo(2))
		.body("data.mst_oem.id",Matchers.everyItem(Matchers.notNullValue()));
		
	}
	@Test(description = "verify of the Master API is working for FD user for no auth ", groups = { "Negative","API", "Regression", "smoke" })
	public void Master_Negative() throws IOException {
		
		given()
		.spec(SpecUtils.requestspec())
		.when()
		.post("master")//default content-type application/url-formencoded
		.then()
		.spec(SpecUtils.responsespec(401));
		
		
	}
}
