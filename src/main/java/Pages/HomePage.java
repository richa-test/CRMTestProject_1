package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import Base.TestBase;

public class HomePage extends TestBase{

	int WAIT_TIME=15;
	/*@FindBy(xpath="//td[contains(text(),\"User: crmseltest crmseltest\")]")
	@CacheLookup //this stores the webelement in cache memory
	//to imporve speed instead of searching in dom everytime u call this elment
	//use only when refresh does not happen or dom changes
	 WebElement userNameLabel;
	 * */
	@FindBy(xpath="//td[contains(text(),\"User: crmseltest crmseltest\")]")
	WebElement userNameLabel;
	
/*	@FindBy(xpath="//a[contains(text(),\"Contacts\")]")
	WebElement contactsLink;
	*/
	@FindBy(xpath="//a[@title='Contacts']")
	WebElement contactsLink;
	
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactlink;
	
	@FindBy(xpath="//a[contains(text(),\"Deals\")]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),\"Tasks\")]")
	WebElement tasksLink;
	//initialize page objects using constructor
	
	public  HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
public ContactsPage clickOnContactsLink() {
	try{
	/*	WebDriverWait wait =new WebDriverWait(driver,WAIT_TIME);
		wait.until(ExpectedConditions.elementToBeClickable(contactsLink));*/
	Actions action=new Actions(driver);
	action.moveToElement(contactsLink).build().perform();
	contactsLink.click();
		//on clicking on the contacts link in homepage contacts page gets displayed
	}catch (Exception e){
		Reporter.log(e.getMessage(),true);
	}
	return new ContactsPage();
}
	
	public void clickOnNewContactsLink() {
		try{
			Actions action=new Actions(driver);
			action.moveToElement(contactsLink).build().perform();
			WebDriverWait wait =new WebDriverWait(driver,WAIT_TIME);
			wait.until(ExpectedConditions.elementToBeClickable(newContactlink));
			newContactlink.click();
		}catch (Exception e){
			Reporter.log(e.getMessage(),true);
		}
		
		
	}
	
	public DealsPage clickOnDealsLink() {
		WebDriverWait wait =new WebDriverWait(driver,WAIT_TIME);
		wait.until(ExpectedConditions.elementToBeClickable(dealsLink));
		dealsLink.click();
		return new DealsPage();//on clicking on the deals link deal page gets displayed.
	}

public String verifyUserName() {
	WebDriverWait wait =new WebDriverWait(driver,WAIT_TIME);
	wait.until(ExpectedConditions.visibilityOf(userNameLabel));
	return userNameLabel.getText();
	}

}
