package script;

import org.jspecify.annotations.Nullable;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;

public class Demo2 extends BaseTest{

	@Test
	public void testA()
	{
	
		
		String title = driver.getTitle();
		System.out.println(title);
		test.info("testA"+title);
		
	}
}
