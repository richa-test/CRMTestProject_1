package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.TestBase;

public class ContactsPage extends TestBase {

WebElement contactsCheckbox;
int WAIT_TIME=15;	
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	/**@FindBy(xpath="//a[text()='test2 test2']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")
	WebElement checkbox;	
	this is not right by hard coding and we cannot define so many page factory for all elements,
	so instead of test2 test2 hard code use below
		**/
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement lastName;
	
	@FindBy(name="client_lookup")
	WebElement company;

	
	@FindBy(xpath="//input[@value='Save' and @type='submit']")
	WebElement saveBtn;
	
	//Initialize page object
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyContactsLabel() {
		WebDriverWait wait =new WebDriverWait(driver,WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOf(contactsLabel));
		return contactsLabel.isDisplayed();
		
	}
	
	public void selectContacts(String contactname) {
		contactsCheckbox=driver.findElement(By.xpath("//a[text()='"+contactname+"']//parent::td[@class='datalistrow']"
				+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']"));
		contactsCheckbox.click();
		
	}
	
	public boolean isContactsCheckboxSelected() {
		return  contactsCheckbox.isSelected();
	}
	
	public void createNewContact(String fname,String lname,String comp) {
		//title is a drop down (select)-use Select class
	/*	Select select=new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);*/
		/*		WebDriverWait wait =new WebDriverWait(driver,WAIT_TIME);
	wait.until(ExpectedConditions.elementToBeClickable(firstName));*/
		
		driver.switchTo().frame("mainpanel");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Contacts']"))).build().perform();
	/*	driver.findElement(By.xpath("//a[@title='New Contact']")).click();*/
		
		driver.findElement(By.xpath("//*[@id='navmenu']//a[@title='New Contact']")).click();
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		company.sendKeys(comp);
		saveBtn.click();
		
		driver.switchTo().defaultContent();
		
	}
}


