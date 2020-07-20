package TestCases;
import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.TestBase;
import Pages.HomePage;
import Pages.LoginPage;


public class LoginPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeTest
	public void setUp() {
		 loginPage=new LoginPage();	
		// homePage=new HomePage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest() throws IOException {
		
		testcaseName="loginPageTitleTest";
		testcaseDescription="to check CRM login  Page title";		
		String title=loginPage.validateLoginPageTitle();	
		try{
			if(title.equalsIgnoreCase("CRMPRO - CRM software for customer relationship management, sales, and support."))
			{
				testStatus="PASS";
				testStatusDescription="CRM login page title is correct";				
				extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,testStatus,testStatusDescription);
				Reporter.log(testStatusDescription,true);
			}
			else
			{				
			testStatus="FAIL";
			testStatusDescription="CRM login page title is incorrect";			
			extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,testStatus,testStatusDescription);
			Reporter.log(testStatusDescription,true);
			}
		}
		catch (Exception e) {
			
			testStatus="ERROR";
			testStatusDescription=e.getMessage();			
			extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,testStatus,testStatusDescription);
			Reporter.log(testStatusDescription,true);
		}
	}
	
	@Test(priority=2)
	public HomePage loginTest() throws IOException {
		testcaseName="loginTest";
		testcaseDescription="to verify log into CRM site is successful";
		
		try{
		String username=configFileReader.getPropertyValue("username");
		String password=configFileReader.getPropertyValue("password");
		homePage=loginPage.login(username, password);
		testStatus="PASS";
		testStatusDescription="log into CRM site done successfully";
		extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,testStatus,testStatusDescription);
		Reporter.log(testStatusDescription,true);
		}catch (Exception e) {
			testStatus="ERROR";
			testStatusDescription=e.getMessage();
			extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,testStatus,testStatusDescription);
			Reporter.log(testStatusDescription,true);
		}
	return homePage; 
	}
}
