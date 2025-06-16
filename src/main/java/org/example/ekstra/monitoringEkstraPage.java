package org.example.ekstra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class monitoringEkstraPage {
    WebDriver driver;

    public monitoringEkstraPage(WebDriver driver) {
        this.driver = driver;
    }

    By btnTambahKontrak = By.id("tambah-kontrak-ekstra");
    By btnBayar = By.xpath("//button[contains(text(),'Bayar')]");

    public void openPage() {
        driver.get("https://fe-fintrack.vercel.app/ekstra");
    }

    public void clikTambahKontrak() {
        driver.findElement(btnTambahKontrak).click();
    }

    public void klikBayar() {
        driver.findElement(btnBayar).click();
    }

    public boolean isAt() {
        return driver.getCurrentUrl().contains("/org/example/ekstra");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitUntilLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(btnTambahKontrak));
    }
}
