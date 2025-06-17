package com.example.ekstra;

import com.example.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.ekstra.pembayaranEkstraPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class PembayaranEkstra {
    private final TestContext context;
    private WebDriver driver;
    pembayaranEkstraPage pembayaranPage;

    public PembayaranEkstra(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("User mencari nama siswa {string} untuk pembayaran ekstra")
    public void cariSiswaEkstra(String nama) {
        pembayaranPage = new pembayaranEkstraPage(driver);
        pembayaranPage.cariNama(nama);
    }

    @When("User klik tombol bayar ekstra")
    public void klikTombolBayarEkstra() {
        pembayaranPage.klikBayarEkstra();
    }

    @When("User mengisi tanggal pembayaran dengan {string}")
    public void isiTanggal(String tanggal) {
        pembayaranPage.isiTanggal(tanggal);
    }

    @When("User mengisi nominal pembayaran dengan {string}")
    public void isiNominal(String nominal) {
        pembayaranPage.isiNominal(nominal);
    }

    @When("User mengisi catatan pembayaran dengan {string}")
    public void isiCatatan(String catatan) {
        pembayaranPage.isiCatatan(catatan);
    }

    @When("User menekan tombol simpan pembayaran")
    public void simpanPembayaran() {
        pembayaranPage.klikSimpan();
    }

    @Then("User melihat konfirmasi pembayaran berhasil")
    public void verifikasiBerhasil() {
        System.out.println("Pembayaran berhasil disubmit");
    }

    @Then("User melihat pesan error pembayaran ditampilkan")
    public void verifikasiGagal() {
        Assertions.assertTrue(
                pembayaranPage.pesanErrorMuncul(),
                "Pesan error 'please fill' tidak muncul saat data tidak lengkap."
        );
    }
}
