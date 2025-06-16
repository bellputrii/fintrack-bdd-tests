package org.example.praxisAcademy;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.time.Duration;

public class TambahKontrakSiswaPraxisPage {
    WebDriver driver;
    WebDriverWait wait;

    public TambahKontrakSiswaPraxisPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // === Locator ===
    By cariSiswaField = By.id("cari_siswa");
    By opsiDropdown = By.xpath("/html/body/div/div/main/div/div/div/form/div[1]/div/div");

    By uangKBMField = By.id("uang_kbm");
    By uangSPPField = By.id("uang_spp");
    By uangSumbanganField = By.id("uang_sumbangan");
    By uangPemeliharaanField = By.id("uang_pemeliharaan");

    By catatanField = By.id("catatan");
    By fileKontrakInput = By.id("file_kontrak");

    By simpanKontrakButton = By.xpath("//button[@type='submit' and contains(., 'Simpan Kontrak')]");
    By toastSuccess = By.className("toast-success");

    By pesanError = By.xpath("/html/body/div/div/main/div/div/div/div/p");

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

    public void setSiswaPraxisFromDropdown() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(opsiDropdown)).getFirst().click();
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

    public void unggahFileKontrakHeadless() {
        String filePath = Paths.get(
                System.getProperty("user.dir"),
                "src", "main", "java", "org", "example", "assets",
                "tagihan_Ahmad Budi (3).pdf"
        ).toAbsolutePath().toString();

        WebElement fileInput = driver.findElement(fileKontrakInput);

        // Hilangkan atribut 'hidden' jika ada
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].removeAttribute('hidden');", fileInput
        );

        // Beri waktu untuk render
        try { Thread.sleep(500); } catch (InterruptedException ie) {}

        fileInput.sendKeys(filePath);

//        // Verifikasi kuat
//        Awaitility.await()
//                .atMost(5, TimeUnit.SECONDS)
//                .until(() -> !fileInput.getAttribute("value").isEmpty());
    }

    public void clickSimpanKontrakPraxis() {
        driver.findElement(simpanKontrakButton).click();
    }

    public String getNotifikasiBerhasil() {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastSuccess));
        return toast.getText().trim();
    }

    public String getPesanErrorPraxis() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pesanError));
        return driver.findElement(pesanError).getText();
    }
}
