package com.example.boarding;

import com.example.support.TestContext;
import io.cucumber.java.en.*;
import org.example.boarding.DashboardPage;
import org.example.boarding.MonitoringBoardingPage;
import org.example.boarding.PembayaranBoardingPage;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class PembayaranSiswaBoardingStepDef {

    private TestContext context;
    private WebDriver driver;

    DashboardPage dashboard;
    MonitoringBoardingPage monitoringPage;
    PembayaranBoardingPage pembayaranPage;

    public PembayaranSiswaBoardingStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("pengguna mengklik menu boarding")
    public void pengguna_mengklik_menu_boarding() {
        dashboard = new DashboardPage(driver);
        dashboard.clickPendapatanButton();
        dashboard.waitUntilBoardingButtonVisible();
        dashboard.clickBoardingButton();
    }

    @Then("pengguna diarahkan ke halaman monitoring boarding")
    public void pengguna_diarahkan_ke_halaman_monitoring_boarding() {
        monitoringPage = new MonitoringBoardingPage(driver);
        monitoringPage.waitUntilLoaded();
        assertTrue(monitoringPage.isOnMonitoringBoardingPage());
    }

    @When("pengguna mengklik tombol bayar untuk siswa eka")
    public void pengguna_mengklik_tombol_bayar_untuk_siswa_eka() {
        monitoringPage.clickPembayaranButton("eka");
    }

    @Then("pengguna diarahkan ke halaman pembayaran boarding")
    public void pengguna_diarahkan_ke_halaman_pembayaran_boarding() {
        pembayaranPage = new PembayaranBoardingPage(driver);
        pembayaranPage.waitUntilLoaded();
        assertTrue(pembayaranPage.isOnPembayaranBoardingPage());
    }

    @When("pengguna mengisi form pembayaran dengan data valid")
    public void pengguna_mengisi_form_pembayaran_dengan_data_valid() {
        pembayaranPage.setTanggalPembayaranField("06/16/2025");
        pembayaranPage.setNominalPembayaranBoardingField("1000000");
        pembayaranPage.setCatatanField("pembayaran bulan juni");
    }

    @And("pengguna mengklik tombol simpan")
    public void pengguna_mengklik_tombol_simpan() {
        pembayaranPage.clickSimpanButton();
    }

    @Then("pengguna diarahkan kembali ke halaman monitoring boarding")
    public void pengguna_diarahkan_kembali_ke_halaman_monitoring_boarding() {
        monitoringPage.waitUntilLoaded();
        assertTrue(monitoringPage.isOnMonitoringBoardingPage());
    }

    @When("pengguna mengklik tombol riwayat untuk siswa eka")
    public void pengguna_mengklik_tombol_riwayat_untuk_siswa_eka() {
        monitoringPage.clickRiwayatPembayaranButton("eka");
    }

    @Then("pengguna diarahkan ke halaman riwayat pembayaran")
    public void pengguna_diarahkan_ke_halaman_riwayat_pembayaran() {
        assertTrue(driver.getCurrentUrl().contains("/riwayat"));
    }

    @And("pengguna melihat data pembayaran telah tercatat")
    public void pengguna_melihat_data_pembayaran_telah_tercatat() {
        assertTrue(driver.getPageSource().contains("pembayaran bulan juni"));
    }

    @When("pengguna mengklik tombol bayar untuk siswa eka")
    public void pengguna_mengklik_tombol_bayar_untuk_siswa_Eka() {
        monitoringPage.clickPembayaranButton("Eka");
    }

    @When("pengguna mengisi form pembayaran dengan data tidak valid")
    public void pengguna_mengisi_form_pembayaran_dengan_data_tidak_valid() {
        pembayaranPage.setTanggalPembayaranField("");
        pembayaranPage.setNominalPembayaranBoardingField("abc");
        pembayaranPage.setCatatanField("");
    }

    @Then("pengguna tetap berada di halaman pembayaran boarding")
    public void pengguna_tetap_berada_di_halaman_pembayaran_boarding() {
        assertTrue(pembayaranPage.isOnPembayaranBoardingPage());
    }

    @And("pengguna melihat pesan error validasi pembayaran")
    public void pengguna_melihat_pesan_error_validasi_pembayaran() {
        String pesanError = pembayaranPage.getPesanError();
        assertTrue(pesanError.toLowerCase().contains("tidak boleh kosong") || !pesanError.isEmpty());
    }
}