package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.TestBase;

public class LoginPage extends TestBase {
	int WAIT_TIME=15;
	@FindBy(xpath=".//*[@id='loginForm']/div/input[1]")
	WebElement username;
	
	@FindBy(xpath=".//*[@id='loginForm']/div/input[2]")
	WebElement password;
	
	@FindBy(xpath=".//*[@id='loginForm']/div/div/input[@type='submit']")
	WebElement loginBtn;
			
	@FindBy(xpath="//img[@class='img-responsive' and contains(@src,'logo.png')]")
	WebElement crmprologo;	
	
	/* initialize the web elements  page objects using constructor */
	
	public LoginPage() {
		PageFactory.initElements(driver, this);//in this constructor all variables initialized with driver
	}
	//actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmprologo.isDisplayed();
	}
	
	public HomePage login(String un,String pwd) {
		WebDriverWait wait =new WebDriverWait(driver,WAIT_TIME);
		wait.until(ExpectedConditions.elementToBeClickable(username));
		username.sendKeys(un);
		password.sendKeys(pwd);
		//loginBtn.click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",loginBtn);
		return new HomePage();//returning home page object as after clicking login button we goto homepage that is landing page
	}

	
}
