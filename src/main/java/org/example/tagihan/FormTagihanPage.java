package org.example.tagihan;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormTagihanPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public FormTagihanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =======================
    // ======= LOCATORS ======
    // =======================
    private final By fieldNisn = By.cssSelector("input[placeholder='Masukkan NISN atau Nama Siswa']");
    private final By fieldSemester = By.cssSelector("input[placeholder='Semester']");
    private final By fieldPeriode = By.cssSelector("input[placeholder='Periode']");
    private final By fieldTanggalTagihan = By.xpath("(//input[@type='date'])[1]");
    private final By fieldJatuhTempo = By.xpath("(//input[@type='date'])[2]");
    private final By fieldKBM = By.cssSelector("input[placeholder='Jumlah KBM']");
    private final By fieldSPP = By.cssSelector("input[placeholder='Jumlah SPP']");
    private final By fieldPemeliharaan = By.cssSelector("input[placeholder='Jumlah Pemeliharaan']");
    private final By fieldSumbangan = By.cssSelector("input[placeholder='Jumlah Sumbangan']");
    private final By fieldKonsumsi = By.cssSelector("input[placeholder='Jumlah Konsumsi']");
    private final By fieldBoarding = By.cssSelector("input[placeholder='Jumlah Boarding']");
    private final By fieldEkstrakurikuler = By.cssSelector("input[placeholder='Jumlah Ekstrakurikuler']");
    private final By fieldUangBelanja = By.cssSelector("input[placeholder='Jumlah Uang Belanja']");
    private final By fieldTunggakan = By.xpath("//input[@type='number' and not(@placeholder)]");
    private final By fieldCatatan = By.cssSelector("textarea[placeholder='Masukkan catatan atau keterangan tambahan']");
    private final By tombolSimpanCetak = By.xpath("//button[contains(text(), 'Simpan') and contains(text(), 'Cetak')]");
    private final By pesanErrorValidasi = By.xpath("//div[contains(text(), 'harus diisi')]");

    // =======================
    // ======= ACTIONS =======
    // =======================

    public void isiFieldNisn(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldNisn)).sendKeys(value);
    }

    public void isiFieldSemester(String value) {
        driver.findElement(fieldSemester).sendKeys(value);
    }

    public void isiFieldPeriode(String value) {
        driver.findElement(fieldPeriode).sendKeys(value);
    }

    public void isiFieldTanggalTagihan(String date) {
        driver.findElement(fieldTanggalTagihan).sendKeys(date);
    }

    public void isiFieldJatuhTempo(String date) {
        driver.findElement(fieldJatuhTempo).sendKeys(date);
    }

    public void isiFieldKBM(String value) {
        driver.findElement(fieldKBM).sendKeys(value);
    }

    public void isiFieldSPP(String value) {
        driver.findElement(fieldSPP).sendKeys(value);
    }

    public void isiFieldPemeliharaan(String value) {
        driver.findElement(fieldPemeliharaan).sendKeys(value);
    }

    public void isiFieldSumbangan(String value) {
        driver.findElement(fieldSumbangan).sendKeys(value);
    }

    public void isiFieldKonsumsi(String value) {
        driver.findElement(fieldKonsumsi).sendKeys(value);
    }

    public void isiFieldBoarding(String value) {
        driver.findElement(fieldBoarding).sendKeys(value);
    }

    public void isiFieldEkstrakurikuler(String value) {
        driver.findElement(fieldEkstrakurikuler).sendKeys(value);
    }

    public void isiFieldUangBelanja(String value) {
        driver.findElement(fieldUangBelanja).sendKeys(value);
    }

    public void isiFieldTunggakan(String value) {
        driver.findElement(fieldTunggakan).sendKeys(value);
    }

    public void isiFieldCatatan(String value) {
        driver.findElement(fieldCatatan).sendKeys(value);
    }

    public void klikSimpanCetak() {
        driver.findElement(tombolSimpanCetak).click();
    }

    public String getPesanError() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pesanErrorValidasi)).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlContains("/tagihan"));
    }

    public void isiFormulirValid() {
        isiFieldNisn("Linda");
        isiFieldSemester("2");
        isiFieldPeriode("2");
        isiFieldTanggalTagihan("2025-06-16");
        isiFieldJatuhTempo("2025-06-30");
        isiFieldKBM("3000000");
        isiFieldSPP("3000000");
        isiFieldPemeliharaan("3000000");
        isiFieldSumbangan("3000000");
        isiFieldKonsumsi("0");
        isiFieldBoarding("0");
        isiFieldEkstrakurikuler("0");
        isiFieldUangBelanja("0");
        isiFieldTunggakan("0");
        isiFieldCatatan("Tagihan dengan input yang valid");
    }
}
