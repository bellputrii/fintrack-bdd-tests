package org.example.praxisAcademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DetailKontrakSiswaPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public DetailKontrakSiswaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // === Locator ===
    private By namaSiswaField = By.id("nama_siswa");
    private By idSiswaField = By.id("id_siswa");
    private By nisnField = By.id("nisn");
    private By akademikField = By.id("akademik");

    private By uangKBMField = By.id("uang_kbm");
    private By uangSPPField = By.id("uang_spp");
    private By uangPemeliharaanField = By.id("uang_pemeliharaan");
    private By uangSumbanganField = By.id("uang_sumbangan");

    private By fileKontrakLink = By.id("file_kontrak");
    private By kembaliButton = By.xpath("//button[contains(text(),'Kembali')]");

    // === Action ===

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(namaSiswaField));
    }

    public String getNamaSiswa() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(namaSiswaField)).getAttribute("value").trim();
    }

    public String getIdSiswaWithLevel() {
        return driver.findElement(idSiswaField).getAttribute("value").trim();
    }

    public String getNISN() {
        return driver.findElement(nisnField).getAttribute("value").trim();
    }

    public String getAkademik() {
        return driver.findElement(akademikField).getAttribute("value").trim();
    }

    public String getUangKBM() {
        return driver.findElement(uangKBMField).getAttribute("value").trim();
    }

    public String getUangSPP() {
        return driver.findElement(uangSPPField).getAttribute("value").trim();
    }

    public String getUangPemeliharaan() {
        return driver.findElement(uangPemeliharaanField).getAttribute("value").trim();
    }

    public String getUangSumbangan() {
        return driver.findElement(uangSumbanganField).getAttribute("value").trim();
    }

    public String getFileKontrakLink() {
        try {
            WebElement link = driver.findElement(fileKontrakLink);
            return link.getAttribute("href");
        } catch (Exception e) {
            return null;
        }
    }

    public void clickKembaliButton() {
        wait.until(ExpectedConditions.elementToBeClickable(kembaliButton)).click();
    }
}
