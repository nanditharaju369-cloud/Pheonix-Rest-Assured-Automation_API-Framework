package com.api.Record_Models;

//instead of normal pojo this is record that comes with java 16
public record Customer(String first_name, String last_name, String mobile_number, String mobile_number_alt,
		String email_id, String email_id_alt

) {
}
