package TestCases;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.TestBase;
import Pages.ContactsPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.TestDataFetchXL;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;

	
	@BeforeTest
	public void setUp() throws IOException {
		String username=configFileReader.getPropertyValue("username");
		String password=configFileReader.getPropertyValue("password");
		 loginPage=new LoginPage();	
		 homePage=loginPage.login(username, password);
		 contactsPage=homePage.clickOnContactsLink();
	}
	/*getting data from hashmap into object of dataprovider*/
	@DataProvider(name = "getContactsDataMap")
	public Object[][] getCRMTestData() throws Exception {
		Object data[][]=TestDataFetchXL.getTestDataMap("ContactsMap");
		return data;
	}
	
	@Test(dataProvider = "getContactsDataMap",priority=4)
	public void CreateNewContact(Map<Object,Object> mapdata) throws IOException {		
		testcaseName="CreateNewContact";
		testcaseDescription="to  CreateNewContact";	
	try{		String firstname=(String) mapdata.get("FirstName");
				String lastname=(String) mapdata.get("LastName");
				String company=(String) mapdata.get("Company");
	
			contactsPage.createNewContact(firstname,lastname,company);
			//extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"PASS","Created New Contact "+firstname+" "+lastname);
		extentGenerateReport.extentTestWithNodes(testcaseName,testcaseDescription,"Create New Contact "+firstname+" "+lastname,"PASS","Created New Contact "+firstname+" "+lastname);
				Reporter.log("Created New Contact "+firstname+" "+lastname,true);
		}
	catch (Exception e) {			
				//extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"ERROR",e.getMessage());
		extentGenerateReport.extentTestWithNodes(testcaseName,testcaseDescription,"Create New cotact","ERROR",e.getMessage());
				Reporter.log(e.getMessage(),true);
			}
	}
	
	
	/* use testdat from 2 2d object of dataprovider
	 @DataProvider(name = "getContactsData")
	public Object[][] getCRMTestData() throws Exception {
		Object data[][]=TestDataFetchXL.getTestData("Contacts");
		return data;
	}
	
	@Test(dataProvider = "getContactsData",priority=4)
	public void CreateNewContact(String firstname,String lastname,String company) throws IOException {		
		testcaseName="CreateNewContact";
		testcaseDescription="to  CreateNewContact";	
	try{			
			contactsPage.createNewContact(firstname,lastname,company);
			extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"PASS","Created New Contact "+firstname+" "+lastname);
				Reporter.log("Created New Contact "+firstname+" "+lastname,true);
		}
	catch (Exception e) {			
				extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"ERROR",e.getMessage());
				Reporter.log(e.getMessage(),true);
			}
	}*/
	
	/*@Test(priority=4)
	public void CreateNewContact() throws IOException {
		
		testcaseName="CreateNewContact";
		testcaseDescription="to  CreateNewContact";	
	try{	
		
		contactsPage.createNewContact("Mr.", "Samuel", "Ray", "Simon");
			extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"PASS","Created New Contact");
				Reporter.log("contactspage label is displayed",true);
		}
	catch (Exception e) {			
				extentGenerateReport.extentReportTest(testcaseName,testcaseDescription,"ERROR",e.getMessage());
				Reporter.log(e.getMessage(),true);
			}
	}
	*/
/*	@Test(priority=2)
	public void singleSelectContactsTest() {
		contactsPage.selectContacts("test2 test2");
		Assert.assertTrue(contactsPage.isContactsCheckboxSelected(), "contacts checkbox not selected");
		
	}
	
	@Test(priority=3)
	public void multipleSelectContactsTest() {
		contactsPage.selectContacts("ui uiii");
		Assert.assertTrue(contactsPage.isContactsCheckboxSelected(), "contacts checkbox not selected");
		contactsPage.selectContacts("John Wick");
		Assert.assertTrue(contactsPage.isContactsCheckboxSelected(), "contacts checkbox not selected");
		
	}
	
	
	
	@Test(priority=4,dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String fname, String lname, String comp) {
		homePage.clickOnNewContactsLink();
		contactsPage.createNewContact(title, fname, lname, comp);
	}*/
}
