package org.example.boarding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RiwayatPembayaranBoardingPage {
    WebDriver driver;
    WebDriverWait wait;

    public RiwayatPembayaranBoardingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By tanggalPembayaranText = By.id("tanggal_pembayaran");
    By nominalPembayaranText = By.id("boarding");

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlContains("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/detail-bk"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalPembayaranText)); // optionally also wait for a unique element
    }

    public boolean isOnPembayaranBoardingPage() {
        return driver.getCurrentUrl().contains("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/detail-bk");
    }

    public String getTanggalPembayaranText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalPembayaranText));
        return driver.findElement(tanggalPembayaranText).getText();
    }

    public String getNominalPembayaranText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nominalPembayaranText));
        return driver.findElement(nominalPembayaranText).getText();
    }
}
