package script;

import org.jspecify.annotations.Nullable;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;

public class Demo3 extends BaseTest{

	@Test
	public void testB()
	{
	
		String title = driver.getTitle();
		System.out.println(title);
		test.info("testB"+title);
	}
}

