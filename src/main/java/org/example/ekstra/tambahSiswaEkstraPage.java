package org.example.ekstra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class tambahSiswaEkstraPage {
    WebDriver driver;
    WebDriverWait wait;

    public tambahSiswaEkstraPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By inputNama = By.xpath("/html/body/div/div/main/div/div/form/div[1]/div/input");
    By tombolTambah = By.xpath("/html/body/div/div/main/div/div/form/button[1]");
    By ekstra0 = By.id("ekstra-0");
    By selectEkstra = By.xpath("/html/body/div/div/main/div/div/form/div[2]/div/select");
    By optionEkstraKedua = By.xpath("/html/body/div/div/main/div/div/form/div[2]/div/select/option[2]");
    By tanggalMulai = By.id("tanggalMulai");
    By tanggalSelesai = By.id("tanggalSelesai");
    By pesanError = By.xpath("/html/body/div/div/main/div/div/div/p");

    public void isiNama(String nama) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputNama));
        driver.findElement(inputNama).clear();
        driver.findElement(inputNama).sendKeys(nama);
    }

    public void klikTambah() {
        wait.until(ExpectedConditions.elementToBeClickable(tombolTambah));
        driver.findElement(tombolTambah).click();
    }

    public void klikEkstra0() {
        wait.until(ExpectedConditions.elementToBeClickable(ekstra0));
        driver.findElement(ekstra0).click();
    }

    public void pilihJenisEkstraKedua() {
        WebElement dropdown = driver.findElement(selectEkstra);
        Select select = new Select(dropdown);
        select.selectByIndex(1); // index 1 = option ke-2
    }

    public void isiTanggalMulai(String tanggal) {
        WebElement inputTanggal = driver.findElement(tanggalMulai);
        inputTanggal.click();
        inputTanggal.sendKeys(tanggal);
    }

    public void isiTanggalSelesai(String tanggal) {
        WebElement inputTanggal = driver.findElement(tanggalSelesai);
        inputTanggal.click();
        inputTanggal.sendKeys(tanggal);
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputNama));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean apakahAdaPesanError() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pesanError));
            return driver.findElement(pesanError).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPesanError() {
        try {
            return driver.findElement(pesanError).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
