package org.example.konsumsi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TambahSiswaKonsumsiPage {
    WebDriver driver;
    WebDriverWait wait;

    public TambahSiswaKonsumsiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlToBe("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/tambah-siswa"));
    }

    By cariSiswaField = By.cssSelector("input[placeholder='Masukkan NISN atau Nama Siswa']");
    By tanggalMulaiField = By.cssSelector("input[type='date']:nth-of-type(1)");
    By tanggalSelesaiField = By.xpath("/html/body/div/div/main/div/div/div/form/div[4]/input");
    By tagihanField = By.cssSelector("input[placeholder='Nominal Tagihan']");
    By jenisTagihanSelect = By.cssSelector("select"); // karena hanya 1 select ditemukan
    By catatanField = By.cssSelector("textarea[placeholder='Tuliskan catatan tambahan']");
    By simpanSiswaButton = By.xpath("/html/body/div/div/main/div/div/div/form/div[8]/button");

    By pesanError = By.xpath("/html/body/div/div/main/div/div/div/div/p");

    public String getPesanError() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pesanError));
        return driver.findElement(pesanError).getText();
    }
    public void setCariSiswaField(String query) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cariSiswaField));
        driver.findElement(cariSiswaField).sendKeys(query);
    }

    public void setSiswaFromDropdown() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".cursor-pointer"))).get(0).click();
    }


    public void setTanggalMulaiField(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalMulaiField));
        driver.findElement(tanggalMulaiField).clear();
        driver.findElement(tanggalMulaiField).sendKeys(date);
    }

    public void setTanggalSelesaiField(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalSelesaiField));
        driver.findElement(tanggalSelesaiField).clear();
        driver.findElement(tanggalSelesaiField).sendKeys(date);
    }

    public void setTagihanField(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagihanField));
        driver.findElement(tagihanField).sendKeys(nominal);
    }

    public void selectJenisTagihan(String value) {
        WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(jenisTagihanSelect));
        select.sendKeys(value); // Atau pakai Select class kalau dropdown <select>
    }

    public void setCatatanField(String query) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(catatanField));
        driver.findElement(catatanField).sendKeys(query);
    }

    public void clickSimpanSiswaButton() {
        driver.findElement(simpanSiswaButton).click();
    }


}
