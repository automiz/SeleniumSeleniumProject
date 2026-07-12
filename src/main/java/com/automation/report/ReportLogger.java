package com.automation.report;

import com.aventstack.extentreports.Status;

public class ReportLogger {
    
    public static void info(String message) {
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.INFO, message);
        }
        System.out.println("ℹ️ [INFO] " + message);
    }

    public static void pass(String message) {
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.PASS, message);
        }
        System.out.println("✅ [PASS] " + message);
    }

    public static void fail(String message) {
        if (ExtentManager.getTest() != null) {
            ExtentManager.getTest().log(Status.FAIL, message);
        }
        System.err.println("❌ [FAIL] " + message);
    }
}