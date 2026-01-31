package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


@Test
public class LoginAPITest {
UserCredentials user=new UserCredentials("iamfd","password");
	public void loginapitest() {
		given().
		baseUri("http://64.227.160.186:9000/v1").
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
		time(lessThan(2000L)).
		log().all().
		body("message",equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/loginresponse.json"));
		
	}
	

}
