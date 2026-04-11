package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.Record_Models.UserCredentials;
import com.api.utils.SpecUtils;
import com.dataproviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIDatadriven {

	

	@Test(description = "verify of the login API is working for FD user ", 
			groups = { "API", "Regression", "datadriven" },
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider="LoginAPIDP")
	public void loginapitest(UserBean userbean) throws IOException {

		given().spec(SpecUtils.requestspec(userbean)).when().post("login").then().spec(SpecUtils.responsespec_OK())
				.body("message", equalTo("Success"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/loginresponse.json"));

	}

}
