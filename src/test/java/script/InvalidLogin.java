package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.LoginPage;

public class InvalidLogin extends BaseTest {
@Test(priority=2)
public void testInvalidLogin()
{
	//Get Test Data
	String un = Utility.getXLCellData(XL_PATH, "InvalidLogin", 1, 0);
	String pw = Utility.getXLCellData(XL_PATH, "InsvlidLogin", 1, 1);
//	1.enter invalid username
	LoginPage loginpage=new LoginPage(driver);
	loginpage.setUserName("abc");
	test.info("enter invalid username");
//	2.enter invalid password
	loginpage.setPassword("123");
	test.info("enter invalid Password");
//	3.clock on go button
	loginpage.clickGoButton();
	  test.info("click on login");
//	4.should display errr msg
	boolean result = loginpage.verifyErrMsgIsDisplayed(wait);
		Assert.assertTrue(result);
		   test.info("ErrMsg is displayed");
}
}
