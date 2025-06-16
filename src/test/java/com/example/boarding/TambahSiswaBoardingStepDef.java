package com.example.boarding;

import com.example.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.boarding.DashboardPage;
import org.example.boarding.MonitoringBoardingPage;
import org.example.boarding.TambahSiswaBoardingPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class TambahSiswaBoardingStepDef {
    private final TestContext context;
    private WebDriver driver;

    DashboardPage dashboard;
    MonitoringBoardingPage monitoringBoarding;
    TambahSiswaBoardingPage tambahSiswaBoarding;

    public TambahSiswaBoardingStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("pengguna klik tombol boarding")
    public void klikTombolBoarding() {
        dashboard = new DashboardPage(driver);
        System.out.println("[Dashboard] Current URL: " + driver.getCurrentUrl());

        dashboard.clickPendapatanButton();
        dashboard.waitUntilBoardingButtonVisible();
        dashboard.clickBoardingButton();
        System.out.println("[Dashboard] Boarding button clicked");
    }

    @Then("pengguna diarahkan ke halaman monitoring boarding")
    public void verifikasiHalamanMonitoringBoarding() {
        monitoringBoarding = new MonitoringBoardingPage(driver);
        monitoringBoarding.waitUntilLoaded();
        String currentUrl = monitoringBoarding.getCurrentUrl();
        System.out.println("[Monitoring] Current URL: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi", currentUrl);
    }

    @When("pengguna klik tombol tambah siswa")
    public void klikTombolTambahSiswa() {
        monitoringBoarding = new MonitoringBoardingPage(driver);
        monitoringBoarding.clickTambahSiswaButton();
        System.out.println("[Monitoring] Tambah siswa button clicked");
    }

    @Then("pengguna diarahkan ke halaman tambah siswa boarding")
    public void verifikasiHalamanTambahSiswaBoarding() {
        tambahSiswaBoarding = new TambahSiswaBoardingPage(driver);
        tambahSiswaBoarding.waitUntilLoaded();
        String currentUrl = tambahSiswaBoarding.getCurrentUrl();
        System.out.println("[Tambah Siswa] Current URL: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/tambah-siswa", currentUrl);
    }

    @When("pengguna menginputkan data boarding siswa")
    public void isiFormulirDataBoardingValid() {
        tambahSiswaBoarding = new TambahSiswaBoardingPage(driver);
        System.out.println("[Tambah Siswa] Mengisi data valid untuk siswa: Eka");
        tambahSiswaBoarding.setCariSiswaField("Eka");
        tambahSiswaBoarding.setSiswaFromDropdown();
        tambahSiswaBoarding.setTanggalMulaiField("07-01-2025");
        tambahSiswaBoarding.setTanggalSelesaiField("07-31-2025");
        tambahSiswaBoarding.setTagihanField("1000000");
        tambahSiswaBoarding.selectJenisTagihan("Boarding");
        tambahSiswaBoarding.setCatatanField("Mengikuti program Boarding");
    }

    @When("pengguna menginputkan data boarding siswa yang tidak valid")
    public void isiFormulirDataBoardingTidakValid() {
        tambahSiswaBoarding = new TambahSiswaBoardingPage(driver);
        System.out.println("[Tambah Siswa] Mengisi data TIDAK valid untuk siswa: Linda");
        tambahSiswaBoarding.setCariSiswaField("Linda");
        tambahSiswaBoarding.setSiswaFromDropdown();
        tambahSiswaBoarding.setTanggalMulaiField("");
        tambahSiswaBoarding.setTanggalSelesaiField("");
        tambahSiswaBoarding.setTagihanField("1500000");
        tambahSiswaBoarding.selectJenisTagihan("Boarding");
        tambahSiswaBoarding.setCatatanField("Mengikuti program Boarding");
    }

    @When("klik tombol simpan siswa")
    public void klikTombolSimpanSiswa() {
        tambahSiswaBoarding = new TambahSiswaBoardingPage(driver);
        tambahSiswaBoarding.clickSimpanSiswaButton();
        System.out.println("[Tambah Siswa] Klik tombol Simpan");
    }

    @Then("pengguna diarahkan kembali ke halaman monitoring boarding")
    public void verifikasiKembaliKeHalamanMonitoring() {
        monitoringBoarding = new MonitoringBoardingPage(driver);
        monitoringBoarding.waitUntilLoaded();
        String currentUrl = monitoringBoarding.getCurrentUrl();
        System.out.println("[Monitoring] Kembali ke halaman monitoring, URL: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi", currentUrl);
    }

    @Then("data boarding siswa ditampilkan")
    public void verifikasiDataSiswaDitampilkan() {
        monitoringBoarding = new MonitoringBoardingPage(driver);
        monitoringBoarding.waitUntilLoaded();
        boolean ditemukan = monitoringBoarding.isNamaSiswaOnTabelMonitoringBoarding("Linda");
        System.out.println("[Monitoring] Apakah nama siswa 'Linda' muncul di tabel? " + ditemukan);
        Assertions.assertTrue(ditemukan);
    }

    @Then("pengguna tetap berada di halaman tambah siswa boarding")
    public void verifikasiDataSiswaTidakTersimpan() {
        tambahSiswaBoarding = new TambahSiswaBoardingPage(driver);
        tambahSiswaBoarding.waitUntilLoaded();
        String currentUrl = tambahSiswaBoarding.getCurrentUrl();
        System.out.println("[Tambah Siswa] Current URL: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/tambah-siswa", currentUrl);
    }

    @Then("pengguna melihat pesan error validasi")
    public void verifikasiPesanErrorValidasi() {
        tambahSiswaBoarding = new TambahSiswaBoardingPage(driver);
        tambahSiswaBoarding.waitUntilLoaded();
        String errorMessage = tambahSiswaBoarding.getPesanError();
        System.out.println("[Tambah Siswa] Error Message: " + errorMessage);
        Assertions.assertEquals("Semua field wajib diisi!", errorMessage);
    }
}
