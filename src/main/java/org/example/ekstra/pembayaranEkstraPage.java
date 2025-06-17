package org.example.ekstra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class pembayaranEkstraPage {
    WebDriver driver;

    By inputCariNama = By.id("search-ekstra");
    By tombolBayar = By.id("bayar-ekstra");
    By inputTanggal = By.xpath("/html/body/div/div/main/div/div/div/form/div[4]/input");
    By inputNominal = By.xpath("/html/body/div/div/main/div/div/div/form/div[5]/div/input");
    By inputCatatan = By.xpath("/html/body/div/div/main/div/div/div/form/div[6]/textarea");
    By tombolSimpan = By.xpath("//button[contains(text(),'Simpan')]");

    public pembayaranEkstraPage(WebDriver driver) {
        this.driver = driver;
    }

    public void cariNama(String nama) {
        WebElement input = driver.findElement(inputCariNama);
        input.clear();
        input.sendKeys(nama);
    }

    public void klikBayarEkstra() {
        driver.findElement(tombolBayar).click();
    }

    public void isiTanggal(String tanggal) {
        WebElement input = driver.findElement(inputTanggal);
        input.clear();
        input.sendKeys(tanggal);
    }

    public void isiNominal(String nominal) {
        WebElement input = driver.findElement(inputNominal);
        input.clear();
        input.sendKeys(nominal);
    }

    public void isiCatatan(String catatan) {
        WebElement input = driver.findElement(inputCatatan);
        input.clear();
        input.sendKeys(catatan);
    }

    public void klikSimpan() {
        driver.findElement(tombolSimpan).click();
    }

    public boolean pesanErrorMuncul() {
        return driver.getPageSource().toLowerCase().contains("please fill");
    }
}
