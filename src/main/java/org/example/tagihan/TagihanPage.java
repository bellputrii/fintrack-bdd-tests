package org.example.tagihan;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class TagihanPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public TagihanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ======= Locator Elements =======
    public By levelSelect = By.xpath("//select[contains(@class,'bg-gray-300')]");
    public By searchInput = By.id("search");
    public By tambahTagihanButton = By.xpath("//button[contains(.,'Tambah Tagihan') or contains(.,'Tambah Tagihan')]");
    public By tableRows = By.cssSelector("tbody tr");
    public By namaSiswaCell = By.xpath("td[1]");
    public By tanggalTagihanCell = By.xpath("td[2]");
    public By fileTagihanLink = By.cssSelector("a[href*='fitrack-production']");

    // ======= Interactions =======

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahTagihanButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(levelSelect));
    }

    public void selectLevel(String level) {
        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(levelSelect));
        Select dropdown = new Select(select);
        dropdown.selectByVisibleText("Level " + level);
    }

    public void searchByName(String name) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        search.clear();
        search.sendKeys(name);
    }

    public void clickTambahTagihan() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(tambahTagihanButton));
        button.click();
    }

    public boolean isTagihanDisplayed(String namaSiswa) {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
        return rows.stream().anyMatch(row -> {
            WebElement namaCell = row.findElement(namaSiswaCell);
            return namaCell.getText().equalsIgnoreCase(namaSiswa);
        });
    }

    public String getTanggalTagihan(String namaSiswa) {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
        for (WebElement row : rows) {
            WebElement namaCell = row.findElement(namaSiswaCell);
            if (namaCell.getText().equalsIgnoreCase(namaSiswa)) {
                WebElement tanggalCell = row.findElement(tanggalTagihanCell);
                return tanggalCell.getText().trim();
            }
        }
        throw new NoSuchElementException("Tidak ditemukan tanggal tagihan untuk siswa: " + namaSiswa);
    }

    public void openTagihanFileForSiswa(String namaSiswa) {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
        for (WebElement row : rows) {
            WebElement namaCell = row.findElement(namaSiswaCell);
            if (namaCell.getText().equalsIgnoreCase(namaSiswa)) {
                WebElement fileLink = row.findElement(fileTagihanLink);
                fileLink.click();
                System.out.println("[ACTION] Klik file tagihan untuk siswa: " + namaSiswa);
                return;
            }
        }
        throw new RuntimeException("Siswa dengan nama '" + namaSiswa + "' tidak ditemukan.");
    }

    public boolean isOnTagihanPage() {
        return driver.getCurrentUrl().contains("/tagihan");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
