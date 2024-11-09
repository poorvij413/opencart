package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.RegisterPage;

public class TC001AccountRegistrationTest extends BaseClass {
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
				
		logger.info("****Staring TC****");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MY account link");
		
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		RegisterPage rp=new RegisterPage(driver);
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastName(randomString().toUpperCase());
		rp.setEmail(randomString()+"@gmail.com");//randomly generate 
		rp.setTelephone(randomNumber());
		
		String pass=randomalphaNumeric();
		
		rp.setPassword(pass);
		rp.setConfirmPass(pass);
		
		rp.acceptPolicy();
		rp.continueClick();
		
		logger.info("Validating success mesaage ");
		String message =rp.getConfirmMessage();
		if(message.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		
		else {
			logger.error("fail");
			logger.debug("debug logs");
			Assert.assertTrue(false);
		}
		
			
	//	Assert.assertEquals(message, "Your Account Has Been Created!");
		}
		catch(Exception e) 
		{			
		
		Assert.fail();
		}
		
		logger.info("****Finished TC****");
	}
	
	
}
