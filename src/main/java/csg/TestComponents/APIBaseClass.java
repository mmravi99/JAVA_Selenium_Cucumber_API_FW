package csg.TestComponents;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import io.cucumber.java.Scenario;


public class APIBaseClass {

	public FileInputStream fis;
	public static Properties prop;
	public static ExtentHtmlReporter report = null;
	public static ExtentReports extent = null;
	public ExtentTest test = null;
	public static String dirName=null;
	public static String baseURI=null;
	public static String token=null;
	public static String username=null;
	public static String password=null;
	public 	static Scenario scenario;
	public static ThreadLocal<ExtentTest> logInfo = new ThreadLocal<ExtentTest>(); // Thread safe

	public static String getTimeStamp() {
		Locale locale = new Locale("en", "UK");
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
		String pattern = "dd-MMM-HH_mm_ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, dateFormatSymbols);

		String timestamp = simpleDateFormat.format(new Date());
		return timestamp;
	}

	
	
	
	// Extent Report Configurations
	public static ExtentReports setUp() {
		String fileName = "Run_" +getTimeStamp();
		String reportLocation = System.getProperty("user.dir") + "\\AutomationReports\\"+fileName+".html";
		report = new ExtentHtmlReporter(reportLocation);		
		report.config().setDocumentTitle("Automation Test Report");
		report.config().setReportName("Automation Test Report");
		report.config().setTheme(Theme.STANDARD);		
		System.out.println("Extent Report location initialized . . .");
		report.start();
		
		extent = new ExtentReports();
		extent.attachReporter(report);		
		extent.setSystemInfo("Application", "API Testing");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		System.out.println("System Info. set in Extent Report");		
		return extent;
	}
	
	
	
	public void reportInfo(String msg) {
		test.pass(msg);
	}
	public void reportPass(String title,String exp, String act) {
		String message = "<b>" +"Expected " +  title+" : "+"</b>"  + "<font color=" + "green>" +exp + "</font>"+ "\t" + "<b>" + "Actual " +title+ " : "+"</b>"  + "<font color=" + "green>" +act+ "</font>";
		test.pass(message);
	}
	public void reportFail(String title,String exp, String act) {
		String message = "<b>" +"Expected " +  title+" : "+"</b>"  + "<font color=" + "red>" +exp + "</font>"+ "\t" + "<b>" + "Actual " +title+ " : "+"</b>"  + "<font color=" + "red>" +act+ "</font>";
		test.fail(message);
	}
	
	public void validateField(String title,String expected, String actual) {
		
		if(expected.equals(actual))
			reportPass(title,expected, actual);
		else
			reportFail(title,expected, actual);
	}

	private static String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {
		}
		return str;
	}
}
