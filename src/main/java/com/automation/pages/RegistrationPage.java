package com.automation.pages;

import com.automation.report.ReportLogger; // Import the custom framework logger
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private final WebDriverWait wait;

    @FindBy(id = "customer.firstName")
    private WebElement firstNameField;

    @FindBy(id = "customer.lastName")
    private WebElement lastNameField;

    @FindBy(id = "customer.ssn")
    private WebElement ssnField;

    public RegistrationPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); 
    }

    public void registerCustomer(String fName, String lName, String ssn) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField)).clear();
        firstNameField.sendKeys(fName);
        ReportLogger.info("Entered First Name parameter: " + fName);
        
        lastNameField.sendKeys(lName);
        ReportLogger.info("Entered Last Name parameter: " + lName);
        
        ssnField.sendKeys(ssn);
        ReportLogger.info("Entered SSN configuration key parameter data.");
    }
}