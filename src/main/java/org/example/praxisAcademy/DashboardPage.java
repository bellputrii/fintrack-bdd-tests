package org.example.praxisAcademy;

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

//    By pendapatanButton = By.xpath("//button[contains(@id, 'pendapatan') and contains(text(), 'Pendapatan')]");

    By pendapatanButton = By.id("pendapatan-button");
    By praxisAcademyButton = By.id("pendapatan-praxis-link");

    public void clickPendapatanButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pendapatanButton));
        wait.until(ExpectedConditions.elementToBeClickable(pendapatanButton));
        driver.findElement(pendapatanButton).click();
    }

    public void waitUntilKonsumsiButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(praxisAcademyButton));
    }
    public void clickKonsumsiButton() {
        driver.findElement(praxisAcademyButton).click();
    }
}