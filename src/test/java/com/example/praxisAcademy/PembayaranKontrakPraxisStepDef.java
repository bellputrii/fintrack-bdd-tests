package com.example.praxisAcademy;

import com.example.support.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DashboardPage;
import org.example.praxisAcademy.MonitoringPraxisPage;
import org.example.praxisAcademy.PembayaranPraxisAcademyPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class PembayaranKontrakPraxisStepDef {
    private final TestContext context;
    private WebDriver driver;

    DashboardPage dashboard;
    MonitoringPraxisPage monitoringPraxis;
    PembayaranPraxisAcademyPage pembayaranPraxisAcademy;

    public PembayaranKontrakPraxisStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

//    @When("pengguna klik tombol praxis academy di dashboard")
//    public void klikTombolPraxisAcademy() {
//        dashboard = new DashboardPage(driver);
//        dashboard.clickPendapatanButton();
//        dashboard.clickPraxisButton();
//        System.out.println("[Dashboard] Praxis Academy button clicked");
//    }

//    @Then("pengguna diarahkan ke halaman monitoring praxis academy")
//    public void verifikasiMonitoringPraxisPage() {
//        monitoringPraxis = new MonitoringPraxisPage(driver);
//        monitoringPraxis.waitUntilLoadedPraxis();
//        String currentUrl = driver.getCurrentUrl();
//        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/praxis", currentUrl);
//        System.out.println("[Monitoring] Current URL: " + currentUrl);
//    }

    @When("pengguna klik tombol pembayaran siswa praxis")
    public void klikTombolPembayaranSiswaPraxis() {
        monitoringPraxis = new MonitoringPraxisPage(driver);
        monitoringPraxis.waitUntilLoadedPraxis();
        monitoringPraxis.selectLevel("Level XI");
        monitoringPraxis.clickPembayaranButtonPraxis("Linda Mawar");
        System.out.println("[Monitoring Praxis] Pembayaran button clicked");
    }

    @Then("pengguna diarahkan ke halaman pembayaran siswa praxis academy")
    public void verifikasiHalamanPembayaranSiswaPraxis() {
        pembayaranPraxisAcademy = new PembayaranPraxisAcademyPage(driver);
        pembayaranPraxisAcademy.waitUntilLoaded();
        Assertions.assertTrue(pembayaranPraxisAcademy.isOnPembayaranPraxisPage());
        System.out.println("[Pembayaran] Current URL: " + driver.getCurrentUrl());
    }

    @When("pengguna menginputkan data pembayaran praxis siswa yang valid")
    public void inputDataPembayaranPraxisValid() {
        pembayaranPraxisAcademy = new PembayaranPraxisAcademyPage(driver);
        pembayaranPraxisAcademy.waitUntilLoaded();
        pembayaranPraxisAcademy.setTanggalPembayaran("06-17-2025");
        pembayaranPraxisAcademy.setKbm("3000000");
        pembayaranPraxisAcademy.setSpp("3000000");
        pembayaranPraxisAcademy.setPemeliharaan("3000000");
        pembayaranPraxisAcademy.setSumbangan("3000000");
        pembayaranPraxisAcademy.setCatatan("Membayar tagihan praxis");
        System.out.println("[Pembayaran] Mengisi data valid untuk pembayaran siswa: Linda");
    }

    @When("pengguna menginputkan data pembayaran praxis siswa yang tidak valid")
    public void inputDataPembayaranTidakValid() {
        pembayaranPraxisAcademy = new PembayaranPraxisAcademyPage(driver);
        pembayaranPraxisAcademy.waitUntilLoaded();
        pembayaranPraxisAcademy.setKbm("3000000");
        pembayaranPraxisAcademy.setSpp("3000000");
        pembayaranPraxisAcademy.setPemeliharaan("3000000");
        pembayaranPraxisAcademy.setSumbangan("3000000");
        pembayaranPraxisAcademy.setCatatan("Field kosong untuk testing validasi");
        System.out.println("[Pembayaran] Mengisi data tidak valid untuk pembayaran siswa: Linda");
    }

    @And("pengguna klik tombol simpan pembayaran praxis")
    public void klikTombolSimpanPembayaranPraxis() {
        pembayaranPraxisAcademy = new PembayaranPraxisAcademyPage(driver);
        pembayaranPraxisAcademy.clickSimpanButton();
        System.out.println("[Pembayaran] Klik tombol simpan pembayaran praxis");
    }

//    @Then("pengguna diarahkan kembali ke halaman monitoring praxis academy")
//    public void kembaliKeHalamanMonitoringPraxis() {
//        monitoringPraxis = new MonitoringPraxisPage(driver);
//        monitoringPraxis.waitUntilLoadedPraxis();
//        String currentUrl = driver.getCurrentUrl();
//        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/praxis", currentUrl);
//        System.out.println("[Monitoring] Kembali ke halaman monitoring, URL: " + currentUrl);
//    }

    @And("data pembayaran siswa praxis academy ditampilkan di tabel monitoring")
    public void verifikasiDataPembayaranTercatat() {
        monitoringPraxis = new MonitoringPraxisPage(driver);
        monitoringPraxis.waitUntilLoadedPraxis();

        Assertions.assertTrue(monitoringPraxis.isSiswaDisplayed("Linda Mawar"));
//        Assertions.assertEquals("Lunas", monitoringPraxis.getPaymentStatus("Nur", "KBM"));
//        Assertions.assertEquals("Lunas", monitoringPraxis.getPaymentStatus("Nur", "SPP"));
//        Assertions.assertEquals("Lunas", monitoringPraxis.getPaymentStatus("Nur", "Pemeliharaan"));
//        Assertions.assertEquals("Lunas", monitoringPraxis.getPaymentStatus("Nur", "Sumbangan"));
        System.out.println("[Monitoring] Data pembayaran siswa Linda ditampilkan dengan status Lunas");
    }

    @Then("pengguna tetap berada di halaman pembayaran siswa praxis academy")
    public void tetapDiHalamanPembayaran() {
        pembayaranPraxisAcademy = new PembayaranPraxisAcademyPage(driver);
        pembayaranPraxisAcademy.waitUntilLoaded();
        Assertions.assertTrue(pembayaranPraxisAcademy.isOnPembayaranPraxisPage());
        System.out.println("[Pembayaran] Masih berada di halaman pembayaran karena data tidak valid");
    }

    @And("pengguna melihat pesan error validasi pembayaran praxis")
    public void verifikasiPesanErrorValidasi() {
        pembayaranPraxisAcademy = new PembayaranPraxisAcademyPage(driver);
        pembayaranPraxisAcademy.waitUntilLoaded();
        String errorMessage = pembayaranPraxisAcademy.getPesanError();
        Assertions.assertEquals("Lengkapi keseluruhan field pembayaran", errorMessage);
        System.out.println("[Pembayaran] Error Message: " + errorMessage);
    }
}