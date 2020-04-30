package com.test.automation.selenium.framework;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class logResult implements IAnnotationTransformer{

	public static int testPassCount = 0;
	public static int testFailCount = 0;
	static OutputStream htmlfile = null;
	static PrintStream printhtml = null;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	/*public  logResult(String strTestCaseName) {
			String Path = System.getProperty("user.dir") + "/" + Environment.resultDir + "/AutomationReport.html";
			System.out.println(Path);
			extent = new ExtentReports(Path, true);
			//startTest(strTestCaseName);
	}*/
	
	public  logResult(String strTestSuiteName) {
		String Path = System.getProperty("user.dir") + "/" + Environment.resultDir + "/AutomationReport_" + strTestSuiteName + ".html";
		System.out.println(Path);
		extent = new ExtentReports(Path, false);
		//startTest(strTestCaseName);
}
	
	public  void startTest(String testName) {
		test = extent.startTest(testName);
		
	}

	public  void logTest(String name1, String name2, String name3, String name4, String name5, String name6) {

		System.out.println(
				"output ->" + name1 + "<->" + name2 + "<->" + name3 + "<->" + name4 + "<->" + name5 + "<->" + name6);
		String dtls;
		if (name4.isEmpty())
			dtls = name5;
		else
			dtls = name4 + "::" + name5;

		switch (name3.toUpperCase()) {
		case "PASS":
			test.log(LogStatus.PASS, name1 + "::" + name2, dtls);
			break;

		case "FAIL":
			test.log(LogStatus.FAIL, name1 + "::" + name2,
					dtls + ":: Screenshot: <span style='font-weight:bold;'><a target = \"_blank\" href=\".\\Screenshots\\" + name6 + "\">" + name6 + "</a></span>");
			break;

		case "INFO":
			test.log(LogStatus.INFO, name1 + "::" + name2, dtls);
			break;
		}
	}

	public  void endTest() {
		this.extent.endTest(this.test);
		extent.flush();
	}

	public void closeReport() {
		extent.close();
	}

	public static String CaptureScreen(WebDriver driver, String ImagesPath) {
		TakesScreenshot oScn = (TakesScreenshot) driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File oDest = new File(ImagesPath + ".jpg");
		try {
			FileUtils.copyFile(oScnShot, oDest);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return ImagesPath + ".jpg";
	}
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod){
		
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
	
}
