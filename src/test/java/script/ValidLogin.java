package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import page.HomePage;
import page.LoginPage;

public class ValidLogin extends BaseTest {
@Test(priority=1)
	public void testValidLogin()
	{
//		1.enter valid un
         test.info("enter valid username");
	    LoginPage loginpage=new LoginPage(driver);
	    loginpage.setUserName("admin");
//		2.enter valid pw
	    test.info("enter valid password");
	    loginpage.setPassword("pointofsale");
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
