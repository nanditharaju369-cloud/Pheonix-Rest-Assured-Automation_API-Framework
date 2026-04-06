package com.api.utils;

import java.io.IOException;

import org.hamcrest.Matchers;

import com.api.constants.roles;
import com.api.pojo.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {
	
	
public static RequestSpecification requestspec() throws IOException {
	RequestSpecification request=new RequestSpecBuilder()
			.setBaseUri(ConfigManager.getproperty("BASE_URI"))
			.setContentType(ContentType.JSON)
			.setAccept(ContentType.JSON)
			.log(LogDetail.URI)
			.log(LogDetail.METHOD)
			.log(LogDetail.HEADERS)
			.log(LogDetail.BODY)
			.build();
	
	return request;
	
}





//object as data type coz of lose coupling

public static RequestSpecification requestspec(Object payload) throws IOException {
	RequestSpecification request=new RequestSpecBuilder()
			.setBaseUri(ConfigManager.getproperty("BASE_URI"))
			.setContentType(ContentType.JSON)
			.setAccept(ContentType.JSON)
			.setBody(payload)
			.log(LogDetail.URI)
			.log(LogDetail.METHOD)
			.log(LogDetail.HEADERS)
			.log(LogDetail.BODY)
			.build();
	
	return request;
	
}


public static RequestSpecification requestspecwithAuth(roles role) throws IOException {
	RequestSpecification request=new RequestSpecBuilder()
			.setBaseUri(ConfigManager.getproperty("BASE_URI"))
			.setContentType(ContentType.JSON)
			.setAccept(ContentType.JSON)
			.addHeader("Authorization",AuthTokenProvider.gettoken(role))
			.log(LogDetail.URI)
			.log(LogDetail.METHOD)
			.log(LogDetail.HEADERS)
			.log(LogDetail.BODY)
			.build();
	
	return request;
	
}











public static ResponseSpecification responsespec_OK() {
	ResponseSpecification respnsespec=new ResponseSpecBuilder()
	.expectContentType(ContentType.JSON)
	.expectStatusCode(200)
	.expectResponseTime(Matchers.lessThan(1000L))
	.log(LogDetail.ALL)
	.build();
	
	return respnsespec;
}


public static ResponseSpecification responsespec(int statuscode) {
	ResponseSpecification respnsespec=new ResponseSpecBuilder()
	.expectStatusCode(statuscode)
	.expectResponseTime(Matchers.lessThan(1000L))
	.log(LogDetail.ALL)
	.build();
	
	return respnsespec;
}


public static ResponseSpecification responsespec_JSON(int statuscode) {
	ResponseSpecification respnsespec=new ResponseSpecBuilder()
	.expectStatusCode(statuscode)
	.expectContentType(ContentType.JSON)
	.expectResponseTime(Matchers.lessThan(1000L))
	.log(LogDetail.ALL)
	.build();
	
	return respnsespec;
}



}
