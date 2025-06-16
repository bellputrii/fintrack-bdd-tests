package com.example.konsumsi;

import com.example.support.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.konsumsi.*;
import org.example.DashboardPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class PembayaranSiswaKonsumsiStepDef {
    private final TestContext context;
    private WebDriver driver;

    DashboardPage dashboard;
    MonitoringKonsumsiPage monitoringKonsumsi;
    PembayaranKonsumsiPage pembayaranKonsumsi;
    RiwayatPembayaranKonsumsiPage riwayatPembayaranKonsumsiPage;

    public PembayaranSiswaKonsumsiStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("pengguna klik tombol pembayaran siswa")
    public void klikTombolPembayaran() {
        monitoringKonsumsi = new MonitoringKonsumsiPage(driver);
        monitoringKonsumsi.waitUntilLoaded();
        monitoringKonsumsi.selectLevel("Level IX");
        monitoringKonsumsi.clickPembayaranButton("Putri");
        System.out.println("[Monitoring] Pembayaran button clicked");
    }

    @When("pengguna klik tombol riwayat pembayaran siswa")
    public void klikTombolRiwayatPembayaran() {
        monitoringKonsumsi = new MonitoringKonsumsiPage(driver);
        monitoringKonsumsi.waitUntilLoaded();
        monitoringKonsumsi.clickRiwayatPembayaranButton("Putri");
        System.out.println("[Monitoring] Riwayat pembayaran button clicked");
    }

    @Then("pengguna diarahkan ke halaman pembayaran siswa konsumsi")
    public void verifikasiHalamanPembayaranKonsumsi() {
        pembayaranKonsumsi = new PembayaranKonsumsiPage(driver);
        pembayaranKonsumsi.waitUntilLoaded();
        System.out.println("[Pembayaran] Current URL: " + driver.getCurrentUrl());
        Assertions.assertTrue(pembayaranKonsumsi.isOnPembayaranKonsumsiPage());
    }

    @When("pengguna menginputkan data pembayaran konsumsi siswa")
    public void inputDataPembayaranValid() {
        pembayaranKonsumsi = new PembayaranKonsumsiPage(driver);
        pembayaranKonsumsi.waitUntilLoaded();
        pembayaranKonsumsi.setTanggalPembayaranField("06-10-2025");
        pembayaranKonsumsi.setNominalPembayaranKonsumsiField("10000");
        pembayaranKonsumsi.setCatatanField("Membayar tagihan konsumsi");
        System.out.println("[Pembayaran] Mengisi data valid untuk pembayaran siswa: Putri");
    }

    @When("pengguna menginputkan data pembayaran konsumsi siswa yang tidak valid")
    public void inputDataPembayaranTidakValid() {
        pembayaranKonsumsi = new PembayaranKonsumsiPage(driver);
        pembayaranKonsumsi.waitUntilLoaded();
        pembayaranKonsumsi.setTanggalPembayaranField("06-10-2025");
        pembayaranKonsumsi.setNominalPembayaranKonsumsiField("");
        pembayaranKonsumsi.setCatatanField("Field kosong untuk testing validasi");
        System.out.println("[Pembayaran] Mengisi data tidak valid untuk pembayaran siswa: Putri");
    }

    @And("pengguna klik tombol simpan pembayaran")
    public void klikTombolSimpanPembayaran() {
        pembayaranKonsumsi = new PembayaranKonsumsiPage(driver);
        pembayaranKonsumsi.clickSimpanButton();
        System.out.println("[Pembayaran] Klik tombol simpan pembayaran");
    }

    @Then("pengguna diarahkan ke halaman riwayat pembayaran siswa")
    public void verifikasiHalamanRiwayatPembayaran() {
        riwayatPembayaranKonsumsiPage = new RiwayatPembayaranKonsumsiPage(driver);
        riwayatPembayaranKonsumsiPage.waitUntilLoaded();
        System.out.println("[Riwayat Pembayaran] Current URL: " + driver.getCurrentUrl());
        Assertions.assertTrue(riwayatPembayaranKonsumsiPage.isOnPembayaranKonsumsiPage());
    }

    @And("pengguna melihat data pembayaran telah tercatat")
    public void verifikasiDataPembayaranTercatat() {
        riwayatPembayaranKonsumsiPage = new RiwayatPembayaranKonsumsiPage(driver);
        riwayatPembayaranKonsumsiPage.waitUntilLoaded();
        Assertions.assertEquals("2025-06-10", riwayatPembayaranKonsumsiPage.getTanggalPembayaranText());
        Assertions.assertEquals("Rp 50.000", riwayatPembayaranKonsumsiPage.getNominalPembayaranText());
        System.out.println("[Riwayat Pembayaran] Data pembayaran siswa berhasil ditampilkan");
    }

    @Then("pengguna tetap berada di halaman pembayaran siswa konsumsi")
    public void tetapDiHalamanPembayaran() {
        pembayaranKonsumsi = new PembayaranKonsumsiPage(driver);
        pembayaranKonsumsi.waitUntilLoaded();
        Assertions.assertTrue(pembayaranKonsumsi.isOnPembayaranKonsumsiPage());
        System.out.println("[Pembayaran] Masih berada di halaman pembayaran karena data tidak valid");
    }

    @And("pengguna melihat pesan error validasi pembayaran")
    public void verifikasiPesanErrorValidasi() {
        pembayaranKonsumsi = new PembayaranKonsumsiPage(driver);
        pembayaranKonsumsi.waitUntilLoaded();
        String errorMessage = pembayaranKonsumsi.getPesanError();
        System.out.println("[Pembayaran] Error Message: " + errorMessage);
        Assertions.assertEquals("Minimal isi salah satu pembayaran (boarding/konsumsi).", errorMessage);
    }

}
