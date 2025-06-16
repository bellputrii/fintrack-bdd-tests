package org.example;

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

    By pendapatanButton = By.id("pendapatan-button");
    By konsumsiButton = By.id("pendapatan-boarding-konsumsi-link");
    By praxisAcademyButton = By.id("pendapatan-praxis-link");

    By boardingButton = By.id("pendapatan-boarding-konsumsi-link");


    By tagihanButton = By.id("tagihan-link"); // Sesuaikan ID dengan HTML kamu

    public void waitUntilTagihanButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagihanButton));
    }

    public void clickTagihanButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tagihanButton)).click();
    }

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

    public void waitUntilKonsumsiButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(konsumsiButton));
    }

    public void waitUntilPraxisAcademyButtonVisible(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(praxisAcademyButton));
    }

    public void clickKonsumsiButton() {
        driver.findElement(konsumsiButton).click();
    }

    public void clickPraxisButton() {
        driver.findElement(praxisAcademyButton).click();
    }
}
