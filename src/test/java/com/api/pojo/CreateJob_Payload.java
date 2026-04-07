package com.api.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateJob_Payload(int mst_service_location_id, int mst_platform_id, int mst_warrenty_status_id,
		int mst_oem_id,

		Customer customer, CustomerAddress customer_address,
		   @JsonProperty("customer_product")   // 🔥 THIS FIXES EVERYTHING
	    CustomerProduct product,

		List<Problems> problems) {

}
