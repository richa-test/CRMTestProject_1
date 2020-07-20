package Utilities;

import java.io.IOException;

import Base.TestBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utilities.CaptureScreenShot;

public class ExtentReportGenerate  extends TestBase{
	public static ExtentHtmlReporter htmlReports;
	public static ExtentReports extent;
	public ExtentTest parentTest;
	public static ExtentTest parentTest2;
	public ExtentTest childtest;
	public static String createdTest;
	
	//@SuppressWarnings("rawtypes")
	//private static ThreadLocal parentTest = new ThreadLocal();
    //@SuppressWarnings("rawtypes")
	//private static ThreadLocal test = new ThreadLocal();
	CaptureScreenShot captureScreenShot=new CaptureScreenShot();
	public String failedscreenshot;

	public  static ExtentReports setUp(String fileName, String reportName, String reportTitle) {

		htmlReports = new ExtentHtmlReporter(fileName);
		htmlReports.config().setReportName(reportName);
		htmlReports.config().setTheme(Theme.DARK);
		htmlReports.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReports.config().setDocumentTitle(reportTitle);
		htmlReports.setAppendExisting(true);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReports);
		return extent;
		
	}
	

	
	//public ExtentTest extentReportTest 
	public void extentReportTest (String testcaseName,String testcaseDescription ,String testStatus,String testStatusDescription) throws IOException
	{
	
		parentTest = extent.createTest(testcaseName,testcaseDescription); 
		if (testStatus.equals("PASS")) {
		
			parentTest.log(Status.PASS, testStatusDescription);
			} else if (testStatus.equals("FAIL")) {
				failedscreenshot=captureScreenShot.getScreenShot(driver,testcaseName);
				parentTest.log(Status.FAIL, testStatusDescription, MediaEntityBuilder.createScreenCaptureFromPath(failedscreenshot).build());
				
			/*	parentTest.log(Status.FAIL, testStatusDescription);
				parentTest.addScreenCaptureFromPath(failedscreenshot);
				*/
			} else if (testStatus.equals("SKIP")) {
				parentTest.log(Status.SKIP, testStatusDescription);
			}
			else if (testStatus.equals("INFO")) {
				parentTest.log(Status.INFO, testStatusDescription);
			}
			else if (testStatus.equals("ERROR")) {
				parentTest.log(Status.ERROR, testStatusDescription);
			}
		//return parentTest;
	}
	
	/*public ExtentTest extentCreateParentTest (String testcaseName,String testcaseDescription ) throws IOException
	{
		parentTest = extent.createTest(testcaseName,testcaseDescription); 
		return parentTest;
	}
	*/
	
	public void extentTestWithNodes (String testcaseName,String testcaseDescription,String testStep,String testStatus,String testStatusDescription ) throws IOException
	{
		if (!testcaseName.equalsIgnoreCase(createdTest)){
			parentTest2 = extent.createTest(testcaseName,testcaseDescription); 
			createdTest=testcaseName;
		}
		childtest = parentTest2.createNode(testStep); 
		//parentTest = extent.createTest(testcaseName,testcaseDescription); 
		if (testStatus.equals("PASS")) {
		
			childtest.log(Status.PASS, testStatusDescription);
			} 
		  else if (testStatus.equals("FAIL")) {
				failedscreenshot=captureScreenShot.getScreenShot(driver,testcaseName);
				childtest.log(Status.FAIL, testStatusDescription, MediaEntityBuilder.createScreenCaptureFromPath(failedscreenshot).build());
				
				//parentTest.log(Status.FAIL, testStatusDescription);
				//parentTest.addScreenCaptureFromPath(failedscreenshot);
				
			}
	else if (testStatus.equals("SKIP")) {
		childtest.log(Status.INFO, testStatusDescription);
			}
		
	}
	
	public void tearDown() {
		extent.flush();
	}
}
