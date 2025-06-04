package org.example.konsumsi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class tambahSiswaKonsumsiPage {
    WebDriver driver;
    WebDriverWait wait;

    public tambahSiswaKonsumsiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlToBe("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/tambah-siswa"));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahSiswaButton)); // optionally also wait for a unique element
    }

}
