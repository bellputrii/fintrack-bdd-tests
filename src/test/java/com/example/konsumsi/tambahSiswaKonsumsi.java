package com.example.konsumsi;

import com.example.support.TestContext;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.konsumsi.dashboardPage;
import org.example.konsumsi.monitoringKonsumsiPage;
import org.example.konsumsi.tambahSiswaKonsumsiPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class tambahSiswaKonsumsi {
    private final TestContext context;
    private WebDriver driver;

    dashboardPage dashboard;
    monitoringKonsumsiPage monitoringKonsumsi;
    tambahSiswaKonsumsiPage tambahSiswaKonsumsi;

    public tambahSiswaKonsumsi(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("User klik tombol konsumsi")
    public void navigateToMonitoringKonsumsiPage() {
        dashboard = new dashboardPage(driver);
        System.out.println("Current URL before click: " + driver.getCurrentUrl());
        System.out.println("Page Source contains pendapatan-button? " + driver.getPageSource().contains("pendapatan-button"));
        dashboard.clickPendapatanButton();
        dashboard.waitUntilKonsumsiButtonVisible();
        System.out.println("Current URL after click pendapatan button: " + driver.getCurrentUrl());
        System.out.println("Page Source contains pendapatan-boarding-konsumsi-link? " + driver.getPageSource().contains("pendapatan-boarding-konsumsi-link"));
        dashboard.clickKonsumsiButton();
        System.out.println("Konsumsi button clicked successfully");
    }

    @Then("User diarahkan ke halaman monitoring konsumsi")
    public void redirectToMonitoringKonsumsiPageSuccess() {
        System.out.println("Inside redirectToMonitoringKonsumsiPageSuccess function");
        monitoringKonsumsi = new monitoringKonsumsiPage(driver);
        monitoringKonsumsi.waitUntilLoaded();
        String currentUrl = monitoringKonsumsi.getCurrentUrl();
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi", currentUrl);
    }

    @When("User klik tombol tambah siswa")
    public void navigateToTambahSiswaKonsumsiPage() {
        monitoringKonsumsi = new monitoringKonsumsiPage(driver);
        monitoringKonsumsi.clickTambahSiswaButton();
        System.out.println("Tambah siswa button clicked successfully");
    }

    @Then("User diarahkan ke halaman tambah siswa konsumsi")
    public void redirectToTambahSiswaKonsumsiPageSuccess() {
        System.out.println("Inside redirectToTambahSiswaKonsumsiPageSuccess function");
        tambahSiswaKonsumsi = new tambahSiswaKonsumsiPage(driver);
        tambahSiswaKonsumsi.waitUntilLoaded();
        String currentUrl = tambahSiswaKonsumsi.getCurrentUrl();
        Assertions.assertEquals("https://fe-fintrack.vercel.app/pendapatan/boarding-konsumsi/tambah-siswa", currentUrl);
    }
}
