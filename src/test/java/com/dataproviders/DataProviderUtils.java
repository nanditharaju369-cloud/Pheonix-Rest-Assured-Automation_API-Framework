package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.Record_Models.CreateJob_Payload;
import com.api.utils.CSV_ReaderUtility;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	//if im not giving a name to the data provider then methodname becomes the name of DP
	@DataProvider(name="LoginAPIDP", parallel=true)//to run in parallel--all test data
	public static Iterator<UserBean> logindataprovider() {
		return CSV_ReaderUtility.LoadCSV("Test_Data/loginCred.csv",UserBean.class);
	}
	
	@DataProvider(name="CreateJObDataProvider", parallel=true)
	public static Iterator<CreateJob_Payload> createjobdataprovider() {
		
		Iterator<CreateJobBean> createjobiterator=CSV_ReaderUtility.LoadCSV("Test_Data/CreateJobData.csv", CreateJobBean.class);
		List<CreateJob_Payload> payloadlist=new ArrayList<CreateJob_Payload>();
		CreateJobBean tempbean;
		CreateJob_Payload temppayload;
		while(createjobiterator.hasNext()) {
			tempbean=createjobiterator.next();
			temppayload=CreateJobBeanMapper.mapper(tempbean);
			payloadlist.add(temppayload);
			
		}
		return payloadlist.iterator();
		
	}
	
	@DataProvider(name="CreateJobAPIFakerDataProvider", parallel=true)
	public static Iterator<CreateJob_Payload> CreateJobFakeDataProvider() {
		String fakercount=System.getProperty("fakercount","5");
		int Fcount=Integer.parseInt(fakercount);
		Iterator<CreateJob_Payload> payloaditerator=FakerDataGenerator.Generatefakecreatejobdata(Fcount);
			return payloaditerator;
		
		
	}
	
	
	
	
	
	
}
