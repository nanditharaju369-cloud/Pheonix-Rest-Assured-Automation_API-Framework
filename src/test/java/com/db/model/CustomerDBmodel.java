package com.db.model;

public class CustomerDBmodel {
private String first_name;
private String last_name;
private String mobile_number;
private String mobile_number_alt;
private String email_id;
private String email_id_alt;
private int tr_customer_address_id;
public CustomerDBmodel(String first_name, String last_name, String mobile_number, String mobile_number_alt,
		String email_id, String email_id_alt,int tr_customer_address_id) {
	super();
	this.first_name = first_name;
	this.last_name = last_name;
	this.mobile_number = mobile_number;
	this.mobile_number_alt = mobile_number_alt;
	this.email_id = email_id;
	this.email_id_alt = email_id_alt;
	this.tr_customer_address_id=tr_customer_address_id;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getMobile_number() {
	return mobile_number;
}
public void setMobile_number(String mobile_number) {
	this.mobile_number = mobile_number;
}
public String getMobile_number_alt() {
	return mobile_number_alt;
}
public void setMobile_number_alt(String mobile_number_alt) {
	this.mobile_number_alt = mobile_number_alt;
}
public String getEmail_id() {
	return email_id;
}
public void setEmail_id(String email_id) {
	this.email_id = email_id;
}
public String getEmail_id_alt() {
	return email_id_alt;
}
public void setEmail_id_alt(String email_id_alt) {
	this.email_id_alt = email_id_alt;
	
	
}
public int getTr_customer_address_id() {
	return tr_customer_address_id;
}
public void setTr_customer_address_id(int tr_customer_address_id) {
	this.tr_customer_address_id = tr_customer_address_id;
}
@Override
public String toString() {
	return "CustomerDBmodel [first_name=" + first_name + ", last_name=" + last_name + ", mobile_number=" + mobile_number
			+ ", mobile_number_alt=" + mobile_number_alt + ", email_id=" + email_id + ", email_id_alt=" + email_id_alt
			+ ", tr_customer_address_id=" + tr_customer_address_id + "]";
}

}




