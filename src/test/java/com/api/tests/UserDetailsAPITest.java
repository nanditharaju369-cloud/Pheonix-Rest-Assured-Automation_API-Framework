package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class UserDetailsAPITest {
	
	
	@Test
	public void UserDetails() throws IOException {
		
		Header authheader= new Header("Authorization",AuthTokenProvider.gettoken(roles.FD));
		given().
			baseUri(ConfigManager.getproperty("BASE_URI")).
			contentType(ContentType.JSON).
			header(authheader).
		when().
			get("/userdetails").
		then().
			statusCode(200).
			time(lessThan(3000L)).
			body("message",equalTo("Success")).
			log().body().
			log().body();
			
		
		
	}

}
