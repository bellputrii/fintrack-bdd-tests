package org.example.tagihan;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormTagihanPage {
    WebDriver driver;

    public FormTagihanPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Masukkan NISN atau Nama Siswa']")
    private WebElement fieldNisn;

    @FindBy(xpath = "//input[@placeholder='Semester']")
    private WebElement fieldSemester;

    @FindBy(xpath = "//input[@placeholder='Periode']")
    private WebElement fieldPeriode;

    @FindBy(xpath = "//input[@type='date'][1]")
    private WebElement fieldTanggalTagihan;

    @FindBy(xpath = "//input[@type='date'][2]")
    private WebElement fieldJatuhTempo;

    @FindBy(xpath = "//input[@placeholder='Jumlah KBM']")
    private WebElement fieldKBM;

    @FindBy(xpath = "//input[@placeholder='Jumlah SPP']")
    private WebElement fieldSPP;

    @FindBy(xpath = "//input[@placeholder='Jumlah Pemeliharaan']")
    private WebElement fieldPemeliharaan;

    @FindBy(xpath = "//input[@placeholder='Jumlah Sumbangan']")
    private WebElement fieldSumbangan;

    @FindBy(xpath = "//input[@placeholder='Jumlah Konsumsi']")
    private WebElement fieldKonsumsi;

    @FindBy(xpath = "//input[@placeholder='Jumlah Boarding']")
    private WebElement fieldBoarding;

    @FindBy(xpath = "//input[@placeholder='Jumlah Ekstrakurikuler']")
    private WebElement fieldEkstrakurikuler;

    @FindBy(xpath = "//input[@placeholder='Jumlah Uang Belanja']")
    private WebElement fieldUangBelanja;

    @FindBy(xpath = "//input[@type='number' and not(@placeholder)]")
    private WebElement fieldTunggakan;

    @FindBy(xpath = "//textarea[@placeholder='Masukkan catatan atau keterangan tambahan']")
    private WebElement fieldCatatan;

    @FindBy(xpath = "//button[contains(text(), 'Simpan') and contains(text(), 'Cetak')]")
    private WebElement tombolSimpanCetak;

    @FindBy(xpath = "//div[contains(text(), 'harus diisi')]")
    private WebElement pesanErrorValidasi;

    public void isiFormulirValid() {
        fieldNisn.sendKeys("Linda");
        fieldSemester.sendKeys("2");
        fieldPeriode.sendKeys("2");
        fieldTanggalTagihan.sendKeys("2025-06-16");
        fieldJatuhTempo.sendKeys("2025-06-30");
        fieldKBM.sendKeys("3000000");
        fieldSPP.sendKeys("3000000");
        fieldPemeliharaan.sendKeys("3000000");
        fieldSumbangan.sendKeys("3000000");
        fieldKonsumsi.sendKeys("0");
        fieldBoarding.sendKeys("0");
        fieldEkstrakurikuler.sendKeys("0");
        fieldUangBelanja.sendKeys("0");
        fieldTunggakan.sendKeys("0");
        fieldCatatan.sendKeys("Tagihan dengan input yang valid");
    }

    public void isiFormulirKosong() {
        // Tidak mengisi apapun
    }

    public void klikSimpanCetak() {
        tombolSimpanCetak.click();
    }

    public String getPesanError() {
        return pesanErrorValidasi.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
