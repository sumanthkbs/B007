package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.HomePage;
import page.LoginPage;

public class ValidLogin extends BaseTest {
@Test(priority=1)
	public void testValidLogin()
	{
	//Get Test Data
	String un = Utility.getXLCellData(XL_PATH, "ValidLogin", 1, 0);
	String pw = Utility.getXLCellData(XL_PATH, "ValidLogin", 1, 1);
//		1.enter valid un
         test.info("enter valid username");
	    LoginPage loginpage=new LoginPage(driver);
	    loginpage.setUserName(un);
//		2.enter valid pw
	    test.info("enter valid password");
	    loginpage.setPassword(pw);
//		3.click on login
	    test.info("click on login");
	    loginpage.clickGoButton();
//		4.homepage should be displayed
	    test.info("homepage is displayed");
	    HomePage homepage=new HomePage(driver);
	    boolean result = homepage.verifyHomePageIsDisplayed(wait);
	    Assert.assertTrue(result);
	}
}
