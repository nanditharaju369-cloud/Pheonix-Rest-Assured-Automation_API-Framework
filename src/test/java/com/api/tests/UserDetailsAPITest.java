package com.api.tests;

import org.testng.annotations.Test;

import com.api.constants.roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtils;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class UserDetailsAPITest {
	
	
	@Test(description = "verify of the User Details API is working for FD user ", groups = { "API", "Regression", "smoke" })
	public void UserDetails() throws IOException {
		
		
		given().
			spec(SpecUtils.requestspecwithAuth(roles.FD))
			
		.when().
			get("userdetails").
		then().
			spec(SpecUtils.responsespec_OK())
			.body("message",equalTo("Success")).
			log().body().
			log().body();
			
		
		
	}

}
