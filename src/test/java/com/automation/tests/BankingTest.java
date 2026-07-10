package com.automation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.pages.RegistrationPage;

public class BankingTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // 1. Define the Data Matrix loop
    @DataProvider(name = "onboardingData")
    public Object[][] getCustomerData() {
        return new Object[][] {
            {"James", "Smith", "999-11-2222"},
            {"Sarah", "Connor", "888-22-3333"}
        };
    }

    // 2. Link Test to DataProvider
    @Test(dataProvider = "onboardingData")
    public void testCustomerOnboardingScenario(String fName, String lName, String ssn) {
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Feed data parameters straight into Page Factory methods
        registrationPage.enterFirstName(fName);
        registrationPage.enterLastName(lName);
        registrationPage.enterSsn(ssn);

        System.out.println("Loop Processing Completed For: " + fName + " " + lName);
        Assert.assertNotNull(fName);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}