package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import Utilities.CaptureScreenShot;
import Utilities.ConfigFileReader;
import Utilities.DateTime;
import Utilities.ExtentReportGenerate;


public class TestBase {
	
	public static Properties properties;
	public static WebDriver driver;
	public static String Test_Environment; 
	public static String url;
	public String extentreportlocation = System.getProperty("user.dir") + "\\Reports";
	public  static String extentfilename;
	public ExtentTest  extentTest;
	public ExtentReportGenerate extentGenerateReport;
	public ExtentReports extentReport;
	public DateTime dt = new DateTime();
	public ConfigFileReader configFileReader = new ConfigFileReader();
	public String browserName;
	public String testcaseName;
	public String testcaseDescription ;
	public String testStatus;
	public String testStatusDescription;

	@BeforeSuite
	public void  setUpSuite() {
		//environment is qa or dev
				Test_Environment = configFileReader.getPropertyValue("TestEnvironment");
				
					
				if (Test_Environment.equalsIgnoreCase("QA")) {
					url =configFileReader.getPropertyValue("qa_url");
					System.out.println("Test environment is "+Test_Environment+" and url is "+url);
				}
					
				else if (Test_Environment.equalsIgnoreCase("DEV")) {
						url =configFileReader.getPropertyValue("dev_url");
						System.out.println("Test environment is "+Test_Environment+" and url is "+url);
				}
				else {
					System.out.println("please check the environment in config");
				}
	
				extentfilename=extentreportlocation + "\\TestReport_" + dt.getDate(0) + ".html";
				System.out.println("extent report filename is "+extentfilename);
				extentReport=ExtentReportGenerate.setUp(extentfilename, "CRM Regression TestReport","CRMRegressionTestReport");
				
	}

	
	@BeforeTest
	public void initialization(){
	
		extentGenerateReport=new ExtentReportGenerate(); 
		//System.out.println("extent report filename is "+extentfilename);
		//extentReport=ExtentReportGenerate.setUp(extentfilename, "TakeaLot Regression TestReport","TakeaLotRegressionTestReport");
	
		
		//get browser
		browserName = configFileReader.getPropertyValue("browser");
	
		
		//browser driver
		if(browserName.equalsIgnoreCase("Chrome")) {
			System.out.println("Browser chrome");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\browserdrivers\\chromedriver_win32V83\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		if(browserName.equalsIgnoreCase("FF")) {
			System.out.println("Browser Firefox");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\browserdrivers\\geckodriver");
			driver=new FirefoxDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);		
		driver.get(url);
			
	}
/*	@AfterMethod
	public void amteardown(ITestResult result) throws IOException
	{ 	CaptureScreenShot captureScreenShot=new CaptureScreenShot();
		String failedscreenshot;
		Reporter.log("Name is "+ result.getName() +" testname is " + result.getTestName(),true);
		failedscreenshot=captureScreenShot.getScreenShot(driver,result.getTestName());
		extentTest.fail( result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(failedscreenshot).build());
	
	}
	*/
	@AfterTest
	public void teardown()
	{ 	
	driver.quit();
	extentGenerateReport.tearDown();
	
	}
	
/*	@AfterSuite
	public void extentteardown()
	{ 	
	
	extentTest.tearDown();
	}*/
}
