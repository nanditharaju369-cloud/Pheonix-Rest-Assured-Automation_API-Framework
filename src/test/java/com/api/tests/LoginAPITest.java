package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


@Test
public class LoginAPITest {
	

UserCredentials user=new UserCredentials("iamfd","password");
	public void loginapitest() throws IOException {
		
		given()
		.spec(SpecUtils.requestspec(user))
		.when().
			post("login").
		then().
		spec(SpecUtils.responsespec_OK())
		.body("message",equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/loginresponse.json"));
		
	}
	

}
