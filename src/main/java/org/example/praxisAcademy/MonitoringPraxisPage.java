package org.example.praxisAcademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MonitoringPraxisPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MonitoringPraxisPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ======= Locator Elements =======
    private By levelSelect = By.id("level-select");
    private By searchInput = By.id("search-praxis");
    private By tambahKontrakButton = By.id("tambah-kontrak-praxis");
    private By tableRows = By.cssSelector("tbody tr");
    private By namaSiswaCell = By.xpath("//table/tbody/tr/td[1]");
    private By kontrakIcon = By.cssSelector("td svg#kontrak");
    private By bayarIcon = By.cssSelector("td svg#bayar");

    // ======= Interactions =======

    public void clickPembayaranButton(String namaSiswaTarget) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            WebElement namaCell = row.findElement(namaSiswaCell);
            if (namaCell.getText().toLowerCase().contains(namaSiswaTarget.toLowerCase())) {
                WebElement bayarButton = row.findElement(bayarIcon);
                bayarButton.click();
                System.out.println("[ACTION] Klik tombol bayar untuk siswa: " + namaSiswaTarget);
                return;
            }
        }

        throw new RuntimeException("Siswa dengan nama '" + namaSiswaTarget + "' tidak ditemukan di tabel.");
    }

    public void clickKontrakSiswaButton(String namaSiswaTarget) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            WebElement namaCell = row.findElement(namaSiswaCell);
            if (namaCell.getText().toLowerCase().contains(namaSiswaTarget.toLowerCase())) {
                WebElement riwayatButton = row.findElement(kontrakIcon);
                riwayatButton.click();
                System.out.println("[ACTION] Klik tombol riwayat pembayaran untuk siswa: " + namaSiswaTarget);
                return;
            }
        }

        throw new RuntimeException("Siswa dengan nama '" + namaSiswaTarget + "' tidak ditemukan di tabel.");
    }

    public void waitUntilLoadedPraxis() {
        wait.until(ExpectedConditions.urlToBe("https://fe-fintrack.vercel.app/pendapatan/praxis"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahKontrakButton)); // optionally also wait for a unique element
    }

    public String getCurrentUrlPraxis() {
        return driver.getCurrentUrl();
    }

    public void selectLevel(String level) {
        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(levelSelect));
        select.findElement(By.xpath(".//option[. = '" + level + "']")).click();
    }

    public void clickAddKontrakPraxisButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahKontrakButton));
        driver.findElement(tambahKontrakButton).click();
    }

    public void searchByKeyword(String keyword) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        search.clear();
        search.sendKeys(keyword);
    }

    public void clickKontrakIconByNama(String namaSiswa) {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
        for (WebElement row : rows) {
            String nama = row.findElement(namaSiswaCell).getText();
            if (nama.equalsIgnoreCase(namaSiswa)) {
                row.findElement(kontrakIcon).click();
                break;
            }
        }
    }

    public void clickBayarIconByNama(String namaSiswa) {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
        for (WebElement row : rows) {
            String nama = row.findElement(namaSiswaCell).getText();
            if (nama.equalsIgnoreCase(namaSiswa)) {
                row.findElement(bayarIcon).click();
                break;
            }
        }
    }

    public boolean isSiswaDisplayed(String namaSiswa) {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
        return rows.stream().anyMatch(row ->
                row.findElement(namaSiswaCell).getText().equalsIgnoreCase(namaSiswa)
        );
    }
}
