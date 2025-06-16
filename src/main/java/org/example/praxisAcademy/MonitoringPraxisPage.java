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
    private By namaSiswaCell = By.cssSelector("td:nth-child(1)");
    private By kontrakIcon = By.cssSelector("td svg#kontrak");
    private By bayarIcon = By.cssSelector("td svg#bayar");

    // ======= Interactions =======

    public void selectLevel(String level) {
        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(levelSelect));
        select.findElement(By.xpath(".//option[. = '" + level + "']")).click();
    }

    public void searchByKeyword(String keyword) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        search.clear();
        search.sendKeys(keyword);
    }

    public void clickTambahKontrak() {
        wait.until(ExpectedConditions.elementToBeClickable(tambahKontrakButton)).click();
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
