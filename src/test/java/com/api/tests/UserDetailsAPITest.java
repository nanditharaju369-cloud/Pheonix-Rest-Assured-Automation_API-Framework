package com.api.tests;

import org.testng.annotations.Test;

import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class UserDetailsAPITest {
	
	
	@Test
	public void UserDetails() throws IOException {
		
		Header authheader= new Header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3Njk4MzA1OTB9.eC_-RPFE5qBNG6Hx8Avb0D4rkPRg0QTvgXiUJGKL3Sk");
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
