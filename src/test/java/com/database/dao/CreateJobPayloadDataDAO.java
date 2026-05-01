package com.database.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.database.DatabaseManagerhikari;
import com.dataproviders.api.bean.CreateJobBean;

public class CreateJobPayloadDataDAO {
	
	
	private static final String SQL_QUERY=
			"""
			
			SELECT
			mst_service_location_id,
			mst_platform_id,
			mst_warrenty_status_id,
			mst_oem_id,
			first_name,
			last_name,
			mobile_number,
			mobile_number_alt,
			email_id,
			email_id_alt,
			flat_number,
			apartment_name,
			street_name,
			landmark,
			area,
			pincode,
			country,
			state,
			serial_number,
			imei1,
			imei2,
			popurl,
			dop,
			mst_model_id,
			mst_problem_id,
			remark


			FROM tr_customer
			INNER JOIN tr_customer_address
			ON tr_customer.tr_customer_address_id = tr_customer_address.id

			INNER JOIN tr_customer_product
			on tr_customer_product.tr_customer_id= tr_customer.id


			INNER JOIN tr_job_head
			on tr_job_head.tr_customer_id= tr_customer.id

			INNER JOIN map_job_problem
			on tr_job_head.id= map_job_problem.tr_job_head_id


			LIMIT 5;
						""";
	
	public static List<CreateJobBean> getCreateJobPayloadData() throws SQLException, IOException {
		//Need connection
		Connection conn=DatabaseManagerhikari.getconnection();
		Statement statement=conn.createStatement();
		ResultSet resultset=statement.executeQuery(SQL_QUERY);
	
		List<CreateJobBean> beanlist=new ArrayList<CreateJobBean>();
		
		while(resultset.next()) {
			CreateJobBean createjob=new CreateJobBean();
			createjob.setMst_service_location_id(resultset.getString("mst_service_location_id"));
			createjob.setMst_platform_id(resultset.getString("mst_platform_id"));
			createjob.setMst_warrenty_status_id(resultset.getString("mst_warrenty_status_id"));
			createjob.setMst_oem_id("1");

			createjob.setCustomer__first_name(resultset.getString("first_name"));
			createjob.setCustomer__last_name(resultset.getString("last_name"));
			createjob.setCustomer__mobile_number(resultset.getString("mobile_number"));
			createjob.setCustomer__mobile_number_alt(resultset.getString("mobile_number_alt"));
			createjob.setCustomer__email_id(resultset.getString("email_id"));
			createjob.setCustomer__email_id_alt(resultset.getString("email_id_alt"));

			createjob.setCustomer_address__flat_number(resultset.getString("flat_number"));
			createjob.setCustomer_address__apartment_name(resultset.getString("apartment_name"));
			createjob.setCustomer_address__street_name(resultset.getString("street_name"));
			createjob.setCustomer_address__landmark(resultset.getString("landmark"));
			createjob.setCustomer_address__area(resultset.getString("area"));
			createjob.setCustomer_address__pincode(resultset.getString("pincode"));
			createjob.setCustomer_address__country(resultset.getString("country"));
			createjob.setCustomer_address__state(resultset.getString("state"));

			createjob.setCustomer_product__serial_number(resultset.getString("serial_number"));
			createjob.setCustomer_product__imei1(resultset.getString("imei1"));
			createjob.setCustomer_product__imei2(resultset.getString("imei2"));
			createjob.setCustomer_product__popurl(resultset.getString("popurl"));
			createjob.setCustomer_product__dop(resultset.getString("dop"));

			createjob.setCustomer_product__mst_model_id("1");

			createjob.setProblems__id(resultset.getString("mst_problem_id"));
			createjob.setProblems__remark(resultset.getString("remark"));
			createjob.setCustomer_product__product_id("1");
			beanlist.add(createjob);
			
		}
		
		for(CreateJobBean b:beanlist) {
			System.out.println(b);
		}
		return beanlist;
	}

}
