package com.database.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;

import com.api.Record_Models.Customer;
import com.db.model.CustomerDBmodel;

public class DAORunner {

	public static void main(String[] args) throws SQLException, IOException {
		CustomerDBmodel customerdb=CustomerDAO.getcustomerinfo(268594);
		
		System.out.println(customerdb);
		
		Customer customer=new Customer("Vinnie", "Orn", "680-647-9976", "", "Milton_Boehm82@hotmail.com", "");
Assert.assertEquals(customerdb.getFirst_name(), customer.first_name());


	}

}
