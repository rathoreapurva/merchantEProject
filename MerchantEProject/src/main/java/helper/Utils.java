package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ReporterConfig.Property;

import com.relevantcodes.extentreports.ExtentReports;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utils {

	public static ExtentReports extent;
	
	
	 void configDataProvider() throws IOException
	{
		FileInputStream src= new FileInputStream(System.getProperty("user.dir")+"\\resources\\ApiResources.config");
		Properties prop= new Properties();
		prop.load(src);
		
		
		 
	}

}
