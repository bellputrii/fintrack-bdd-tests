package org.example.konsumsi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class monitoringKonsumsiPage {
    WebDriver driver;
    WebDriverWait wait;

    public monitoringKonsumsiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    By tambahSiswaButton = By.xpath("//button[contains(@class,'bg-blue-900') and contains(text(),'+ Tambah Siswa')]");

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlToBe("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahSiswaButton)); // optionally also wait for a unique element
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickTambahSiswaButton() {
        driver.findElement(tambahSiswaButton).click();
    }
}
