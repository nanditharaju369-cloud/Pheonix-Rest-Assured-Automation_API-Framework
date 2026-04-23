package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Hikaridemo {

	public static void main(String[] args) throws IOException, SQLException {
		HikariConfig hkariconfig=new HikariConfig();
		hkariconfig.setJdbcUrl(ConfigManager.getproperty("DB_URL"));
		hkariconfig.setUsername(ConfigManager.getproperty("DB_USERNAME"));
		hkariconfig.setPassword(ConfigManager.getproperty("DB_PASSWORD"));

		//config
		hkariconfig.setMaximumPoolSize(10);
		hkariconfig.setMinimumIdle(2);
		hkariconfig.setConnectionTimeout(10*100);//10 sec
		hkariconfig.setIdleTimeout(10*1000);
		hkariconfig.setMaxLifetime(1800000); //30 mins,close all connections and creates fresh after 30 mins
		hkariconfig.setPoolName("Pheonix pool");
		
		
		HikariDataSource ds=new HikariDataSource(hkariconfig);
		Connection conn=ds.getConnection();
		
		Statement statement=conn.createStatement();
		ResultSet rs=statement.executeQuery("SELECT * FROM tr_customer");
		
		while(rs.next()) {
			System.out.println(rs.getString("first_name"));
		}
		ds.close();
	}
	
	

}
