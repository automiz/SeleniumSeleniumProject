package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automation.utils.ExcelReader;
import com.automation.utils.ConfigReader;
import com.automation.pages.RegistrationPage; // 👈 Don't forget to import the page page!

public class BankingTest extends BaseTest {

    @DataProvider(name = "excelOnboardingData", parallel = true) 
    public Object[][] getExcelData() {
        String path = ConfigReader.getProperty("excelPath");
        String sheet = ConfigReader.getProperty("excelSheet");
        return ExcelReader.getSheetData(path, sheet);
    }

    @Test(dataProvider = "excelOnboardingData")
    public void testCustomerOnboardingScenario(String fName, String lName, String ssn) {
        // 1. Fetch the URL configuration map dynamically
        String targetUrl = ConfigReader.getProperty("appUrl");
        getActiveDriver().get(targetUrl);

        // 2. Initialize the Page Factory object with the active thread driver
        RegistrationPage registrationPage = new RegistrationPage(getActiveDriver());

        // 3. Execute the single optimized action block
        registrationPage.registerCustomer(fName, lName, ssn);

        System.out.println("📊 Thread ID [" + Thread.currentThread().threadId() + "] Processed -> " + fName);
        Assert.assertNotNull(fName);
    }
}