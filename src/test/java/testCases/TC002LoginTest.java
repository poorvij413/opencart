package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		logger.info("Starting TC002LoginTestt");
		
		try {
		//homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//loginpage
		LoginPage lp=new LoginPage(driver);
		lp.setEmailID(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		
		lp.clickLogin();
		
		//myaccount
		MyAccountPage ma=new MyAccountPage(driver);
		boolean targetPage=ma.isMyAccountExist();
		//Assert.assertEquals(targetPage,true, "Login FAILED");
		Assert.assertTrue(targetPage);	
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("Finished TC002LoginTestt");
	}
}
