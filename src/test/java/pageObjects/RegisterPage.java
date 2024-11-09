package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegisterPage extends BasePage {

	public RegisterPage(WebDriver driver) {
		super(driver);		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtfirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtlastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemailId;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txttelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtconfirmpass;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement checkpolicyAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continuebtn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement succesmesage;
	
	public void setFirstName(String fname) {
		txtfirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtlastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtemailId.sendKeys(email);
	}
	
	public void setTelephone(String teleph) {
		txttelephone.sendKeys(teleph);
	}
	
	public void setPassword(String pass) {
		txtpassword.sendKeys(pass);
	}
	
	public void setConfirmPass(String pass) {
		txtconfirmpass.sendKeys(pass);
	}
	
	public void acceptPolicy() {
		checkpolicyAgree.click();;
	}
	
	public void continueClick() {
		//continuebtn.click();
		continuebtn.submit();
	}
	
	public String getConfirmMessage() {
		try{
			String message=succesmesage.getText();
			return(message);
		}
		catch(Exception e) {
			return (e.getMessage());
		}
	}
}
