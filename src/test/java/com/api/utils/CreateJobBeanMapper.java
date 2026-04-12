package com.api.utils;

import java.util.ArrayList;
import java.util.List;

import com.api.Record_Models.CreateJob_Payload;
import com.api.Record_Models.Customer;
import com.api.Record_Models.CustomerAddress;
import com.api.Record_Models.CustomerProduct;
import com.api.Record_Models.Problems;
import com.dataproviders.api.bean.CreateJobBean;

public class CreateJobBeanMapper {

	private CreateJobBeanMapper() {

	}

	public static CreateJob_Payload mapper(CreateJobBean bean) {
		int mstservicelocationid = Integer.parseInt(bean.getMst_service_location_id());
		int mstplatformid = Integer.parseInt(bean.getMst_platform_id());

		int oemid = Integer.parseInt(bean.getMst_oem_id());
		int mstwarrantyid = Integer.parseInt(bean.getMst_warrenty_status_id());
		
		Customer customer=new Customer(
				bean.getCustomer__first_name(),
				bean.getCustomer__last_name(),
				bean.getCustomer__mobile_number(),
				bean.getCustomer__mobile_number_alt(),
				bean.getCustomer__email_id(),
				bean.getCustomer__email_id_alt());
		
		
		CustomerAddress address=new CustomerAddress(
				bean.getCustomer_address__flat_number(),
				bean.getCustomer_address__apartment_name(),
				bean.getCustomer_address__street_name(),
				bean.getCustomer_address__landmark(),
				bean.getCustomer_address__area(),
				bean.getCustomer_address__pincode(),
				bean.getCustomer_address__country(),
				bean.getCustomer_address__state());
		
		
		int productid=Integer.parseInt(bean.getCustomer_product__product_id());
		int modelid=Integer.parseInt(bean.getCustomer_product__mst_model_id());
		CustomerProduct customerproduct=new CustomerProduct(
				bean.getCustomer_product__dop(),
				bean.getCustomer_product__serial_number(),
				bean.getCustomer_product__imei1(),
				bean.getCustomer_product__imei2(),
				bean.getCustomer_product__popurl(),
				productid,modelid);
						
	
				List<Problems> problemlist=new ArrayList<Problems>();
				int problemid=Integer.parseInt(bean.getProblems__id());
				Problems problem=new Problems(problemid,bean.getProblems__remark());
				problemlist.add(problem);

		CreateJob_Payload payload = new CreateJob_Payload(mstservicelocationid, mstplatformid, mstwarrantyid, oemid,
				customer, address, customerproduct,problemlist);
		
		
		System.out.println("mst_service_location_id = " + bean.getMst_service_location_id());
		System.out.println("mst_platform_id = " + bean.getMst_platform_id());
		System.out.println("mst_oem_id = " + bean.getMst_oem_id());
		System.out.println("mst_warrenty_status_id = " + bean.getMst_warrenty_status_id());
		System.out.println("product_id = " + bean.getCustomer_product__product_id());
		System.out.println("model_id = " + bean.getCustomer_product__mst_model_id());
		System.out.println("problem_id = " + bean.getProblems__id());
		
		
		
		return payload;
	}
}
