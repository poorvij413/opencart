package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

	
	public HomePage(WebDriver driver) {
		super(driver);		
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement myaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement register;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement login;
	
	public void clickMyAccount() {
		myaccount.click();
	}
	
	public void clickRegister() {
		register.click();
	}
	
	public void clickLogin() {
		login.click();
	}
	
}