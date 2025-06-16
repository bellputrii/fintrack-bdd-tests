package com.example.tagihan;

import com.example.support.TestContext;
import io.cucumber.java.en.*;
import org.example.tagihan.DashboardPage;
import org.example.tagihan.FormTagihanPage;
import org.example.tagihan.RiwayatTagihanPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BuatTagihanStepDef {

    private final TestContext context;
    private final WebDriver driver;

    private RiwayatTagihanPage riwayatTagihanPage;
    private FormTagihanPage formTagihanPage;
    private DashboardPage dashboardPage;

    public BuatTagihanStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
        this.dashboardPage = new DashboardPage(driver);
    }

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

    @When("pengguna klik tombol tagihan")
    public void pengguna_klik_tombol_tagihan() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.waitUntilTagihanButtonVisible();
        dashboardPage.clickTagihanButton();
        System.out.println("[Dashboard] Tagihan button clicked");
    }

    @Then("pengguna diarahkan ke halaman riwayat tagihan siswa")
    public void pengguna_diarahkan_ke_halaman_riwayat_tagihan() {
        riwayatTagihanPage = new RiwayatTagihanPage(driver);
        riwayatTagihanPage.waitUntilLoaded();

        String currentUrl = riwayatTagihanPage.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/tagihan"),
                "Halaman riwayat tagihan tidak terbuka. Current URL: " + currentUrl);
    }

    @When("pengguna diarahkan ke halaman form tagihan siswa")
    public void pengguna_diarahkan_ke_halaman_form_tagihan_siswa() {
        formTagihanPage = new FormTagihanPage(driver);
        formTagihanPage.waitUntilLoaded();
        Assertions.assertTrue(formTagihanPage.getCurrentUrl().contains("/tagihan"),
                "Tidak diarahkan ke halaman form tagihan. URL: " + formTagihanPage.getCurrentUrl());
    }

    @Then("pengguna diarahkan ke halaman form buat tagihan")
    public void pengguna_diarahkan_ke_form_tagihan() {
        formTagihanPage = new FormTagihanPage(driver);
        formTagihanPage.waitUntilLoaded();
        Assertions.assertTrue(formTagihanPage.getCurrentUrl().contains("/tagihan"),
                "Tidak berada di halaman form tagihan. Current URL: " + formTagihanPage.getCurrentUrl());
    }

    @When("pengguna menginputkan data siswa")
    public void pengguna_menginputkan_data() {
        formTagihanPage = new FormTagihanPage(driver);
        formTagihanPage.waitUntilLoaded();

        formTagihanPage.isiFieldNisn("Linda");
        formTagihanPage.isiFieldSemester("2");
        formTagihanPage.isiFieldPeriode("2");
        formTagihanPage.isiFieldTanggalTagihan("2025-06-16");
        formTagihanPage.isiFieldJatuhTempo("2025-06-30");
        formTagihanPage.isiFieldKBM("3000000");
        formTagihanPage.isiFieldSPP("3000000");
        formTagihanPage.isiFieldPemeliharaan("3000000");
        formTagihanPage.isiFieldSumbangan("3000000");
        formTagihanPage.isiFieldKonsumsi("0");
        formTagihanPage.isiFieldBoarding("0");
        formTagihanPage.isiFieldEkstrakurikuler("0");
        formTagihanPage.isiFieldUangBelanja("0");
        formTagihanPage.isiFieldTunggakan("0");
        formTagihanPage.isiFieldCatatan("Tagihan dengan input yang valid");

        System.out.println("[FormTagihanPage] Data siswa berhasil diinputkan");
    }
}
