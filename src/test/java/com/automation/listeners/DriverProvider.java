package com.automation.listeners;

import org.openqa.selenium.WebDriver;

public interface DriverProvider {
    // 🎯 Guarantees a safe, thread-specific way to fetch the active driver instance
    WebDriver getActiveDriver();
}