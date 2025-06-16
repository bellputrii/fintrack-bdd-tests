package org.example.praxisAcademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TambahKontrakSiswaPraxisPage {
    WebDriver driver;
    WebDriverWait wait;

    public TambahKontrakSiswaPraxisPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // === Locator ===
    By cariSiswaField = By.id("cari_siswa");
    By opsiDropdown = By.cssSelector(".cursor-pointer");

    By uangKBMField = By.id("uang_kbm");
    By uangSPPField = By.id("uang_spp");
    By uangSumbanganField = By.id("uang_sumbangan");
    By uangPemeliharaanField = By.id("uang_pemeliharaan");

    By catatanField = By.id("catatan");
    By fileKontrakInput = By.id("file_kontrak");

    By simpanKontrakButton = By.xpath("//button[@type='submit' and contains(., 'Simpan Kontrak')]");
    By toastSuccess = By.className("toast-success");

    // === Actions ===
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlToBe("https://fe-fintrack.vercel.app/pendapatan/praxis/tambah-kontrak"));
    }

    public void setCariSiswaField(String nama) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cariSiswaField));
        driver.findElement(cariSiswaField).sendKeys(nama);
    }

    public void setSiswaFromDropdown() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(opsiDropdown)).get(0).click();
    }

    public void setUangKBM(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(uangKBMField));
        driver.findElement(uangKBMField).sendKeys(nominal);
    }

    public void setUangSPP(String nominal) {
        driver.findElement(uangSPPField).sendKeys(nominal);
    }

    public void setUangSumbangan(String nominal) {
        driver.findElement(uangSumbanganField).sendKeys(nominal);
    }

    public void setUangPemeliharaan(String nominal) {
        driver.findElement(uangPemeliharaanField).sendKeys(nominal);
    }

    public void setCatatanField(String catatan) {
        driver.findElement(catatanField).sendKeys(catatan);
    }

    public void unggahFileKontrak(String filePath) {
        WebElement uploadInput = driver.findElement(fileKontrakInput);
        uploadInput.sendKeys(filePath);
    }

    public void clickSimpanKontrak() {
        driver.findElement(simpanKontrakButton).click();
    }

    public String getNotifikasiBerhasil() {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastSuccess));
        return toast.getText().trim();
    }
}
