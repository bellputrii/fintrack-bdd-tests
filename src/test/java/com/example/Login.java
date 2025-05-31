package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Login {
//    WebDriver driver;
//    WebDriverWait wait;
//
//    @Given("pengguna berada di halaman login")
//    public void halamanLogin() {
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get("http://127.0.0.1:3000"); // URL React login page kamu
//    }
//
//    @When("pengguna mengisi email dan password yang benar")
//    public void isiForm() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
//        driver.findElement(By.id("email")).clear();
//        driver.findElement(By.id("email")).sendKeys("bendahara@example.com");
//
//        driver.findElement(By.id("password")).clear();
//        driver.findElement(By.id("password")).sendKeys("password123");
//    }
//
//    @And("menekan tombol login")
//    public void klikLogin() {
//        driver.findElement(By.id("loginButton")).click();
//    }
//
//    @Then("pengguna diarahkan ke halaman beranda")
//    public void cekHalamanDashboard() {
//        // Tunggu sampai URL berubah ke /dashboard (maks 10 detik)
//        wait.until(ExpectedConditions.urlContains("/dashboard"));
//        String currentUrl = driver.getCurrentUrl();
//
//        // Pastikan URL mengandung /dashboard
//        if (!currentUrl.contains("/dashboard")) {
//            throw new AssertionError("User tidak diarahkan ke halaman dashboard, URL saat ini: " + currentUrl);
//        }
//
//        driver.quit();
//    }
WebDriver driver;

    @Given("pengguna berada di halaman login")
    public void pengguna_berada_di_halaman_login() {
        // Setup driver dan buka halaman login
        driver = new ChromeDriver();
        driver.get("http://127.0.0.1:3000"); // ganti sesuai URL aplikasi kamu
    }

    @When("pengguna mengisi email dan password yang benar")
    public void pengguna_mengisi_email_dan_password_yang_benar() {
        driver.findElement(By.id("email")).sendKeys("bendahara@example.com");
        driver.findElement(By.id("password")).sendKeys("password123");
    }

    @When("pengguna mengisi email yang valid")
    public void pengguna_mengisi_email_yang_valid() {
        driver.findElement(By.id("email")).sendKeys("bendahara@example.com");
    }

    @When("pengguna mengisi password yang salah")
    public void pengguna_mengisi_password_yang_salah() {
        driver.findElement(By.id("password")).sendKeys("salah123");
    }

    @When("pengguna mengisi email yang tidak terdaftar")
    public void pengguna_mengisi_email_yang_tidak_terdaftar() {
        driver.findElement(By.id("email")).sendKeys("tidakterdaftar@example.com");
    }

    @When("pengguna mengisi password apa saja")
    public void pengguna_mengisi_password_apa_saja() {
        driver.findElement(By.id("password")).sendKeys("randompass");
    }

    @And("menekan tombol login")
    public void menekan_tombol_login() {
        driver.findElement(By.id("loginButton")).click();
    }

    @Then("pengguna diarahkan ke halaman dashboard")
    public void pengguna_diarahkan_ke_halaman_dashboard() {
        // Tunggu sebentar bisa pakai explicit wait kalau perlu
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/dashboard"));
        driver.quit();
    }

    @Then("pengguna melihat pesan kesalahan {string}")
    public void pengguna_melihat_pesan_kesalahan(String pesan) {
        String errorMessage = driver.findElement(By.id("error-message")).getText();
        assertEquals(pesan, errorMessage);
        driver.quit();
    }
}
