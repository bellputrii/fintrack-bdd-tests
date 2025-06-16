package com.example.praxisAcademy;

import com.example.support.TestContext;
import io.cucumber.java.en.*;
import org.example.praxisAcademy.MonitoringPraxisPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class TambahKontrakPraxisStepDef {

    private final TestContext context;
    private WebDriver driver;

    MonitoringPraxisPage praxisPage;

    public TambahKontrakPraxisStepDef(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @Given("pengguna sudah login dan berada di halaman monitoring Praxis")
    public void penggunaSudahLoginDanBeradaDiHalamanMonitoringPraxis() {
        praxisPage = new MonitoringPraxisPage(driver);
        praxisPage.waitUntilLoaded();
        System.out.println("[Praxis] Current URL: " + driver.getCurrentUrl());
    }

    @And("pengguna mengklik tombol Pendapatan")
    public void klikPendapatan() {
        praxisPage = new MonitoringPraxisPage(driver);
        praxisPage.klikPendapatan();
        System.out.println("[Praxis] Klik tombol Pendapatan");
    }

    @And("pengguna memilih link Praxis Academy")
    public void klikPraxisAcademy() {
        praxisPage = new MonitoringPraxisPage(driver);
        praxisPage.klikPraxisAcademy();
        System.out.println("[Praxis] Klik link Praxis Academy");
    }

    @And("pengguna mengklik tombol Tambah Kontrak Praxis")
    public void klikTambahKontrak() {
        praxisPage = new MonitoringPraxisPage(driver);
        praxisPage.klikTambahKontrak();
        System.out.println("[Praxis] Klik tombol Tambah Kontrak");
    }

    @And("pengguna mengisi dan memilih nama siswa")
    public void isiNamaSiswa() {
        praxisPage = new MonitoringPraxisPage(driver);
        praxisPage.setCariSiswaField("Eka Putra");
        praxisPage.setSiswaFromDropdown();
        System.out.println("[Praxis] Isi nama siswa: Eka Putra");
    }

    @And("pengguna mengisi semua field tagihan")
    public void isiFieldTagihan() {
        praxisPage = new MonitoringPraxisPage(driver);
        praxisPage.isiFieldTagihan();
        System.out.println("[Praxis] Isi field tagihan");
    }

    @And("pengguna mengisi catatan")
    public void isiCatatan() {
        praxisPage = new MonitoringPraxisPage(driver);
        praxisPage.isiCatatan("Kontrak untuk siswa Eka Putra - bulan ini.");
        System.out.println("[Praxis] Isi catatan kontrak");
    }

    @And("pengguna mengunggah file kontrak PDF yang valid berukuran < 5 MB")
    public void unggahFileKontrak() {
        praxisPage = new MonitoringPraxisPage(driver);
        String filePath = "C:\\Users\\user\\Downloads\\tagihan_Eka Putra.pdf";
        praxisPage.unggahFileKontrak(filePath);
        System.out.println("[Praxis] Upload file kontrak: " + filePath);
    }

    @And("pengguna mengklik tombol simpan kontrak")
    public void klikSimpanKontrak() {
        praxisPage = new MonitoringPraxisPage(driver);
        praxisPage.klikSimpanKontrak();
        System.out.println("[Praxis] Klik tombol Simpan Kontrak");
    }

    @Then("sistem menampilkan notifikasi bahwa kontrak siswa berhasil ditambahkan")
    public void verifikasiNotifikasiKontrakBerhasil() {
        praxisPage = new MonitoringPraxisPage(driver);
        String notifikasi = praxisPage.getNotifikasiBerhasil();
        System.out.println("[Praxis] Notifikasi: " + notifikasi);
        Assertions.assertEquals("Kontrak berhasil ditambahkan.", notifikasi);
    }
}
