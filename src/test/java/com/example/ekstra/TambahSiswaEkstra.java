package com.example.ekstra;

import com.example.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DashboardPage;
import org.example.ekstra.monitoringEkstraPage;
import org.example.ekstra.tambahSiswaEkstraPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TambahSiswaEkstra {
    private final TestContext context;
    private WebDriver driver;
    private WebDriverWait wait;

    DashboardPage dashboard;
    monitoringEkstraPage monitoringEkstra;
    tambahSiswaEkstraPage tambahSiswaEkstra;

    public TambahSiswaEkstra(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        tambahSiswaEkstra.isiNama("100011");
        tambahSiswaEkstra.klikAutocompletePertama();
        tambahSiswaEkstra.klikTambah();
        tambahSiswaEkstra.klikEkstra0();
        tambahSiswaEkstra.pilihJenisEkstraKedua();
        tambahSiswaEkstra.isiTanggalMulai("10102024");
        tambahSiswaEkstra.isiTanggalSelesai("12122025");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit-tambah-siswa-ekstra")));
        submitButton.click();

        System.out.println("[Ekstra] Klik tombol Simpan setelah isi data");
    }

    @Then("User diarahkan kembali ke halaman monitoring Ekstrakurikuler")
    public void verifikasiKembaliKeHalamanMonitoringEkstra() {
        wait.until(ExpectedConditions.urlToBe("https://fe-fintrack.vercel.app/ekstra"));
        String currentUrl = driver.getCurrentUrl();
        System.out.println("[Monitoring Ekstra] URL saat ini: " + currentUrl);
        Assertions.assertEquals("https://fe-fintrack.vercel.app/ekstra", currentUrl);
    }

    @When("User menginputkan data ekstra siswa yang tidak valid")
    public void isiDataEkstraTidakLengkap() {
        tambahSiswaEkstra.isiNama("100011");
        tambahSiswaEkstra.klikAutocompletePertama();
        tambahSiswaEkstra.klikTambah();
        tambahSiswaEkstra.klikEkstra0();
        tambahSiswaEkstra.pilihJenisEkstraKedua();
        tambahSiswaEkstra.isiTanggalMulai("101020");
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit-tambah-siswa-ekstra")));
        submitButton.click();

        System.out.println("[Ekstra] Klik tombol Simpan setelah isi data");
    }

    @Then("sistem menampilkan pesan kesalahan validasi")
    public void tampilkanPesanValidasi() {
        Assertions.assertTrue(tambahSiswaEkstra.apakahAdaPesanError(), "Validasi tidak muncul.");
        System.out.println("Pesan validasi: " + tambahSiswaEkstra.getPesanError());
    }
}
