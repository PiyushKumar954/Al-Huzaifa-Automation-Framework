package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public static ExtentReports extent;
    public static ExtentTest test;
    public ExtentSparkReporter sparkReporter;

	 String repName;
	 
	 public void onStart(ITestContext testContext)
	    {
	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        repName = "Test-Report-" + timeStamp + ".html";
	        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

	        sparkReporter.config().setDocumentTitle("AlHuzaifa Automation Report");
	        sparkReporter.config().setReportName("AlHuzaifa Sanity Test");
	        sparkReporter.config().setTheme(Theme.STANDARD);

	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	        extent.setSystemInfo("Application", "Alhuzaifa");
	        extent.setSystemInfo("Module", "Header");
	        extent.setSystemInfo("Sub Module", "Sanity");
	        extent.setSystemInfo("User Name", System.getProperty("user.name"));
	        extent.setSystemInfo("Environment", "Live");

	        String os = testContext.getCurrentXmlTest().getParameter("os");
	        extent.setSystemInfo("Operating System", os);

	        String browser = testContext.getCurrentXmlTest().getParameter("browser");
	        extent.setSystemInfo("Browser", browser);

	        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	        if (!includedGroups.isEmpty())
	        {
	            extent.setSystemInfo("Groups", includedGroups.toString());
	        }
	    }
	 
	 public void onTestStart(ITestResult result)
	    {
	        test = extent.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
	        test.assignCategory(result.getMethod().getGroups());
	    }
	 
	 public void onTestSuccess(ITestResult result)
	    {
	        test.pass(result.getName() + " got Successfully executed");
	    }

	 public void onTestFailure(ITestResult result)
	    {
	        test.fail(result.getName() + " has failed.");
	        test.fail(result.getThrowable());

	        try
	        {
	            String imgPath = BaseClass.captureS(result.getName());
	            test.addScreenCaptureFromPath(imgPath);
	        }
	        catch (IOException e1)
	        {
	            e1.printStackTrace();
	        }
	    }

	 public void onTestSkipped(ITestResult result)
	    {
	        test.skip(result.getName() + " got Skipped");
	        if (result.getThrowable() != null)
	        {
	            test.info(result.getThrowable().getMessage());
	        }
	    }

	 public void onFinish(ITestContext testContext)
	 {
		 extent.flush();
	        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
	        File extentReports = new File(pathOfExtentReport);

	        try
	        {
	            Desktop.getDesktop().browse(extentReports.toURI());
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	    //try{ URL url = new URL("file:\\"+System.getProperty("user.dir")+"\\reports\\"+repName);
        //Create the Email Messge
        //ImageHTMLEmail email = new ImageHTMLEmail();
        //email.setDataSourceResolver(new DataSourceUrlResolver(url));
        //email.setHostName("smtp.googleemail.com");
        //email.setSmtpPort(465);
        //email.setAuthenticator(new DefaultAuthenticator("piyush.k@codilar.com","password"));
        //email.setSSLOnConnect(true);
        //email.setFrom("piyush.k@codilar.com");
        //email.setSubject("Test Results");
        //email.setMsg("Please find Attached Reports ...");
        //email.addTo("piyush9543@gmail.com");
        //email.attach(url, "extent report", "please check report...");
        //email.send();
        //}
        //catch(Exception e){
        //e.printStackTrace();}
	 }
	 

}
