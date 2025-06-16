package com.example.tagihan;

import com.example.support.TestContext;
import io.cucumber.java.en.*;
import org.example.tagihan.DashboardPage;
import org.example.tagihan.FormTagihanPage;
import org.example.tagihan.RiwayatTagihanPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class BuatTagihanStepDef {

    private final TestContext context;
    private final WebDriver driver;

    private RiwayatTagihanPage riwayatTagihanPage;
    private FormTagihanPage formTagihanPage;
    private DashboardPage dashboardPage;

    public BuatTagihanStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
        this.dashboardPage = new DashboardPage(driver);
    }

    @When("pengguna klik tombol tagihan")
    public void pengguna_klik_tombol_tagihan() {
        dashboardPage = new DashboardPage(driver);
        dashboardPage.waitUntilTagihanButtonVisible();
        dashboardPage.clickTagihanButton();
        System.out.println("[Dashboard] Tagihan button clicked");
    }

    @Then("pengguna diarahkan ke halaman riwayat tagihan siswa")
    public void pengguna_diarahkan_ke_halaman_riwayat_tagihan() {
        riwayatTagihanPage = new RiwayatTagihanPage(driver);
        riwayatTagihanPage.waitUntilLoaded();

        String currentUrl = riwayatTagihanPage.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("/tagihan"),
                "Halaman riwayat tagihan tidak terbuka. Current URL: " + currentUrl);
    }

    @When("pengguna diarahkan ke halaman form tagihan siswa")
    public void pengguna_diarahkan_ke_halaman_form_tagihan_siswa() {
        formTagihanPage = new FormTagihanPage(driver);
        formTagihanPage.waitUntilLoaded();
        Assertions.assertTrue(formTagihanPage.getCurrentUrl().contains("/tagihan"),
                "Tidak diarahkan ke halaman form tagihan. URL: " + formTagihanPage.getCurrentUrl());
    }

    @Then("pengguna diarahkan ke halaman form buat tagihan")
    public void pengguna_diarahkan_ke_form_tagihan() {
        formTagihanPage = new FormTagihanPage(driver);
        formTagihanPage.waitUntilLoaded();
        Assertions.assertTrue(formTagihanPage.getCurrentUrl().contains("/tagihan"),
                "Tidak berada di halaman form tagihan. Current URL: " + formTagihanPage.getCurrentUrl());
    }

    @When("pengguna menginputkan data siswa")
    public void pengguna_menginputkan_data() {
        formTagihanPage = new FormTagihanPage(driver);
        formTagihanPage.waitUntilLoaded();

        formTagihanPage.isiFieldNisn("Linda");
        formTagihanPage.isiFieldSemester("2");
        formTagihanPage.isiFieldPeriode("2");
        formTagihanPage.isiFieldTanggalTagihan("2025-06-16");
        formTagihanPage.isiFieldJatuhTempo("2025-06-30");
        formTagihanPage.isiFieldKBM("3000000");
        formTagihanPage.isiFieldSPP("3000000");
        formTagihanPage.isiFieldPemeliharaan("3000000");
        formTagihanPage.isiFieldSumbangan("3000000");
        formTagihanPage.isiFieldKonsumsi("0");
        formTagihanPage.isiFieldBoarding("0");
        formTagihanPage.isiFieldEkstrakurikuler("0");
        formTagihanPage.isiFieldUangBelanja("0");
        formTagihanPage.isiFieldTunggakan("0");
        formTagihanPage.isiFieldCatatan("Tagihan dengan input yang valid");

        System.out.println("[FormTagihanPage] Data siswa berhasil diinputkan");
    }
}
