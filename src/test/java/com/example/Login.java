package com.example;

import com.example.support.TestContext;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.example.support.TestContext;
import io.cucumber.java.en.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class Login {
    private final TestContext context;
    public Login(TestContext context) {
        this.context = context;
    }
//    WebDriver driver;

    @Given("pengguna berada di halaman login")
    public void pengguna_berada_di_halaman_login() {
        // Setup driver dan buka halaman login
        WebDriver driver = new EdgeDriver();
        context.setDriver(driver);  // Simpan driver ke TestContext
//        driver = new EdgeDriver();
        driver.get("https://fe-fintrack.vercel.app/"); // ganti sesuai URL aplikasi kamu
    }

    @When("pengguna mengisi email dan password yang benar")
    public void pengguna_mengisi_email_dan_password_yang_benar() {
        WebDriver driver = context.getDriver();
        driver.findElement(By.id("email")).sendKeys("bendahara@example.com");
        driver.findElement(By.id("password")).sendKeys("password123");
    }

    @When("pengguna mengisi email yang valid")
    public void pengguna_mengisi_email_yang_valid() {
        WebDriver driver = context.getDriver();
        driver.findElement(By.id("email")).sendKeys("bendahara@example.com");
    }

    @When("pengguna mengisi password yang salah")
    public void pengguna_mengisi_password_yang_salah() {
        WebDriver driver = context.getDriver();
        driver.findElement(By.id("password")).sendKeys("salah123");
    }

    @When("pengguna mengisi email yang tidak terdaftar")
    public void pengguna_mengisi_email_yang_tidak_terdaftar() {
        WebDriver driver = context.getDriver();
        driver.findElement(By.id("email")).sendKeys("tidakterdaftar@example.com");
    }

    @When("pengguna mengisi password apa saja")
    public void pengguna_mengisi_password_apa_saja() {
        WebDriver driver = context.getDriver();
        driver.findElement(By.id("password")).sendKeys("randompass");
    }

    @And("menekan tombol login")
    public void menekan_tombol_login() {
        WebDriver driver = context.getDriver();
        driver.findElement(By.id("loginButton")).click();
    }

    @Then("pengguna diarahkan ke halaman dashboard")
    public void pengguna_diarahkan_ke_halaman_dashboard() {
        // Tunggu sebentar bisa pakai explicit wait kalau perlu
        WebDriver driver = context.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://fe-fintrack.vercel.app/dashboard", currentUrl);
        //        driver.quit();
        //        System.out.println(currentUrl);
    }

    @Then("pengguna melihat pesan kesalahan {string}")
    public void pengguna_melihat_pesan_kesalahan(String pesan) {
        WebDriver driver = context.getDriver();
        String errorMessage = driver.findElement(By.id("error-message")).getText();
        assertEquals(pesan, errorMessage);
        driver.quit();
    }


}
