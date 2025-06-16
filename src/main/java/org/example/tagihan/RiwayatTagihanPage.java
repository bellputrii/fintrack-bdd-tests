package org.example.tagihan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RiwayatTagihanPage {
    WebDriver driver;
    WebDriverWait wait;

    public RiwayatTagihanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // timeout 10 detik
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(), 'Tambah Tagihan')]")
    private WebElement tambahTagihanButton;

    public void klikTambahTagihan() {
        tambahTagihanButton.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOf(tambahTagihanButton));
    }
}
