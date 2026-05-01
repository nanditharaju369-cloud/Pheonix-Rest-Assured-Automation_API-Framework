package com.database.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DatabaseManagerhikari;
import com.db.model.CustomerDBmodel;

public class CustomerDAO {
	private static final String CUSTOMER_DETAIL_QUERY="""
			select * from tr_customer where id=268594
			""";
	
	public static CustomerDBmodel getcustomerinfo() throws SQLException, IOException {
		CustomerDBmodel customermodel = null;
		Connection conn=DatabaseManagerhikari.getconnection();
		Statement statement=conn.createStatement();
		ResultSet resultset=statement.executeQuery(CUSTOMER_DETAIL_QUERY);
		
		while(resultset.next()) {
			System.out.println(resultset.getString("first_name"));
			
			 customermodel=new CustomerDBmodel(resultset.getString("first_name"), resultset.getString("last_name"), resultset.getString("mobile_number"), resultset.getString("mobile_number_alt"), resultset.getString("email_id"), resultset.getString("email_id_alt"));
		}
		return customermodel;
		
		
		
		
	}

}
