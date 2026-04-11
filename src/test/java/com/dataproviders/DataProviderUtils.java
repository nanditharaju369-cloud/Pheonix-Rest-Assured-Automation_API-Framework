package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSV_ReaderUtility;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	//if im not giving a name to the data provider then methodname becomes the name of DP
	@DataProvider(name="LoginAPIDP", parallel=true)//to run in parallel--all test data
	public static Iterator<UserBean> logindataprovider() {
		return CSV_ReaderUtility.LoadCSV("Test_Data/loginCred.csv");
	}
}
