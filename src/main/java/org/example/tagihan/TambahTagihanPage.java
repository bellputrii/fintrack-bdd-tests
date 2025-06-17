package org.example.tagihan;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TambahTagihanPage {
    WebDriver driver;
    WebDriverWait wait;

    public TambahTagihanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    // === Locators ===
    By cariSiswaField = By.xpath("//input[@placeholder='Masukkan NISN atau Nama Siswa']");
    By opsiDropdown = By.xpath("//div[contains(@class,'z-10') and contains(@class,'absolute')]//div[contains(@class,'cursor-pointer')]");
    By semesterField = By.xpath("//input[@placeholder='Semester']");
    By periodeField = By.xpath("//input[@placeholder='Periode']");
    By tanggalTagihanField = By.xpath("/html/body/div/div/main/div/div/div/form/div[5]/input");
    By jatuhTempoField = By.xpath("/html/body/div/div/main/div/div/div/form/div[6]/input");
    By kbmField = By.xpath("//label[contains(.,'KBM')]/following-sibling::input[1]");
    By sppField = By.xpath("//label[contains(.,'SPP')]/following-sibling::input[1]");
    By pemeliharaanField = By.xpath("//label[contains(.,'Pemeliharaan')]/following-sibling::input[1]");
    By sumbanganField = By.xpath("//label[contains(.,'Sumbangan')]/following-sibling::input[1]");
    By konsumsiField = By.xpath("//label[contains(.,'Konsumsi')]/following-sibling::input[1]");
    By boardingField = By.xpath("//label[contains(.,'Boarding')]/following-sibling::input[1]");
    By ekstraField = By.xpath("//label[contains(.,'Ekstrakurikuler')]/following-sibling::input[1]");
    By uangBelanjaField = By.xpath("//label[contains(.,'Uang Belanja')]/following-sibling::input[1]");
    By tunggakanField = By.xpath("//input[@type='number' and @class[contains(.,'w-full')]][last()]");
    By catatanField = By.xpath("//textarea[@placeholder='Masukkan catatan atau keterangan tambahan']");
    By simpanCetakButton = By.xpath("//button[contains(., 'Simpan') and contains(., 'Tagihan')]");
    By toastSuccess = By.className("text-green-600");
    By pesanError = By.xpath("//div[contains(@class,'text-red-600')]");

    // === Actions ===
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(simpanCetakButton));
    }

    public void setCariSiswaField(String nama) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cariSiswaField));
        driver.findElement(cariSiswaField).clear();
        driver.findElement(cariSiswaField).sendKeys(nama);
    }

    public void setSiswaFromDropdown() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(opsiDropdown));
        driver.findElement(opsiDropdown).click();
    }

    public void setSemester(String semester) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(semesterField));
        driver.findElement(semesterField).clear();
        driver.findElement(semesterField).sendKeys(semester);
    }

    public void setPeriode(String periode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(periodeField));
        driver.findElement(periodeField).clear();
        driver.findElement(periodeField).sendKeys(periode);
    }

    public void setTanggalTagihan(String tanggal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tanggalTagihanField));
        driver.findElement(tanggalTagihanField).sendKeys(tanggal);
    }

    public void setJatuhTempo(String tanggal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(jatuhTempoField));
        driver.findElement(jatuhTempoField).sendKeys(tanggal);
    }

    public void setKBM(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(kbmField));
        driver.findElement(kbmField).clear();
        driver.findElement(kbmField).sendKeys(nominal);
    }

    public void setSPP(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sppField));
        driver.findElement(sppField).clear();
        driver.findElement(sppField).sendKeys(nominal);
    }

    public void setPemeliharaan(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pemeliharaanField));
        driver.findElement(pemeliharaanField).clear();
        driver.findElement(pemeliharaanField).sendKeys(nominal);
    }

    public void setSumbangan(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sumbanganField));
        driver.findElement(sumbanganField).clear();
        driver.findElement(sumbanganField).sendKeys(nominal);
    }

    public void setKonsumsi(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(konsumsiField));
        driver.findElement(konsumsiField).clear();
        driver.findElement(konsumsiField).sendKeys(nominal);
    }

    public void setBoarding(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(boardingField));
        driver.findElement(boardingField).clear();
        driver.findElement(boardingField).sendKeys(nominal);
    }

    public void setEkstra(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ekstraField));
        driver.findElement(ekstraField).clear();
        driver.findElement(ekstraField).sendKeys(nominal);
    }

    public void setUangBelanja(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(uangBelanjaField));
        driver.findElement(uangBelanjaField).clear();
        driver.findElement(uangBelanjaField).sendKeys(nominal);
    }

    public void setTunggakan(String nominal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tunggakanField));
        driver.findElement(tunggakanField).clear();
        driver.findElement(tunggakanField).sendKeys(nominal);
    }

    public void setCatatan(String catatan) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(catatanField));
        driver.findElement(catatanField).clear();
        driver.findElement(catatanField).sendKeys(catatan);
    }

    public void clickSimpanCetakTagihan() {
        driver.findElement(simpanCetakButton).click();
    }

    public String getNotifikasiBerhasil() {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastSuccess));
        return toast.getText().trim();
    }

    public String getPesanError() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pesanError));
        return driver.findElement(pesanError).getText();
    }
}
