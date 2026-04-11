package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Record_Models.UserCredentials;
import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	// instance variable always need to marked with private
	private UserCredentials user;

	
	@BeforeMethod(description="Create the payload for login api")
	public void setup() {
		user = new UserCredentials("iamfd", "password");
	}

	@Test(description = "verify of the login API is working for FD user ", groups = { "API", "Regression", "smoke" })
	public void loginapitest() throws IOException {

		given().spec(SpecUtils.requestspec(user)).when().post("login").then().spec(SpecUtils.responsespec_OK())
				.body("message", equalTo("Success"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/loginresponse.json"));

	}

}
