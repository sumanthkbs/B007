package generic;

import java.io.IOException;


import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

	public static final String XL_PATH="./data/input.xlsx";
	public static final String CONFIG_PATH="./config.properties";
	public static ExtentReports extent;
	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest test;

	@BeforeSuite 
	public void initReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
		extent.attachReporter(spark);
	}

	@AfterSuite
	public void generateReport() {
		extent.flush();
	}

	@Parameters("property")
	@BeforeMethod
	public void preCondition(Method method,@Optional(CONFIG_PATH) String property) throws MalformedURLException {
		String testName = method.getName();
		test = extent.createTest(testName);

		String grid=Utility.getProperty(CONFIG_PATH,"GRID");
		String grid_url=Utility.getProperty(CONFIG_PATH,"GRID_URL");
		String browser = Utility.getProperty(CONFIG_PATH,"BROWSER");
		String appURL = Utility.getProperty(CONFIG_PATH,"APP_URL");
		String ITO=Utility.getProperty(CONFIG_PATH,"ITO");
		String ETO=Utility.getProperty(CONFIG_PATH,"ETO");
		if(grid.equalsIgnoreCase("yes"))
		{
			
			if(browser.equalsIgnoreCase("Chrome"))
			{test.info("open chromebrowser in remote system"+browser);
			driver=new RemoteWebDriver(new URL(grid_url), new ChromeOptions());
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				test.info("open FireFoxbrowserin remote system"+browser);
				driver=new RemoteWebDriver(new URL(grid_url), new FirefoxOptions());
			}
			else
			{
				test.info("open Edgebrowserin remote system"+browser);
				driver=new RemoteWebDriver(new URL(grid_url), new EdgeOptions());
			}
		}
		else
		{
		
		if(browser.equalsIgnoreCase("Chrome"))
		{test.info("open chromebrowser in local system"+browser);
		driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			test.info("open FireFoxbrowserin local system"+browser);
			driver = new FirefoxDriver();
		}
		else
		{
			test.info("open Edgebrowserin local system"+browser);
			driver = new EdgeDriver();
		}
		}
		test.info("Enter the url"+appURL);
		driver.get(appURL);

		test.info("Maximize");
		driver.manage().window().maximize();

		test.info("Set implicit Timeout"+ITO);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ITO)));
		
		test.info("Set explicit Timeout"+ETO);
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ETO)));

	}

	@AfterMethod
	public void postCondition(ITestResult testResult) throws IOException {

		String testName = testResult.getName();
		int status=testResult.getStatus();
		if(status==1)
		{
			test.pass("the test is passed");
		}
		else
		{
			String timeStamp = Utility.getTimeStamp();
			String image="target/"+testName+timeStamp+".png";
			
			
			Utility.takeScreenshot(driver,image);
			String attach=testName+timeStamp+".png";
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath(attach).build());
			test.fail("the test is failed");
		}
		test.info("Close the browser");
		driver.quit();
	}
}
