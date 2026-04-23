package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) throws SQLException {
//Establish the connection to pheonix DB
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://64.227.160.186:3306/SR_DEV", "srdev_ro_automation"
, "Srdev@123");
		
		Statement statement=conn.createStatement();
		ResultSet resultset=statement.executeQuery("SELECT * from first_name,last_name,mobile_number from tr_customer");
		
		while(resultset.next()) {
			String fname=resultset.getString("first_name");
			String lname=resultset.getString("last_name");
			String mobnum=resultset.getString("mobile_number");
			
			System.out.println(fname+" |" +lname+ " |"+mobnum);
		}
		
	}

}
