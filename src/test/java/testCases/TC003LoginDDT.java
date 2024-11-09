package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/*Data is valid --login sucsess--tc pass
 * Data is valid ---login fail--tc fail
 * Data is invalid--login success--tc fail
 * Data is invalid--login fail--tc pass
 */


public class TC003LoginDDT extends BaseClass {

	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")//getting dataprovider from different class
	public void verify_loginDDT(String email,String pwd,String exp) {
		logger.info("Started TC003");
		
		try {
		//homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//loginpage
		LoginPage lp=new LoginPage(driver);
		lp.setEmailID(email);
		lp.setPassword(pwd);
		
		lp.clickLogin();
		
		//myaccount
		MyAccountPage ma=new MyAccountPage(driver);
		boolean targetPage=ma.isMyAccountExist();
		//Assert.assertEquals(targetPage,true, "Login FAILED");
		//Assert.assertTrue(targetPage);	
		
		if(exp.equalsIgnoreCase("valid")) 
		{
			if(targetPage==true) {
				Assert.assertTrue(true);
				ma.clickLogout();
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid")) 
		{
			if(targetPage==true) {
				
				ma.clickLogout();
				Assert.assertTrue(false);
			}
			
			else {
				Assert.assertTrue(true);
			}
		}
   }
		
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("Finished TC003");
	}
	
}
