package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class CountAPITest {

	@Test
	public void verifycountAPI() throws IOException {
	 given()
	 .spec(SpecUtils.requestspecwithAuth(roles.FD))
	.when()
	.get("/dashboard/count")
	.then()
	.spec(SpecUtils.responsespec_OK())
	.body("data",notNullValue())
	.body("data.size()",equalTo(3))
	.body("data.count",everyItem(greaterThanOrEqualTo(0)))
	.body("data.label",everyItem(not(blankOrNullString())))//negation
	.body("data.key",containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"))
	 .body(matchesJsonSchemaInClasspath("response_schema/CountAPIschema.json"));
	}
	
	@Test
	public void countAPI_Missauth() throws IOException {
		 given()
		 .spec(SpecUtils.requestspecwithAuth(roles.FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtils.responsespec(401));
		
	}
}
