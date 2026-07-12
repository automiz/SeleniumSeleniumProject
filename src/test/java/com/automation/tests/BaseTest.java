package com.automation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.listeners.DriverProvider;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest implements DriverProvider { 
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        // 🎯 Mutes the version-bound Selenium CDP warnings completely
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
    }

    @Override
    public WebDriver getActiveDriver() {
        return driverThreadLocal.get();
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = getActiveDriver();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}