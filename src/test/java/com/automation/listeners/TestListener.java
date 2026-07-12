package com.automation.listeners;

import com.automation.report.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create an isolated reporting node for the thread running this row of data
        ExtentTest test = ExtentManager.initReport().createTest(
            result.getMethod().getMethodName() + " [" + result.getParameters()[0] + "]"
        );
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Test executed successfully.");
        ExtentManager.unloadTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());
        Object currentClass = result.getInstance();
        
        if (currentClass instanceof DriverProvider) {
            WebDriver driver = ((DriverProvider) currentClass).getActiveDriver();
            if (driver != null) {
                // Take base64 screenshot directly to cleanly embed it inline within the HTML file
                String base64String = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                ExtentManager.getTest().fail("Snapshot attached on failure:", 
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64String).build());
            }
        }
        ExtentManager.unloadTest();
    }

    @Override
    public void onFinish(ITestContext context) {
        if (ExtentManager.initReport() != null) {
            ExtentManager.initReport().flush(); // Writes out the HTML report file to disk
        }
    }
}