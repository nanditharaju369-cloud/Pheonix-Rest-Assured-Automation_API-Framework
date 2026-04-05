package com.api.tests;

import static  io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

public class MasterAPITest {

	@Test
	public void MasterAPI() throws IOException {
		given()
		.baseUri(ConfigManager.getproperty("BASE_URI"))
		.header("Authorization",AuthTokenProvider.gettoken(roles.FD))
		.contentType("")
		.when()
		.post("master")//default content-type application/url-formencoded
		.then()
		.statusCode(200)
		.time(Matchers.lessThan(5000L))
		.body("message",Matchers.equalTo("Success"))
		.body("data",Matchers.notNullValue())
		.body("data",Matchers.hasKey("mst_oem"))
		.body("$",Matchers.hasKey("message"))//outer array
		.body("$",Matchers.hasKey("data"))
		.body("data.mst_oem.size()",Matchers.equalTo(2))
		.body("data.mst_oem.id",Matchers.everyItem(Matchers.notNullValue()));
		
	}
	@Test
	public void Master_Negative() throws IOException {
		
		given()
		.baseUri(ConfigManager.getproperty("BASE_URI"))
		.header("Authorization","")
		.contentType("")
		.when()
		.post("master")//default content-type application/url-formencoded
		.then()
		.statusCode(401);
		
		
	}
}
