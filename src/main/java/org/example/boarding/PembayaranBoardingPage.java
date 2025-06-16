package org.example.boarding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PembayaranBoardingPage {
    WebDriver driver;
    WebDriverWait wait;

    public PembayaranBoardingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isOnPembayaranBoardingPage() {
        return driver.getCurrentUrl().contains("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/pembayaran-bk");
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlContains("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/pembayaran-bk"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalPembayaranField));
    }

    By tanggalPembayaranField = By.id("tanggal_pembayaran");
    By nominalPembayaranBoardingField = By.id("boarding");
    By catatanField = By.id("catatan");
    By simpanButton = By.cssSelector("button.bg-blue-900");
    By pesanError = By.cssSelector("p.font-medium");

    public void setTanggalPembayaranField(String date) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalPembayaranField));
        field.clear();
        field.sendKeys(date);
    }

    public void setNominalPembayaranBoardingField(String query) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(nominalPembayaranBoardingField));
        field.clear();
        field.sendKeys(query);
    }

    public void setCatatanField(String query) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(catatanField));
        field.clear();
        field.sendKeys(query);
    }

    public void clickSimpanButton() {
        wait.until(ExpectedConditions.elementToBeClickable(simpanButton)).click();
    }

    public String getPesanError() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pesanError));
        return driver.findElement(pesanError).getText();
    }
}
