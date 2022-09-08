package com.app.qa.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer{
	
	int counter = 0;
	int retryLimit = 3;
	
	//overriding the retry in IRetryAnalyzer
	public boolean retry(ITestResult result) {
		if(counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}

}