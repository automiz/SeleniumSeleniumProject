package com.automation.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Paths;

public class ExtentManager {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static synchronized ExtentReports initReport() {
        if (extent == null) {
            extent = new ExtentReports();
            
            // 🎯 Generate a unique timestamp folder for this specific suite run instance
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String folderName = "Run_" + timestamp;
            
            // Build the absolute file path inside target/ExtentReports/Run_timestamp/
            String reportDirectory = Paths.get(System.getProperty("user.dir"), "target", "ExtentReports", folderName).toString();
            String fullReportPath = Paths.get(reportDirectory, "ExtentReport.html").toString();
            
            // 🛡️ Ensure the historical directories are physically created on disk before initializing Spark
            File dir = new File(reportDirectory);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            ExtentSparkReporter spark = new ExtentSparkReporter(fullReportPath);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Core Banking Historical Automation Report");
            spark.config().setReportName("Execution Suite Run Archive - " + timestamp);
            
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA-Staging");
            extent.setSystemInfo("Execution Engine", "Selenium 4 + TestNG");
            
            System.out.println("📊 [HISTORICAL ARCHIVE CREATED] Path: " + fullReportPath);
        }
        return extent;
    }

    public static void setTest(ExtentTest test) {
        extentTestThreadLocal.set(test);
    }

    public static ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    public static void unloadTest() {
        extentTestThreadLocal.remove();
    }
}