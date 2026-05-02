package com.database.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManagerhikari;
import com.db.model.customeraddressModel;

public class customeraddressDAO {
private static final String CUSTOMER_ADDRESS_QUERY=
"""
		select 
		id,
		flat_number,
		apartment_name,
		street_name,
		landmark,
		area,
		pincode,
		country,
		state 
		from tr_customer_address where id=?
		""";



public static customeraddressModel getcustomeraddressinfo(int custaddressid) throws SQLException, IOException {
	
	customeraddressModel custaddresdbmodel = null;
	Connection conn=DatabaseManagerhikari.getconnection();
	PreparedStatement preparestmt=conn.prepareStatement(CUSTOMER_ADDRESS_QUERY);
	preparestmt.setInt(1, custaddressid);
	ResultSet rs=preparestmt.executeQuery();
	
	while(rs.next()) {
		 try {
			custaddresdbmodel=new customeraddressModel(rs.getInt("id"),
					rs.getString("flat_number"),
					rs.getString("apartment_name"),
					rs.getString("street_name"), 
					rs.getString("landmark"), 
					rs.getString("area"), 
					rs.getString("pincode"), 
					rs.getString("country"),  
					rs.getString("state"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return custaddresdbmodel;
	
}
}
