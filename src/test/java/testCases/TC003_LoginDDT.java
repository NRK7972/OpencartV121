package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="DataDriven")
	public void verify_loginDDT(String email, String pwd, String exp) {

		logger.info("***TC003_LoginDDT tc is starting*?***");

		// Homepage
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			// Account Page
			MyAccountPage mac = new MyAccountPage(driver);
			boolean targetpage = mac.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetpage == true) {
					mac.clickLogout();
					Assert.assertTrue(true);
				}
			}
			if (exp.equalsIgnoreCase("InValid")) {
				if (targetpage == true) {
					mac.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();

		}
		logger.info("***TC003_LoginDDT tc is Completed*?***");

	}

}
