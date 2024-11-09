package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtendReportManager implements ITestListener

{
public ExtentSparkReporter sparkreporter;//UI of project
public ExtentReports extent;//populate common info of the report
public ExtentTest extentTest;
public ExtentTest test;

String repName;

public void onStart(ITestContext testContext) {
	
	/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	Date dt=new Date();
	String currentdatetimestamp=df.format(dt);*/
	
	
	String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//timestamp
	repName="Test-Report-" +timestamp+".html";
	sparkreporter=new ExtentSparkReporter(".\\reports\\" + repName);//specify the location of report
	
	sparkreporter.config().setDocumentTitle("opencart automation report");//title of report
	sparkreporter.config().setReportName("opencart Functional Testing");//name of report
	sparkreporter.config().setTheme(Theme.DARK);
	
	extent=new ExtentReports();
	extent.attachReporter(sparkreporter);
	extent.setSystemInfo("Application", "opencart");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("Sub Module", "Customers");
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");
	
	String os=testContext.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operating System", os);
	
	String browser=testContext.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);
	
	List<String> includegroups=testContext.getCurrentXmlTest().getIncludedGroups();
	if(!includegroups.isEmpty()) {
		extent.setSystemInfo("Groups", includegroups.toString());
	}
}
	
	public void onTestSuccess(ITestResult result) {
		
		ExtentTest test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+" got successfully executed");
		
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+" got Failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		 try {
			 String imgPath=new BaseClass().captureScreen(result.getName());//before attaching ss weneed to captureit in base calss .....yaha pe do driver hai ek object ka ek base class ka isliye humne base class ke driver ko static kiya hai
			 test.addScreenCaptureFromPath(imgPath);
		 }
		catch(Exception e)
		 {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP,result.getName()+" got Skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		String pathofextentReport=System.getProperty("user.dir")+"\\reports\\" +repName;
		
		File extentReport=new File(pathofextentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		/*try {
			URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\" +repName);
			//ImageHtmlEmail email=new ImageHtmlEmail();
			//email.se
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/
	}
}




