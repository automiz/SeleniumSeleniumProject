package com.automation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    // Declared as protected so child test classes can access it directly
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("🔧 [BaseTest] Spinning up a fresh Chrome instance...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("🧹 [BaseTest] Tearing down the browser session...");
            driver.quit();
        }
    }
}