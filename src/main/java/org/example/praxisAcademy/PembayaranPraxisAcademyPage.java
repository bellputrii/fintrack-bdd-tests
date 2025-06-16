package org.example.praxisAcademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PembayaranPraxisAcademyPage {
    WebDriver driver;
    WebDriverWait wait;

    public PembayaranPraxisAcademyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isOnPembayaranPraxisPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(kbmField));
        return driver.getCurrentUrl().contains("https://fe-fintrack.vercel.app/pendapatan/praxis/pembayaran-siswa");
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlContains("/pendapatan/praxis"));
    }

    By tanggalPembayaranField = By.cssSelector("input[type='date']");
    By kbmField = By.cssSelector("input#kbm");
    By sppField = By.cssSelector("input#spp");
    By pemeliharaanField = By.cssSelector("input#pemeliharaan");
    By sumbanganField = By.cssSelector("input#sumbangan");
    By catatanField = By.cssSelector("textarea#catatan");
    By simpanButton = By.xpath("//button[contains(text(), 'Simpan Pembayaran')]");
    By pesanError = By.cssSelector("div.bg-red-100 p.font-medium");
    By pesanSuccess = By.cssSelector("div.bg-green-100 p.font-medium");

    public void setTanggalPembayaran(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalPembayaranField));
        driver.findElement(tanggalPembayaranField).sendKeys(date);
    }

    public void setKbm(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(kbmField));
        driver.findElement(kbmField).sendKeys(value);
    }

    public void setSpp(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sppField));
        driver.findElement(sppField).sendKeys(value);
    }

    public void setPemeliharaan(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pemeliharaanField));
        driver.findElement(pemeliharaanField).sendKeys(value);
    }

    public void setSumbangan(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sumbanganField));
        driver.findElement(sumbanganField).sendKeys(value);
    }

    public void setCatatan(String catatan) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(catatanField));
        driver.findElement(catatanField).sendKeys(catatan);
    }

    public void clickSimpanButton() {
        wait.until(ExpectedConditions.elementToBeClickable(simpanButton));
        driver.findElement(simpanButton).click();
    }

    public String getPesanError() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pesanError));
        return driver.findElement(pesanError).getText();
    }

    public String getPesanSuccess() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pesanSuccess));
        return driver.findElement(pesanSuccess).getText();
    }
}
