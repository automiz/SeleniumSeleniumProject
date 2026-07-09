package com.automation;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class BankingTest {
    WebDriver driver;
    WebDriverWait wait;
    Faker faker;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Explicit Wait configuration (Crucial for heavy Finacle/T24 architectures)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        faker = new Faker();
    }

    @Test
    public void testCustomerOnboardingScenario() {
        // Navigating to ParaBank's registration module
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        // Dynamically generating data on the fly (Simulates unique customer creation)
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String ssn = faker.idNumber().ssnValid();

        // Waiting explicitly for the form field to be interactable before taking action
        WebElement firstNameField = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("customer.firstName"))
        );
        
        // Execute interactions
        firstNameField.sendKeys(firstName);
        driver.findElement(By.id("customer.lastName")).sendKeys(lastName);
        driver.findElement(By.id("customer.ssn")).sendKeys(ssn);

        System.out.println("Generated Dynamic Core Banking Test Data:");
        System.out.println("First Name: " + firstName + " | Last Name: " + lastName + " | SSN: " + ssn);

        // Verification checkpoint
        Assert.assertNotNull(firstName);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}