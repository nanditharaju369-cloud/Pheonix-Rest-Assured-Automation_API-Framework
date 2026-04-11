package com.api.utils;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import static com.api.constants.roles.*;

import com.api.Record_Models.UserCredentials;
import com.api.constants.roles;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider() {
		
	}
	
	
	
//enums 
	public static String gettoken(roles role) throws IOException {
		UserCredentials user = null;
		if(role==FD){
			user=new UserCredentials("iamfd","password");
		}
		else if(role==(SUP)) {
			user=new UserCredentials("iamsup","password");
		}
		else if(role==(ENG)) {
			user=new UserCredentials("iamengg","password");
		}
		else if(role==(QC)) {
			user=new UserCredentials("iamqc","password");
		}
		
		
		String token=given().
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
		log().all().
		extract().body().jsonPath().getString("data.token");
		
		
		return token;

	}

}
