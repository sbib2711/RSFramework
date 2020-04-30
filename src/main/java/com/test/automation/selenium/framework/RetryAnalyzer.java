package com.test.automation.selenium.framework;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	
	int counter = 0;
	int retryLimit = 2;
	
public boolean retry(ITestResult result){
		
	if(!result.isSuccess()){
		
		if (counter < retryLimit){

			counter++;
			result.setStatus(ITestResult.FAILURE);
			return true;
		}
		else{
			result.setStatus(ITestResult.FAILURE);
		}
		
	}
	
	else{
		
		result.setStatus(ITestResult.SUCCESS);
	}
		

	return false;

	}

}
