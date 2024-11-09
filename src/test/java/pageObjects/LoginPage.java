package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);		
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmailId;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnlogin;
	
	public void setEmailID(String email) {
		txtEmailId.sendKeys(email);
	}
	
	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void clickLogin() {
		btnlogin.click();
	}
}
