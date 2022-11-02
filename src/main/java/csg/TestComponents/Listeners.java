package csg.TestComponents;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class Listeners extends APIBaseClass implements ITestListener{
	
	private static ExtentReports extent;

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
//		PropertiesFileReader obj= new PropertiesFileReader();	
//		TestDataHandler testdata=new TestDataHandler();
//		Map<String,String> testData=testdata.getTestDataInMap();
//		
//		Properties properties;
//		try {
//			properties = obj.getProperty();
//			ExcelHandler.UpdateTestResultsToExcel( properties.getProperty("testdatafilepath"), 
//					properties.getProperty("sheetname"),"PASS",testData.get("TestCaseId"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  		
//		
		
		System.out.println("PASS");
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("FAIL");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("SKIP");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		System.out.println("Execution started on UAT env...");
		extent= setUp();
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("Execution completed on UAT env...");
		extent.flush();		
		System.out.println("Generated Report. . .");	
		
	}
	
	
	
	

}
