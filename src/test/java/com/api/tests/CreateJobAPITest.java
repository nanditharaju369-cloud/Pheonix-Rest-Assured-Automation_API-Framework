package com.api.tests;

import org.testng.annotations.Test;

import com.api.Record_Models.CreateJob_Payload;
import com.api.Record_Models.Customer;
import com.api.Record_Models.CustomerAddress;
import com.api.Record_Models.CustomerProduct;
import com.api.Record_Models.Problems;
import com.api.constants.roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {

	
	
	@Test
	public void createjobAPI() throws IOException {
		Customer customer=new Customer("Nandy","Shetty","887655656","","nanditha76@gmail.com","");
		CustomerAddress Address = new CustomerAddress("D 40","Vasath galaxy","Bangur nagar","Inorbut","Mumbai","67677","India","Maharastha");
		CustomerProduct Product=new CustomerProduct("2025-12-25T18:30:00.000Z","8876543456768767","8876543456768767","8876543456768767","2025-12-25T18:30:00.000Z",1,1);
		Problems problem = new Problems(1,"Battery Issue");

		List<Problems> problemsList = new ArrayList<>();
		problemsList.add(problem);
		CreateJob_Payload create_payload=new CreateJob_Payload(0,2,1,1,customer,Address,Product,problemsList);
		
		
		
		
		
		
		given()
		.baseUri(ConfigManager.getproperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.header("Authorization",AuthTokenProvider.gettoken(roles.FD))
		//this 3 """ is coming from jaVa 15...this enhances readability in body
		.body("""
				{
				"mst_service_location_id": 0,
    "mst_platform_id": 2,
    "mst_warrenty_status_id": 1,
    "mst_oem_id": 1,
    "customer": {
        "first_name": "Nanditha",
        "last_name": "Raju",
        "mobile_number": "99866564566",
        "mobile_number_alt": "",
        "email_id": "nanditha77@gmail.com",
        "email_id_alt": ""
    },
    "customer_address": {
        "flat_number": "23",
        "apartment_name": "samay",
        "street_name": "5080, I block, 8th Cross, Kanakadas Nagar",
        "landmark": "park",
        "area": "RK nagar",
        "pincode": "570022",
        "country": "India",
        "state": "Karnataka"
    },
    "customer_product": {
        "dop": "2025-12-25T18:30:00.000Z",
        "serial_number": "8878767676567678",
        "imei1": "8878767676567678",
        "imei2": "8878767676567678",
        "popurl": "2025-12-25T18:30:00.000Z",
        "product_id": 1,
        "mst_model_id": 1
    },
    "problems": [
        {
            "id": 1,
            "remark": "Battery issue"
        }
    ]	
				}
				
				
				""")
		.when()
		.post("/job/create")
		.then()
		.log().all()
		.statusCode(200);
		
		
	}
	
}
