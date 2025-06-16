package com.example.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

//@ScenarioScoped
public class TestContext {
    private WebDriver driver;

    public TestContext() {}
    public WebDriver getDriver() {
        if (driver == null) {
            driver = new EdgeDriver();
        }
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
