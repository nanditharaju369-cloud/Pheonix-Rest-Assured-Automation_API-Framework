package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariRunner {

	public static void main(String[] args) throws SQLException, IOException {
		Connection conn=DatabaseManagerhikari .getconnection();
	}

}
