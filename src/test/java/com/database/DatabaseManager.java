package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {
	
	private volatile static Connection conn;
	private static HikariConfig hikariconfig;
	private volatile static HikariDataSource hikarids;
	private DatabaseManager() {
		
	}
	//Thread-safe Singleton initialization--sync
//	public synchronized static void createconnection() throws SQLException, IOException {
//		if(conn==null) {//for only one connection
//		conn=DriverManager.getConnection(ConfigManager.getproperty("DB_URL"), ConfigManager.getproperty("DB_USERNAME"), ConfigManager.getproperty("DB_PASSWORD"));
//		}
//	
//	}

//Double-Checked Locking
	public static void instancepool() throws SQLException, IOException
	{
		if(conn==null) {
			synchronized (DatabaseManager.class) {
				if(conn==null) {
					conn=DriverManager.getConnection(ConfigManager.getproperty("DB_URL"), ConfigManager.getproperty("DB_USERNAME"), ConfigManager.getproperty("DB_PASSWORD"));
				}
			}
		}
		
	}
	
	
	
	
}
