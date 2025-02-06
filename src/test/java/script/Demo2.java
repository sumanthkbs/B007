package script;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;

public class Demo2 extends BaseTest{

	@Test
	public void testA()
	{
	

		String data = Utility.getXLCellData(XL_PATH,"sheet1", 0, 0);
		test.info("data from excel"+data);
		String title = driver.getTitle();
		System.out.println(title);
		test.info("testA"+title);
		
	}
}
