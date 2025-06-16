package com.example.ekstra;

import com.example.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DashboardPage;
import org.example.ekstra.monitoringEkstraPage;
import org.example.ekstra.tambahSiswaEkstraPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class TambahSiswaEkstra {
    private final TestContext context;
    private WebDriver driver;

    DashboardPage dashboard;
    monitoringEkstraPage monitoringEkstra;
    tambahSiswaEkstraPage tambahSiswaEkstra;

    public TambahSiswaEkstra(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("User klik tombol ekstrakurikuler")
    public void navigateToEkstrakurikulerPage() {
        dashboard = new DashboardPage(driver);
        dashboard.clickEKstraButton();
    }

    @Then("User diarahkan ke halaman monitoring Ekstrakurikuler")
    public void redirectToMonitoringExtraPageSuccess() {
        monitoringEkstra = new monitoringEkstraPage(driver);
        monitoringEkstra.waitUntilLoaded();
        String currentUrl = monitoringEkstra.getCurrentUrl();
        Assertions.assertEquals("https://fe-fintrack.vercel.app/ekstra", currentUrl);
    }

    @When("User klik tombol tambah siswa")
    public void navigateToTambahSiswaEkstraPage() {
        monitoringEkstra = new monitoringEkstraPage(driver);
        monitoringEkstra.clikTambahKontrak();
    }

    @Then("User diarahkan ke halaman tambah siswa ekstrakurikuler")
    public void redirectToTambahSiswaEkstraPageSuccess() {
        tambahSiswaEkstra = new tambahSiswaEkstraPage(driver);
        tambahSiswaEkstra.waitUntilLoaded();
        String currentUrl = tambahSiswaEkstra.getCurrentUrl();
        Assertions.assertEquals("https://fe-fintrack.vercel.app/ekstra/tambah-siswa", currentUrl);
    }

    @When("User menginputkan data ekstra siswa")
    public void isiFormulirData() {
        tambahSiswaEkstra = new tambahSiswaEkstraPage(driver);
        tambahSiswaEkstra.isiNama("Ali");
        tambahSiswaEkstra.klikTambah();
        tambahSiswaEkstra.klikEkstra0();
        tambahSiswaEkstra.pilihJenisEkstraKedua();
        tambahSiswaEkstra.isiTanggalMulai("2025-07-01");
        tambahSiswaEkstra.isiTanggalSelesai("2025-12-01");
    }

    @Then("User diarahkan kembali ke halaman monitoring Ekstrakurikuler")
    public void verifikasiKembaliKeHalamanMonitoringEkstra() {
        monitoringEkstra = new monitoringEkstraPage(driver);
        monitoringEkstra.waitUntilLoaded();
        String currentUrl = monitoringEkstra.getCurrentUrl();
        Assertions.assertEquals("https://fe-fintrack.vercel.app/ekstra", currentUrl);
    }

    @When("User tidak mengisi nama siswa dan menekan tombol tambah")
    public void userTidakIsiNamaDanKlikTambah() {
        tambahSiswaEkstra = new tambahSiswaEkstraPage(driver);
        tambahSiswaEkstra.isiNama(""); // Kosongkan nama
        tambahSiswaEkstra.klikTambah();
    }

    @Then("sistem menampilkan pesan error {string}")
    public void sistemMenampilkanPesanError(String expectedError) {
        tambahSiswaEkstra = new tambahSiswaEkstraPage(driver);
        Assertions.assertTrue(tambahSiswaEkstra.apakahAdaPesanError(), "Pesan error tidak muncul.");
        Assertions.assertEquals(expectedError, tambahSiswaEkstra.getPesanError());
    }
}
