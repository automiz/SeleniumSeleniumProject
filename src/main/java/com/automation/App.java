package com.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        // Selenium 4 automatically manages the driver binaries (no System.setProperty needed!)
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.get("https://www.google.com");
            System.out.println("Page Title is: " + driver.getTitle());
        } finally {
            driver.quit();
        }
    }
}