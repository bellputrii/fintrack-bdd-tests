package com.example.tagihan;

import com.example.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DashboardPage;
import org.example.tagihan.TagihanPage;
import org.example.tagihan.TambahTagihanPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class BuatTagihanStepDef {
    private final TestContext context;
    private WebDriver driver;

    DashboardPage dashboard;
    TagihanPage tagihanPage;
    TambahTagihanPage tambahTagihanPage;

    public BuatTagihanStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("pengguna klik tombol tagihan di dashboard")
    public void klikTombolTagihanDiDashboard() {
        dashboard = new DashboardPage(driver);
        dashboard.clickTagihanButton();
        System.out.println("[Dashboard] Klik tombol Tagihan");
    }

    @Then("pengguna diarahkan ke halaman monitoring tagihan siswa")
    public void verifikasiHalamanMonitoringTagihan() {
        tagihanPage = new TagihanPage(driver);
        tagihanPage.waitUntilLoaded();
        Assertions.assertTrue(tagihanPage.isOnTagihanPage());
        System.out.println("[Tagihan] Berada di halaman monitoring tagihan siswa");
    }

    @When("pengguna klik tombol tambah tagihan")
    public void klikTombolTambahTagihan() {
        tagihanPage = new TagihanPage(driver);
        tagihanPage.clickTambahTagihan();
        System.out.println("[Tagihan] Klik tombol tambah tagihan");
    }

    @Then("pengguna diarahkan ke halaman buat tagihan")
    public void verifikasiHalamanBuatTagihan() {
        tambahTagihanPage = new TambahTagihanPage(driver);
        tambahTagihanPage.waitUntilLoaded();
        Assertions.assertTrue(driver.getCurrentUrl().contains("/tagihan/add"));
        System.out.println("[Tagihan] Berada di halaman buat tagihan");
    }

    @When("pengguna menginputkan data tagihan siswa valid")
    public void isiFormulirTagihanSiswaValid() {
        tambahTagihanPage = new TambahTagihanPage(driver);
        System.out.println("[Tagihan] Mengisi data tagihan siswa valid: Nur");
        tambahTagihanPage.setCariSiswaField("Nur");
        tambahTagihanPage.setSiswaFromDropdown();
        tambahTagihanPage.setSemester("2");
        tambahTagihanPage.setPeriode("2024/2025");
        tambahTagihanPage.setTanggalTagihan("05-01-2025");
        tambahTagihanPage.setJatuhTempo("06-04-2025");
        tambahTagihanPage.setKBM("1000000");
        tambahTagihanPage.setSPP("500000");
        tambahTagihanPage.setPemeliharaan("200000");
        tambahTagihanPage.setSumbangan("150000");
        tambahTagihanPage.setKonsumsi("100000");
        tambahTagihanPage.setBoarding("250000");
        tambahTagihanPage.setEkstra("50000");
        tambahTagihanPage.setUangBelanja("30000");
        tambahTagihanPage.setTunggakan("0");
        tambahTagihanPage.setCatatan("Tagihan semester genap");
    }

    @When("pengguna menginputkan data tagihan siswa tidak valid")
    public void isiFormulirTagihanSiswaTidakValid() {
        tambahTagihanPage = new TambahTagihanPage(driver);
        System.out.println("[Tagihan] Mengisi data tagihan siswa tidak valid (kosong)");
        // Tidak mengisi apapun atau hanya mengisi sebagian field
        tambahTagihanPage.setCariSiswaField("");
        // Field lain dibiarkan kosong
    }

    @When("klik tombol simpan dan cetak tagihan")
    public void klikTombolSimpanCetakTagihan() {
        tambahTagihanPage = new TambahTagihanPage(driver);
        tambahTagihanPage.clickSimpanCetakTagihan();
        System.out.println("[Tagihan] Klik tombol Simpan & Cetak Tagihan");
    }

    @Then("pengguna diarahkan kembali ke halaman monitoring tagihan siswa")
    public void verifikasiKembaliKeHalamanMonitoringTagihan() {
        tagihanPage = new TagihanPage(driver);
        tagihanPage.waitUntilLoaded();
        Assertions.assertTrue(tagihanPage.isOnTagihanPage());
        System.out.println("[Tagihan] Kembali ke halaman monitoring tagihan siswa");
    }

    @Then("data cetak tagihan siswa ditampilkan di tabel monitoring tagihan")
    public void verifikasiDataTagihanDitampilkan() {
        tagihanPage = new TagihanPage(driver);
        tagihanPage.waitUntilLoaded();
        boolean ditemukan = tagihanPage.isTagihanDisplayed("Nur Aini");
        System.out.println("[Tagihan] Apakah nama siswa 'Nur' muncul di tabel? " + ditemukan);
        Assertions.assertTrue(ditemukan);
    }

    @Then("pengguna tetap dihalaman buat tagihan")
    public void verifikasiTetapDiHalamanBuatTagihan() {
        tambahTagihanPage = new TambahTagihanPage(driver);
        tambahTagihanPage.waitUntilLoaded();
        Assertions.assertTrue(driver.getCurrentUrl().contains("/tagihan/add"));
        System.out.println("[Tagihan] Tetap di halaman buat tagihan (validasi gagal)");
    }

    @Then("muncul pesan error {string}")
    public void verifikasiPesanErrorTagihan(String pesan) {
        tambahTagihanPage = new TambahTagihanPage(driver);
        tambahTagihanPage.waitUntilLoaded();
        String errorMessage = tambahTagihanPage.getPesanError();
        System.out.println("[Tagihan] Error Message: " + errorMessage);
        Assertions.assertEquals(pesan, errorMessage);
    }
}