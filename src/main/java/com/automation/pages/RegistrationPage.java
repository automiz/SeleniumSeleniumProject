package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;

    // Advanced Page Factory Locators
    @FindBy(id = "customer.firstName")
    private WebElement firstNameField;

    @FindBy(id = "customer.lastName")
    private WebElement lastNameField;

    @FindBy(id = "customer.ssn")
    private WebElement ssnField;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initializes elements lazily
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField)).clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterSsn(String ssn) {
        ssnField.sendKeys(ssn);
    }
}