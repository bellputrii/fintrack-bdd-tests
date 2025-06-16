package org.example.boarding;

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

    // Ganti ID sesuai ID tombol pada bagian boarding
    By pendapatanButton = By.id("pendapatan-button");
    By boardingButton = By.id("pendapatan-boarding-konsumsi-link");

    public void clickPendapatanButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pendapatanButton));
        wait.until(ExpectedConditions.elementToBeClickable(pendapatanButton));
        driver.findElement(pendapatanButton).click();
    }

    public void waitUntilBoardingButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(boardingButton));
    }

    public void clickBoardingButton() {
        driver.findElement(boardingButton).click();
    }
}
