package org.example.konsumsi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PembayaranKonsumsiPage {
    WebDriver driver;
    WebDriverWait wait;

    public PembayaranKonsumsiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isOnPembayaranKonsumsiPage() {
        return driver.getCurrentUrl().contains("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/pembayaran-bk");
    }


    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlContains("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/pembayaran-bk"));
    }

    By tanggalPembayaranField = By.xpath("/html/body/div/div/main/div/div/div/div/div[2]/input");
    By nominalPembayaranKonsumsiField = By.cssSelector("#konsumsi");
    By catatanField = By.cssSelector("#catatan");
    By simpanButton = By.xpath("/html/body/div/div/main/div/div/div/div/button");
    By pesanError = By.xpath("/html/body/div/div/main/div/div/div/div[1]/p");

    public String getPesanError() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pesanError));
        return driver.findElement(pesanError).getText();
    }

    public void setTanggalPembayaranField(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalPembayaranField));
        driver.findElement(tanggalPembayaranField).sendKeys(date);
    }

    public void setNominalPembayaranKonsumsiField(String query) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nominalPembayaranKonsumsiField));
        driver.findElement(nominalPembayaranKonsumsiField).sendKeys(query);
    }

    public void setCatatanField(String query) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(catatanField));
        driver.findElement(catatanField).sendKeys(query);
    }

    public void clickSimpanButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(simpanButton));
        driver.findElement(simpanButton).click();
    }


}
