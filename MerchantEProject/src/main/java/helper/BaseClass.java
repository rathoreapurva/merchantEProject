package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClass {
	public static RequestSpecification req;
	public static ResponseSpecification res;
	
	public static Utils utility;
	public static ExcelDataProvider excel ;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static Properties prop;
	
	@BeforeClass
	public static  void setUp() throws IOException
	{ 
		prop= configDataProvider();
		String baseURL= prop.getProperty("baseURL");
		excel= new ExcelDataProvider();		
        req= new RequestSpecBuilder()
    	   .setBaseUri(baseURL)
		   .setContentType(ContentType.JSON)		   
	       .build();
      
		res= new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.build();	
		
		
	}
	
	
@BeforeClass

	  static Properties configDataProvider() throws IOException
	{
		FileInputStream src= new FileInputStream(System.getProperty("user.dir")+"\\resource\\ApiResources.config");
		Properties prop= new Properties();
		prop.load(src);
		
		return prop;
		
		
		
		
		 
	}
}
