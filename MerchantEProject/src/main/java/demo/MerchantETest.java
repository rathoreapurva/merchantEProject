package demo;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import helper.BaseClass;
import helper.ExcelDataProvider;
import helper.Utils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.MerchantEUserCredential;
import pojo.MerchantEUsers;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.tools.sjavac.Log;

public class MerchantETest extends BaseClass
{
	public static ExtentReports	reports;
	public String path;
	public static Properties prop;

	@BeforeTest
	public void initialize()
	{

		String path =System.getProperty("user.dir")+"\\reports\\index.html";
		reports = new ExtentReports(System.getProperty("user.dir")+"\\reports\\ExtentReportResults.html",true);
		logger = reports.startTest("MerchantETest");		
	}
	
	@Test(enabled=true)
	public void loginUser()
	{
		logger.log(LogStatus.INFO, "Login User Test case execution started");
		MerchantEUserCredential user= new MerchantEUserCredential();
		user.setEmail(excel.getStringData("userLogin",1,0));
		user.setPassword(excel.getStringData("userLogin",1,1));		
		
		RequestSpecification reqUser=given().spec(req).body(user);		
		logger.log(LogStatus.INFO, "Post Request created successfuly");
		Response responseUser =reqUser.when().post("api/register").
				then().spec(res).log().all().
				extract().response();
		logger.log(LogStatus.INFO, "Response received successfuly");
		//to remove		
		String responseString=responseUser.asString();
		System.out.println(responseString);		
				
		Assert.assertEquals(200, responseUser.getStatusCode());	
		if(responseUser.getStatusCode()==200) {
		logger.log(LogStatus.INFO, "user is successfuly logged in");
		logger.log(LogStatus.PASS, "Test Case is passed");
		}
		else
		{
			logger.log(LogStatus.FAIL, "Test Case is Failed");	
			
		}
		}
	
	@Test(enabled=true)
	public void getUserById()
	{
		RequestSpecification reqUser=given().spec(req);		
		logger.log(LogStatus.INFO, "Request is created successfuly");
		Response responseUser =reqUser.when().get("api/users/2").
				then().spec(res).log().all().
				extract().response();
		logger.log(LogStatus.INFO, "Response received successfuly");
		String responseString= responseUser.asString();
		JsonPath js= new JsonPath(responseString);
		Assert.assertEquals(2, js.getInt("data.id"));
		if(js.getInt("data.id")==2)
		{
		logger.log(LogStatus.INFO, "User data is received successfuly");
		logger.log(LogStatus.PASS, "Test Case is passed");	
		}
		else
		{
			logger.log(LogStatus.PASS, "Test Case is failed");	
			
		}
	}
	
	@Test(dataProvider="userData", enabled=true)
	public static void PostAddUsers(String name, String job) throws IOException	
	{ 	
		
		logger.log(LogStatus.INFO, "Add User Test case execution started");
		MerchantEUsers user= new MerchantEUsers();
		user.setName(name);
		user.setJob(job);		
		logger.log(LogStatus.INFO, "Post Request created successfuly");
		RequestSpecification reqUser=given().spec(req).body(user);		

		Response responseUser =reqUser.when().post("api/users").
				then().spec(res).log().all().
				extract().response();
		logger.log(LogStatus.INFO, "Response received successfuly");
		Assert.assertEquals(201, responseUser.getStatusCode());
        logger.log(LogStatus.INFO, "User is added successfully");
        logger.log(LogStatus.PASS, "Test case is passed");
       
	}
	
	@DataProvider(name="userData")
	public Object[][] getUserData()
	{
		return new Object[][]
				{
			{"Apurva","Senior Software Engineer"},
			{"Aditya", "Developer"},
			{"Amit", "Manager"}			
			};
		
	}
	
	@AfterClass
	public void tearDown()
	{
		
		reports.endTest(logger);
		
		reports.flush();
		reports.close();
	}

}
