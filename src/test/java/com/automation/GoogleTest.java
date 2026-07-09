package com.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // This runs BEFORE every individual test method
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleTitle() {
        // The actual test case
        driver.get("https://www.google.com");
        String actualTitle = driver.getTitle();
        
        // TestNG Assertions
        Assert.assertEquals(actualTitle, "Google", "Title match failed!");
    }

    @AfterMethod
    public void tearDown() {
        // This runs AFTER every individual test method
        if (driver != null) {
            driver.quit();
        }
    }
}