package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


@Test
public class LoginAPITest {
	

UserCredentials user=new UserCredentials("iamfd","password");
	public void loginapitest() throws IOException {
		given().
		baseUri(ConfigManager.getproperty("BASE_URI")).
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(user).
		log().uri().
		log().method().
		log().headers().
		when().
			post("/login").
		then().
		statusCode(200).
		time(lessThan(3000L)).
		log().all().
		body("message",equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/loginresponse.json"));
		
	}
	

}
