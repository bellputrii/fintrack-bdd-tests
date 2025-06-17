package org.example.konsumsi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MonitoringKonsumsiPage {
    WebDriver driver;
    WebDriverWait wait;

    public MonitoringKonsumsiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    By tambahSiswaButton = By.xpath("//button[contains(@class,'bg-blue-900') and contains(text(),'+ Tambah Siswa')]");
    By levelFilter = By.xpath("/html/body/div/div/main/div/div[2]/select");
    By pembayaranButton = By.cssSelector("#bayar");
    By riwayatPembayaranButton = By.cssSelector("#riwayat");
    By namaSiswaText = By.xpath("//table/tbody/tr/td[1]");

    By namaSiswaBaris = By.xpath("td[1]"); // perhatikan: ini hanya untuk dalam konteks <tr>
    By tabelBaris = By.xpath("//table/tbody/tr");
    By successMessage = By.xpath("/html/body/div/div/main/div/div[3]");

    public boolean isOnMonitoringPage() {
        return driver.getCurrentUrl().contains("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi");
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

    public void clickPembayaranButton(String namaSiswaTarget) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabelBaris));
        List<WebElement> rows = driver.findElements(tabelBaris);

        for (WebElement row : rows) {
            WebElement namaCell = row.findElement(namaSiswaBaris);
            if (namaCell.getText().toLowerCase().contains(namaSiswaTarget.toLowerCase())) {
                WebElement bayarButton = row.findElement(pembayaranButton);
                bayarButton.click();
                System.out.println("[ACTION] Klik tombol bayar untuk siswa: " + namaSiswaTarget);
                return;
            }
        }

        throw new RuntimeException("Siswa dengan nama '" + namaSiswaTarget + "' tidak ditemukan di tabel.");
    }

    public void clickRiwayatPembayaranButton(String namaSiswaTarget) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabelBaris));
        List<WebElement> rows = driver.findElements(tabelBaris);

        for (WebElement row : rows) {
            WebElement namaCell = row.findElement(namaSiswaBaris);
            if (namaCell.getText().toLowerCase().contains(namaSiswaTarget.toLowerCase())) {
                WebElement riwayatButton = row.findElement(riwayatPembayaranButton);
                riwayatButton.click();
                System.out.println("[ACTION] Klik tombol riwayat pembayaran untuk siswa: " + namaSiswaTarget);
                return;
            }
        }

        throw new RuntimeException("Siswa dengan nama '" + namaSiswaTarget + "' tidak ditemukan di tabel.");
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlContains("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahSiswaButton));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void selectLevel(String value) {
        WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(levelFilter));
        select.sendKeys(value); // Atau pakai Select class kalau dropdown <select>
    }

    public void clickTambahSiswaButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahSiswaButton));
        driver.findElement(tambahSiswaButton).click();
    }

    public boolean isNamaSiswaOnTabelMonitoringKonsumsi(String nama) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(namaSiswaText));
        List<WebElement> rows = driver.findElements(namaSiswaText);
        for (WebElement cell : rows) {
            if (cell.getText().toLowerCase().contains(nama.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
