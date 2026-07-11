package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automation.pages.RegistrationPage;
import com.automation.utils.ExcelReader;

public class BankingTest extends BaseTest {

    // 1. Hook the DataProvider straight into the Excel file
    @DataProvider(name = "excelOnboardingData")
    public Object[][] getExcelData() {
        String path = "./TestData.xlsx"; 
        return ExcelReader.getSheetData(path, "Onboarding");
    }

    // 2. Feed the Excel matrix dynamically down into your Page Factory steps
    @Test(dataProvider = "excelOnboardingData")
    public void testCustomerOnboardingScenario(String fName, String lName, String ssn) {
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterFirstName(fName);
        registrationPage.enterLastName(lName);
        registrationPage.enterSsn(ssn);

        System.out.println("📊 Excel Row Processed -> Name: " + fName + " " + lName);
        Assert.assertNotNull(fName);
    }
}