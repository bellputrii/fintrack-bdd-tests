package com.example.konsumsi;

import com.example.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.konsumsi.DashboardPage;
import org.example.konsumsi.MonitoringKonsumsiPage;
import org.example.konsumsi.TambahSiswaKonsumsiPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class TambahSiswaKonsumsiStepDef {
    private final TestContext context;
    private WebDriver driver;

    DashboardPage dashboard;
    MonitoringKonsumsiPage monitoringKonsumsi;
    TambahSiswaKonsumsiPage tambahSiswaKonsumsi;

    public TambahSiswaKonsumsiStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("pengguna klik tombol konsumsi")
    public void klikTombolKonsumsi() {
        dashboard = new DashboardPage(driver);
        System.out.println("[Dashboard] Current URL: " + driver.getCurrentUrl());

        dashboard.clickPendapatanButton();
        dashboard.waitUntilKonsumsiButtonVisible();
        dashboard.clickKonsumsiButton();
        System.out.println("[Dashboard] Konsumsi button clicked");
    }

    @Then("pengguna diarahkan ke halaman monitoring konsumsi")
    public void verifikasiHalamanMonitoringKonsumsi() {
        monitoringKonsumsi = new MonitoringKonsumsiPage(driver);
        monitoringKonsumsi.waitUntilLoaded();
        String currentUrl = monitoringKonsumsi.getCurrentUrl();
        System.out.println("[Monitoring] Current URL: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi", currentUrl);
    }

    @When("pengguna klik tombol tambah siswa")
    public void klikTombolTambahSiswa() {
        monitoringKonsumsi = new MonitoringKonsumsiPage(driver);
        monitoringKonsumsi.clickTambahSiswaButton();
        System.out.println("[Monitoring] Tambah siswa button clicked");
    }

    @Then("pengguna diarahkan ke halaman tambah siswa konsumsi")
    public void verifikasiHalamanTambahSiswaKonsumsi() {
        tambahSiswaKonsumsi = new TambahSiswaKonsumsiPage(driver);
        tambahSiswaKonsumsi.waitUntilLoaded();
        String currentUrl = tambahSiswaKonsumsi.getCurrentUrl();
        System.out.println("[Tambah Siswa] Current URL: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/tambah-siswa", currentUrl);
    }

    @When("pengguna menginputkan data konsumsi siswa")
    public void isiFormulirDataKonsumsiValid() {
        tambahSiswaKonsumsi = new TambahSiswaKonsumsiPage(driver);
        System.out.println("[Tambah Siswa] Mengisi data valid untuk siswa: Linda");
        tambahSiswaKonsumsi.setCariSiswaField("Linda");
        tambahSiswaKonsumsi.setSiswaFromDropdown();
        tambahSiswaKonsumsi.setTanggalMulaiField("05-01-2025");
        tambahSiswaKonsumsi.setTanggalSelesaiField("06-01-2025");
        tambahSiswaKonsumsi.setTagihanField("200000");
        tambahSiswaKonsumsi.selectJenisTagihan("Konsumsi");
        tambahSiswaKonsumsi.setCatatanField("Mengikuti program konsumsi untuk 1 bulan");
    }

    @When("pengguna menginputkan data konsumsi siswa yang tidak valid")
    public void isiFormulirDataKonsumsiTidakValid() {
        tambahSiswaKonsumsi = new TambahSiswaKonsumsiPage(driver);
        System.out.println("[Tambah Siswa] Mengisi data valid untuk siswa: Linda");
        tambahSiswaKonsumsi.setCariSiswaField("Linda");
        tambahSiswaKonsumsi.setSiswaFromDropdown();
        tambahSiswaKonsumsi.setTanggalMulaiField("05-01-2025");
        tambahSiswaKonsumsi.setTanggalSelesaiField("");
        tambahSiswaKonsumsi.setTagihanField("200000");
        tambahSiswaKonsumsi.selectJenisTagihan("Konsumsi");
        tambahSiswaKonsumsi.setCatatanField("Mengikuti program konsumsi untuk 1 bulan");
    }

    @When("klik tombol simpan siswa")
    public void klikTombolSimpanSiswa() {
        tambahSiswaKonsumsi = new TambahSiswaKonsumsiPage(driver);
        tambahSiswaKonsumsi.clickSimpanSiswaButton();
        System.out.println("[Tambah Siswa] Klik tombol Simpan");
    }

    @Then("pengguna diarahkan kembali ke halaman monitoring konsumsi")
    public void verifikasiKembaliKeHalamanMonitoring() {
        monitoringKonsumsi = new MonitoringKonsumsiPage(driver);
        monitoringKonsumsi.waitUntilLoaded();
        String currentUrl = monitoringKonsumsi.getCurrentUrl();
        System.out.println("[Monitoring] Kembali ke halaman monitoring, URL: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi", currentUrl);
    }

    @Then("data konsumsi siswa ditampilkan")
    public void verifikasiDataSiswaDitampilkan() {
        monitoringKonsumsi = new MonitoringKonsumsiPage(driver);
        monitoringKonsumsi.waitUntilLoaded();
        boolean ditemukan = monitoringKonsumsi.isNamaSiswaOnTabelMonitoringKonsumsi("Linda");
        System.out.println("[Monitoring] Apakah nama siswa 'Linda' muncul di tabel? " + ditemukan);
        Assertions.assertTrue(ditemukan);
    }

    @Then("pengguna tetap berada di halaman tambah siswa konsumsi")
    public void verifikasiDataSiswaTidakTersimpan() {
        tambahSiswaKonsumsi = new TambahSiswaKonsumsiPage(driver);
        tambahSiswaKonsumsi.waitUntilLoaded();
        String currentUrl = tambahSiswaKonsumsi.getCurrentUrl();
        System.out.println("[Tambah Siswa] Current URL: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/tambah-siswa", currentUrl);
    }

    @Then("pengguna melihat pesan error validasi")
    public void verifikasiPesanErrorValidasi() {
        tambahSiswaKonsumsi = new TambahSiswaKonsumsiPage(driver);
        tambahSiswaKonsumsi.waitUntilLoaded();
        String errorMessage = tambahSiswaKonsumsi.getPesanError();
        System.out.println("[Tambah Siswa] Error Message: " + errorMessage);
        Assertions.assertEquals("Semua field wajib diisi!", errorMessage);
    }

}
