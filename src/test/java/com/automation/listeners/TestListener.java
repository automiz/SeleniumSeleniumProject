package com.automation.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🎬 [SUITE START] Starting execution for suite: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 [TEST STARTING] Running method: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ [PASS] " + result.getName() + " completed successfully!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ [FAIL] " + result.getName() + " failed!");
        
        // 1. Get the current test instance to fetch its active WebDriver instance
        Object currentClass = result.getInstance();
        WebDriver driver = null;
        
        try {
            // We read the 'driver' field dynamically from your running test class instance
            java.lang.reflect.Field field = currentClass.getClass().getDeclaredField("driver");
            field.setAccessible(true);
            driver = (WebDriver) field.get(currentClass);
        } catch (Exception e) {
            System.out.println("⚠️ Could not access WebDriver instance to capture screenshot: " + e.getMessage());
        }

        // 2. Take the screenshot if driver is online
        if (driver != null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = result.getName() + "_" + timestamp + ".png";
            
            // Create target folder if it doesn't exist
            try {
                Files.createDirectories(Paths.get("./screenshots"));
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destFile = new File("./screenshots/" + screenshotName);
                Files.copy(srcFile.toPath(), destFile.toPath());
                System.out.println("📸 [SCREENSHOT SAVED] Destination: ./screenshots/" + screenshotName);
            } catch (IOException e) {
                System.out.println("💥 Failed to save screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 [SUITE FINISHED] Finished execution for suite: " + context.getName());
    }
}