package org.example.konsumsi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RiwayatPembayaranKonsumsiPage {
    WebDriver driver;
    WebDriverWait wait;

    public RiwayatPembayaranKonsumsiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    By tanggalPembayaranText = By.xpath("/html/body/div/div/main/div/div[2]/table/tbody/tr/td[1]");
    By nominalPembayaranText = By.xpath("/html/body/div/div/main/div/div[2]/table/tbody/tr/td[2]");

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlContains("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/detail-bk"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalPembayaranText)); // optionally also wait for a unique element
    }

    public boolean isOnPembayaranKonsumsiPage() {
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
