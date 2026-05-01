package com.database.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.api.Record_Models.CreateJob_Payload;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DAORunner {

	public static void main(String[] args) throws SQLException, IOException {
		List<CreateJobBean>beanlist=  CreateJobPayloadDataDAO.getCreateJobPayloadData();
		List<CreateJob_Payload> payloadlist=new ArrayList<CreateJob_Payload>();
for(CreateJobBean createjobbean:beanlist) {
	CreateJob_Payload payload=CreateJobBeanMapper.mapper(createjobbean);
	payloadlist.add(payload);
}

for(CreateJob_Payload payload:payloadlist) {
	System.out.println(payload);
}
	}

}
