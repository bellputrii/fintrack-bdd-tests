package org.example.boarding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MonitoringBoardingPage {
    WebDriver driver;
    WebDriverWait wait;

    public MonitoringBoardingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By tambahSiswaButton = By.xpath("//button[contains(@class,'bg-blue-900') and contains(text(),'+ Tambah Siswa')]");
    By levelFilter = By.xpath("/html/body/div/div/main/div/div[2]/select");
    By namaSiswaText = By.xpath("//table/tbody/tr/td[1]");
    By tabelBaris = By.xpath("//table/tbody/tr");

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.urlToBe("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahSiswaButton));
    }

    public void clickTambahSiswaButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahSiswaButton)).click();
    }

    public void selectLevel(String value) {
        WebElement select = wait.until(ExpectedConditions.visibilityOfElementLocated(levelFilter));
        select.sendKeys(value);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isOnMonitoringBoardingPage() {
        return getCurrentUrl().contains("/pendapatan/boarding-konsumsi");
    }

    public boolean isNamaSiswaOnTabelMonitoringBoarding(String nama) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(namaSiswaText));
        List<WebElement> rows = driver.findElements(namaSiswaText);
        for (WebElement cell : rows) {
            if (cell.getText().toLowerCase().contains(nama.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void clickPembayaranButton(String namaSiswaTarget) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabelBaris));
        List<WebElement> rows = driver.findElements(tabelBaris);

        for (WebElement row : rows) {
            WebElement namaCell = row.findElement(By.xpath("td[1]"));
            if (namaCell.getText().toLowerCase().contains(namaSiswaTarget.toLowerCase())) {
                WebElement bayarButton = row.findElement(By.cssSelector("svg#bayar"));
                bayarButton.click();
                System.out.println("[ACTION] Klik tombol BAYAR untuk siswa: " + namaSiswaTarget);
                return;
            }
        }

        throw new RuntimeException("Siswa dengan nama '" + namaSiswaTarget + "' tidak ditemukan di tabel.");
    }

    public void clickRiwayatPembayaranButton(String namaSiswaTarget) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabelBaris));
        List<WebElement> rows = driver.findElements(tabelBaris);

        for (WebElement row : rows) {
            WebElement namaCell = row.findElement(By.xpath("td[1]"));
            if (namaCell.getText().toLowerCase().contains(namaSiswaTarget.toLowerCase())) {
                WebElement riwayatButton = row.findElement(By.cssSelector("svg#riwayat"));
                riwayatButton.click();
                System.out.println("[ACTION] Klik tombol RIWAYAT untuk siswa: " + namaSiswaTarget);
                return;
            }
        }

        throw new RuntimeException("Siswa dengan nama '" + namaSiswaTarget + "' tidak ditemukan di tabel.");
    }
}
