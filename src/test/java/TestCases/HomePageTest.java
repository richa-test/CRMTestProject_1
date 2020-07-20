package TestCases;

import java.io.IOException;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.TestBase;
import Pages.ContactsPage;
import Pages.HomePage;
import Pages.LoginPage;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;


	
	@BeforeTest
	public void setUp() throws IOException {
		String username=configFileReader.getPropertyValue("username");
		String password=configFileReader.getPropertyValue("password");
		 loginPage=new LoginPage();	
		 homePage=loginPage.login(username, password);
		
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() throws IOException {
		
		testcaseName="HomePageTitleTest";
		testcaseDescription="to check CRM Home Page title";	
		
		String homePageTitle=homePage.verifyHomePageTitle();
		Reporter.log("HomePageTitle is " +homePageTitle,true);
		try{
			if(homePageTitle.equalsIgnoreCase("CRMPRO"))
			{
			extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"PASS","CRM Home Page Title is correct");
			Reporter.log("CRM Home Page Title is correct",true);
			}else{				
			extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"FAIL","CRM Home Page Title is incorrect");
			Reporter.log("CRM Home Page Title is incorrect",true);
			}
		}catch (Exception e) {			
			extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"ERROR",e.getMessage());
			Reporter.log(e.getMessage(),true);
		}
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() throws IOException {
		testcaseName="UserNameTestTest";
		testcaseDescription="to check UserName in CRM Home Page";	
		try{
				driver.switchTo().frame("mainpanel");
				String UserName=homePage.verifyUserName();
				Reporter.log("UserName in Home page is" +UserName,true);
				if(UserName.equalsIgnoreCase(configFileReader.getPropertyValue("User")))
				{
				extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"PASS","UserName in CRM Home Page is correct");
				Reporter.log("UserName in CRM Home Page is correct",true);
				}else{				
				extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"FAIL","UserName in CRM Home Page is incorrect");
				Reporter.log("UserName in CRM Home Page is incorrect",true);
				}
			}catch (Exception e) {			
				extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"ERROR",e.getMessage());
				Reporter.log(e.getMessage(),true);
			}
	}
	
	@Test(priority=3)
	public void ContactsLinkTest() throws IOException {
		testcaseName="verifyContactsLinkTest";
		testcaseDescription="to check ContactsLink can be clicked and Contact page returned";
		Reporter.log("verifyContactsLinkTest",true);
		try{	Reporter.log("verifyContactsLinkTest begins",true);
				driver.switchTo().frame("mainpanel");
				contactsPage=homePage.clickOnContactsLink();
				extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"PASS","ContactsLink clicked");
				Reporter.log("ContactsLink clicked",true);
			}catch (Exception e) {			
				extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"ERROR",e.getMessage());
				Reporter.log(e.getMessage(),true);
			}
		
	}
		
}
