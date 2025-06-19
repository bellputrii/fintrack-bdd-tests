package com.example.ekstra;

import com.example.support.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.ekstra.pembayaranEkstraPage;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Assertions;

public class PembayaranEkstra {
    private final TestContext context;
    private final pembayaranEkstraPage pembayaranPage;

    public PembayaranEkstra(TestContext context) {
        this.context = context;
        WebDriver driver = context.getDriver();
        this.pembayaranPage = new pembayaranEkstraPage(driver);
    }

    @When("User memilih Level X dari dropdown untuk pembayaran ekstra")
    public void user_memilih_level_x_dropdown() {
        pembayaranPage.pilihLevelX();
    }

    @And("User klik tombol bayar ekstra")
    public void klikTombolBayarEkstra() {
        pembayaranPage.klikBayarEkstra();
    }

    @And("User mengisi tanggal pembayaran dengan {string}")
    public void isiTanggal(String tanggal) {
        pembayaranPage.isiTanggal(tanggal);
    }

    @And("User mengisi nominal pembayaran dengan {string}")
    public void isiNominal(String nominal) {
        pembayaranPage.isiNominal(nominal);
    }

    @And("User mengisi catatan pembayaran dengan {string}")
    public void isiCatatan(String catatan) {
        pembayaranPage.isiCatatan(catatan);
    }

    @And("User menekan tombol simpan pembayaran")
    public void simpanPembayaran() {
        pembayaranPage.simpanPembayaran();
    }

    @Then("User melihat konfirmasi pembayaran berhasil")
    public void verifikasiBerhasil() {
        Assertions.assertTrue(pembayaranPage.verifikasiPembayaranBerhasil());
    }
    
}
