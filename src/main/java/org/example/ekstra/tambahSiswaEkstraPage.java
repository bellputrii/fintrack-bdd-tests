package org.example.ekstra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class tambahSiswaEkstraPage {
    WebDriver driver;

    public tambahSiswaEkstraPage(WebDriver driver) {
        this.driver = driver;
    }

    By nama = By.id("input-nama");
    By jenis = By.id("input-ekstra");
    By biaya = By.id("input-biaya");
    By periode = By.id("input-periode");
    By btnSimpan = By.xpath("//button[contains(text(),'Simpan')]");

    public void isiForm(String namaSiswa, String jenisEkstra, String biayaEkstra, String periodeEkstra) {
        driver.findElement(nama).sendKeys(namaSiswa);
        driver.findElement(jenis).sendKeys(jenisEkstra);
        driver.findElement(biaya).sendKeys(biayaEkstra);
        driver.findElement(periode).sendKeys(periodeEkstra);
    }

    public void klikSimpan() {
        driver.findElement(btnSimpan).click();
    }
    public void waitUntilLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(nama));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}