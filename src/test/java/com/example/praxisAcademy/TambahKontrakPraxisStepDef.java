package com.example.praxisAcademy;

import com.example.support.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DashboardPage;
import org.example.konsumsi.MonitoringKonsumsiPage;
import org.example.praxisAcademy.MonitoringPraxisPage;
import org.example.praxisAcademy.TambahKontrakSiswaPraxisPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class TambahKontrakPraxisStepDef {
    private final TestContext context;
    private WebDriver driver;

    DashboardPage dashboard;
    MonitoringPraxisPage monitoringPraxis;
    TambahKontrakSiswaPraxisPage tambahKontrakSiswaPraxisPage;

    public TambahKontrakPraxisStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("pengguna klik tombol praxis academy di dashboard")
    public void clickTambahKontrakButton() {
        dashboard = new DashboardPage(driver);
        System.out.println("[Dashboard] Current URL: " + driver.getCurrentUrl());

        dashboard.clickPendapatanButton();
        dashboard.waitUntilPraxisAcademyButtonVisible();
        dashboard.clickPraxisButton();
        System.out.println("[Dashboard] Praxis Academy button clicked");
    }

    @Then("pengguna diarahkan ke halaman monitoring praxis academy")
    public void verifikasiHalamanMonitoringPraxisAcademy() {
        monitoringPraxis = new MonitoringPraxisPage(driver);
        monitoringPraxis.waitUntilLoadedPraxis();
        String currentUrl = monitoringPraxis.getCurrentUrlPraxis();
        System.out.println("[Monitoring] Current URL: " + currentUrl);
        Assertions.assertTrue(monitoringPraxis.isOnMonitoringPraxisPage());
    }

    //    @Then("pengguna diarahkan kembali ke halaman monitoring praxis academy")
//    public void verifikasiKembaliKeHalamanMonitoringPraxis() {
//        monitoringPraxisPage = new MonitoringPraxisPage(driver);
//        monitoringPraxisPage.waitUntilLoadedPraxis();
//        String currentUrl = monitoringPraxisPage.getCurrentUrlPraxis();
//        System.out.println("[Monitoring Praxis] Kembali ke halaman monitoring, URL: " + currentUrl);
//        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/praxis", currentUrl);
//    }

    @When("pengguna klik tombol tambah kontrak siswa praxis")
    public void klikTombolKontrakSiswaPraxis() {
        monitoringPraxis = new MonitoringPraxisPage(driver);
        monitoringPraxis.clickAddKontrakPraxisButton();
        System.out.println("[Monitoring] Tambah kontrak siswa button clicked");
    }

    @Then("pengguna diarahkan ke halaman tambah kontrak siswa praxis academy")
    public void verifikasiHalamanTambahKontrakPraxisAcademy() {
        tambahKontrakSiswaPraxisPage = new TambahKontrakSiswaPraxisPage(driver);
        tambahKontrakSiswaPraxisPage.waitUntilLoaded();
        String currentUrl = tambahKontrakSiswaPraxisPage.getCurrentUrl();
        System.out.println("[Tambah Siswa] Current URL: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/praxis/tambah-kontrak", currentUrl);
    }

    @When("pengguna menginputkan data kontrak siswa praxis academy yang valid")
    public void isiFormulirDataPraxisAcademyValid() {
        tambahKontrakSiswaPraxisPage = new TambahKontrakSiswaPraxisPage(driver);
        System.out.println("[Tambah Kontrak] Mengisi data valid untuk siswa: Citra");
        tambahKontrakSiswaPraxisPage.setCariSiswaField("Citra");
        tambahKontrakSiswaPraxisPage.setSiswaPraxisFromDropdown();
        tambahKontrakSiswaPraxisPage.setUangKBM("2000000");
        tambahKontrakSiswaPraxisPage.setUangSPP("2500000");
        tambahKontrakSiswaPraxisPage.setUangPemeliharaan("1500000");
        tambahKontrakSiswaPraxisPage.setUangSumbangan("4000000");
        tambahKontrakSiswaPraxisPage.unggahFileKontrakHeadless();
        tambahKontrakSiswaPraxisPage.setCatatanField("Kontrak siswa selama 1 tahun");
    }

    @When("pengguna menginputkan data kontrak siswa praxis academy tanpa file")
    public void isiFormulirDataPraxisAcademyTidakValid() {
        tambahKontrakSiswaPraxisPage = new TambahKontrakSiswaPraxisPage(driver);
        System.out.println("[Tambah Kontrak] Mengisi data tanpa file untuk siswa: Iqbal");
        tambahKontrakSiswaPraxisPage.setCariSiswaField("Taufik");
        tambahKontrakSiswaPraxisPage.setSiswaPraxisFromDropdown();
        tambahKontrakSiswaPraxisPage.setUangKBM("2000000");
        tambahKontrakSiswaPraxisPage.setUangSPP("2500000");
        tambahKontrakSiswaPraxisPage.setUangPemeliharaan("1500000");
        tambahKontrakSiswaPraxisPage.setUangSumbangan("4000000");
        tambahKontrakSiswaPraxisPage.setCatatanField("Kontrak siswa selama 1 tahun");
    }

    @When("pengguna klik tombol simpan kontrak praxis")
    public void klikTombolSimpanKontrakPraxis() {
        tambahKontrakSiswaPraxisPage = new TambahKontrakSiswaPraxisPage(driver);
        tambahKontrakSiswaPraxisPage.clickSimpanKontrakPraxis();
        System.out.println("[Tambah Kontrak] Klik tombol Simpan");
    }



    @Then("data kontrak siswa praxis academy ditampilkan di tabel")
    public void verifikasiDataKontrakDitampilkan() {
        monitoringPraxis = new MonitoringPraxisPage(driver);
        monitoringPraxis.waitUntilLoadedPraxis();
        boolean ditemukan = monitoringPraxis.isSiswaDisplayed("Iqbal");
        System.out.println("[Monitoring Praxis] Apakah nama siswa 'Iqbal' muncul di tabel? " + ditemukan);
        Assertions.assertTrue(ditemukan);
    }

    @Then("pengguna tetap berada di halaman tambah kontrak siswa praxis academy")
    public void verifikasiDataSiswaTidakTersimpan() {
        tambahKontrakSiswaPraxisPage = new TambahKontrakSiswaPraxisPage(driver);
        tambahKontrakSiswaPraxisPage.waitUntilLoaded();
        String currentUrl = tambahKontrakSiswaPraxisPage.getCurrentUrl();
        System.out.println("[Tambah Kontrak] Current URL: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/praxis/tambah-kontrak", currentUrl);
    }

    @Then("pengguna melihat pesan error validasi file wajib diisi")
    public void verifikasiPesanErrorValidasiPraxis() {
        tambahKontrakSiswaPraxisPage = new TambahKontrakSiswaPraxisPage(driver);
        tambahKontrakSiswaPraxisPage.waitUntilLoaded();
        String errorMessage = tambahKontrakSiswaPraxisPage.getPesanErrorPraxis();
        System.out.println("[Tambah Kontrak] Error Message: " + errorMessage);
        Assertions.assertEquals("Semua field wajib diisi!", errorMessage);
    }

    @And("pengguna melihat pesan sukses kontrak siswa praxis")
    public void penggunaMelihatPesanSuksesKontrakSiswaPraxis() {
        monitoringPraxis = new MonitoringPraxisPage(driver);
        monitoringPraxis.waitUntilLoaded();
        monitoringPraxis.printAllVisibleTextElements();
        Assertions.assertEquals("Data siswa berhasil ditambahkan!", monitoringPraxis.getSuccessMessage());
        System.out.println("[Monitoring] Pesan sukses data siswa ditampilkan");
    }
}