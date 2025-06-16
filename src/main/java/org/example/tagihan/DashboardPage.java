package org.example.tagihan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By tagihanButton = By.id("tagihan-link"); // Sesuaikan ID dengan HTML kamu

    public void waitUntilTagihanButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagihanButton));
    }

    public void clickTagihanButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tagihanButton)).click();
    }
}