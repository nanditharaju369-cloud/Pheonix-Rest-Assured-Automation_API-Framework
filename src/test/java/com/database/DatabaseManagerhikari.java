package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManagerhikari {

	private volatile static Connection conn;
	private static HikariConfig hikariconfig;
	private volatile static HikariDataSource hikarids;

	private DatabaseManagerhikari() {

	}
	// Thread-safe Singleton initialization--sync
//	public synchronized static void createconnection() throws SQLException, IOException {
//		if(conn==null) {//for only one connection
//		conn=DriverManager.getConnection(ConfigManager.getproperty("DB_URL"), ConfigManager.getproperty("DB_USERNAME"), ConfigManager.getproperty("DB_PASSWORD"));
//		}
//	
//	}

//Double-Checked Locking
	private static void Initilizepool() throws SQLException, IOException {
		if (hikarids == null) {
			synchronized (DatabaseManagerhikari.class) {
				if (hikarids == null) {
					// conn=DriverManager.getConnection(ConfigManager.getproperty("DB_URL"),
					// ConfigManager.getproperty("DB_USERNAME"),
					// ConfigManager.getproperty("DB_PASSWORD"));
					HikariConfig hkariconfig = new HikariConfig();
					hkariconfig.setJdbcUrl(ConfigManager.getproperty("DB_URL"));
					hkariconfig.setUsername(ConfigManager.getproperty("DB_USERNAME"));
					hkariconfig.setPassword(ConfigManager.getproperty("DB_PASSWORD"));
			
					// config
					hkariconfig.setMaximumPoolSize(Integer.parseInt(ConfigManager.getproperty("MAXIMUM_POOL_SIZE")));
					hkariconfig.setMinimumIdle(Integer.parseInt(ConfigManager.getproperty("MINIMUM_IDLE_TIME")));
					hkariconfig.setConnectionTimeout(
							Integer.parseInt(ConfigManager.getproperty("CONNECTION_TIMEOUT")) * 1000);// 10 sec
					hkariconfig.setIdleTimeout(Integer.parseInt(ConfigManager.getproperty("IDLE_TIMEOUT")) * 1000);
					hkariconfig.setMaxLifetime(Integer.parseInt(ConfigManager.getproperty("MAX_LIFETIME")) * 60 * 1000); // 30
																															// mins,close
																															// all
																															// connections
																															// and
																															// creates
																															// fresh																										// 30
																															// mins
					hkariconfig.setPoolName("Pheonix pool");

					hikarids = new HikariDataSource(hkariconfig);

				}
			}
		}

	}

	public static Connection getconnection() throws SQLException, IOException {
		Connection conn = null;

		if (hikarids == null) {
			Initilizepool();
		} else if (hikarids.isClosed()) {
			throw new SQLException("HIKARI DATA SURCE IS CLOSED");
		}
		conn = hikarids.getConnection();
		return conn;

	}
}
