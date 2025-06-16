package org.example.ekstra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class pembayaranEkstraPage {
    WebDriver driver;

    public pembayaranEkstraPage(WebDriver driver) {
        this.driver = driver;
    }

    By nominal = By.id("input-nominal");
    By tanggal = By.id("input-tanggal");
    By btnSimpan = By.xpath("//button[contains(text(),'Simpan Pembayaran')]");

    public void isiForm(String nominalBayar, String tanggalBayar) {
        driver.findElement(nominal).sendKeys(nominalBayar);
        driver.findElement(tanggal).sendKeys(tanggalBayar);
    }

    public void klikSimpan() {
        driver.findElement(btnSimpan).click();
    }
}
