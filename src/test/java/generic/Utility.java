package generic;

import java.io.File;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	public static String getXLCellData(String xlpath,String sheetname,int row,int col)
	{
		 String data = "";
		try
		{Workbook wb = WorkbookFactory.create(new FileInputStream(xlpath));

		Sheet sheet1 = wb.getSheet(sheetname);
		

		 data = sheet1.getRow(row).getCell(col).toString();
		System.out.println(data);
		
		wb.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}
	
	public static String getTimeStamp()
	{
		LocalDateTime n=LocalDateTime.now();
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd_MM_YYYY_hh_mm_ss");
		String timeStamp=n.format(format);
		return timeStamp;
	}

	public static void takeScreenshot(WebDriver driver,String path)
	{
		TakesScreenshot t=(TakesScreenshot)driver;
		File srcfile = t.getScreenshotAs(OutputType.FILE);
		File dstfile=new File(path);
		try {
		FileUtils.copyFile(srcfile, dstfile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static String getProperty(String CONFIG_PATH,String key)
	{
		String value="";
		Properties p=new Properties();
		
		
		try
		{
			p.load(new FileInputStream(CONFIG_PATH));
			 value = p.getProperty(key);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return value;
	}


}
