package com.example.ekstra;

import com.example.support.TestContext;
import io.cucumber.java.Before;
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
        System.out.println("Current URL before click: " + driver.getCurrentUrl());
        System.out.println("Page Source contains ekstra-link? " + driver.getPageSource().contains("ekstra-link"));
        dashboard.clickEKstraButton();
        System.out.println("Current URL after click ekstra button: " + driver.getCurrentUrl());
    }

    @Then("User diarahkan ke halaman monitoring Ekstrakurikuler")
    public void redirectToMonitoringExtraPageSuccess() {
        System.out.println("Inside redirectToMonitoringEkstraPageSuccess function");
        monitoringEkstra = new monitoringEkstraPage(driver);
        monitoringEkstra.waitUntilLoaded();
        String currentUrl = monitoringEkstra.getCurrentUrl();
        Assertions.assertEquals("https://fe-fintrack.vercel.app/ekstra", currentUrl);
    }

    @When("User klik tombol tambah siswa")
    public void navigateToTambahSiswaEkstraPage() {
        monitoringEkstra = new monitoringEkstraPage(driver);
        monitoringEkstra.clikTambahKontrak();
        System.out.println("Tambah siswa ekstra button clicked successfully");
    }

    @Then("User diarahkan ke halaman tambah siswa ekstrakurikuler")
    public void redirectToTambahSiswaKonsumsiPageSuccess() {
        System.out.println("Inside redirectTambahEkstrakurikulerPage Success");
        tambahSiswaEkstra = new tambahSiswaEkstraPage(driver);
        tambahSiswaEkstra.waitUntilLoaded();
        String currentUrl = tambahSiswaEkstra.getCurrentUrl();
        Assertions.assertEquals("https://fe-fintrack.vercel.app/ekstra/tambah-siswa", currentUrl);
    }
}
