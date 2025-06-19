package org.example.ekstra;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class pembayaranEkstraPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private final By dropdownLevel = By.id("level-ekstra");
    private final By btnBayarEkstra = By.id("bayar-ekstra");
    private final By inputCatatan = By.xpath("/html/body/div/div/main/div/div/div/form/div[6]/textarea");
    private final By alertSuccess = By.xpath("//*[contains(text(),'berhasil')]");

    // XPath absolut
    private final By xpathTanggal = By.xpath("/html/body/div/div/main/div/div/div/form/div[4]/input");
    private final By xpathNominal = By.xpath("/html/body/div/div/main/div/div/div/form/div[5]/div/input");
    private final By xpathBtnSimpan = By.xpath("/html/body/div/div/main/div/div/div/form/div[7]/button[2]");

    public pembayaranEkstraPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public void pilihLevelX() {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(dropdownLevel));
        js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
        js.executeScript("window.scrollBy(0, -100);");

        dropdown.click();
        List<WebElement> options = dropdown.findElements(By.tagName("option"));
        if (options.size() > 6) {
            options.get(6).click();
        } else {
            throw new RuntimeException("Tidak ada cukup option di dropdown level-ekstra");
        }

        js.executeScript(
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                dropdown
        );

        wait.until(ExpectedConditions.presenceOfElementLocated(btnBayarEkstra));
    }

    public void klikBayarEkstra() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnBayarEkstra));
        js.executeScript("arguments[0].scrollIntoView(true);", btn);
        js.executeScript("window.scrollBy(0, -100);");
        btn.click();
    }

    public void isiTanggal(String tanggal) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(xpathTanggal));
        input.clear();
        input.sendKeys(tanggal);
    }

    public void isiNominal(String nominal) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(xpathNominal));
        input.clear();
        input.sendKeys(nominal);
    }

    public void isiCatatan(String catatan) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputCatatan));
        input.clear();
        input.sendKeys(catatan);
    }

    public void simpanPembayaran() {
        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/main/div/div/div/form/div[7]/button[2]"))
        );
        submitButton.click();
    }

    public boolean verifikasiPembayaranBerhasil() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alertSuccess)).isDisplayed();
    }
}
