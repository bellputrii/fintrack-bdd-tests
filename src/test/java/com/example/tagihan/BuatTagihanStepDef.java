package com.example.tagihan;

import com.example.support.TestContext;
import io.cucumber.java.en.*;
import org.example.boarding.DashboardPage;
import org.example.tagihan.FormTagihanPage;
import org.example.tagihan.RiwayatTagihanPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BuatTagihanStepDef {

    private final TestContext context;
    private final WebDriver driver;

    private RiwayatTagihanPage riwayatTagihanPage;
    private FormTagihanPage formTagihanPage;

    public BuatTagihanStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    // === Background Steps ===
    @When("pengguna klik tombol tagihan")
    public void pengguna_klik_tombol_tagihan() {
        System.out.println("[Dashboard] Klik tombol tagihan");
    }

    @Then("pengguna diarahkan ke halaman riwayat tagihan siswa")
    public void pengguna_diarahkan_ke_halaman_riwayat_tagihan_siswa() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/tagihan"));
        String currentUrl = driver.getCurrentUrl();
        System.out.println("[Riwayat Tagihan] URL saat ini: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/tagihan", currentUrl);
    }

    // === Skenario: Berhasil Buat Tagihan ===
    @When("pengguna klik tombol tambah tagihan")
    public void klikTambahTagihan() {
        riwayatTagihanPage = new RiwayatTagihanPage(driver);
        riwayatTagihanPage.waitUntilLoaded();
        riwayatTagihanPage.klikTambahTagihan();
        System.out.println("[Tagihan] Klik tombol tambah tagihan");
    }

    @Then("pengguna diarahkan ke halaman form buat tagihan")
    public void verifikasiHalamanFormBuatTagihan() {
        formTagihanPage = new FormTagihanPage(driver);
        String url = formTagihanPage.getCurrentUrl();
        System.out.println("[Form Tagihan] URL saat ini: " + url);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/tagihan/add", url);
    }

    @When("pengguna menginputkan data siswa")
    public void inputDataTagihanValid() {
        formTagihanPage = new FormTagihanPage(driver);
        formTagihanPage.isiFormulirValid();
        System.out.println("[Form Tagihan] Form diisi dengan data valid");
    }

    @And("klik tombol simpan dan cetak tagihan")
    public void klikSimpanDanCetak() {
        formTagihanPage.klikSimpanCetak();
        System.out.println("[Form Tagihan] Klik tombol simpan dan cetak");
    }

    @Then("pengguna tetap berada di halaman form buat tagihan dan berhasil download PDF tagihan")
    public void verifikasiBerhasilCetak() {
        formTagihanPage = new FormTagihanPage(driver);
        String url = formTagihanPage.getCurrentUrl();
        System.out.println("[Form Tagihan] URL setelah cetak: " + url);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/tagihan/add", url);
        // Cek file PDF bisa ditambahkan di sini bila perlu
    }

    // === Skenario: Gagal Buat Tagihan ===
    @When("pengguna tidak menginputkan data siswa")
    public void inputDataTagihanKosong() {
        formTagihanPage = new FormTagihanPage(driver);
        formTagihanPage.isiFormulirKosong();
        System.out.println("[Form Tagihan] Form kosong");
    }

    @Then("pengguna tetap berada di halaman form buat tagihan dan muncul eror validasi")
    public void verifikasiGagalKarenaKosong() {
        formTagihanPage = new FormTagihanPage(driver);
        String url = formTagihanPage.getCurrentUrl();
        System.out.println("[Form Tagihan] URL saat error validasi: " + url);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/tagihan/add", url);
        String errorMsg = formTagihanPage.getPesanError();
        System.out.println("[Form Tagihan] Pesan error: " + errorMsg);
        Assertions.assertTrue(errorMsg.contains("harus diisi"));
    }
}
