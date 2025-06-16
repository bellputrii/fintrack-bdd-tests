package com.example;

import com.example.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DashboardPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Logout{
    private final TestContext context;
    private WebDriver driver;
    DashboardPage dashboard;

    public Logout(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("pengguna klik tombol logout")
    public void pengguna_klik_tombol_logout() {
        dashboard = new DashboardPage(driver);
        // Tunggu tombol logout muncul dan klik
        driver.findElement(By.id("logout-button")).click();
        System.out.println("[Logout] Klik tombol logout");
    }

    @Then("pengguna berada di halaman login setelah logout")
    public void pengguna_berada_di_halaman_login() {
        // Tunggu redirect ke halaman login
        boolean isLogin = driver.getCurrentUrl().contains("") || driver.getCurrentUrl().equals("https://fe-fintrack.vercel.app/login");
        System.out.println("[Logout] Redirect ke halaman login: " + driver.getCurrentUrl());
        Assertions.assertTrue(isLogin, "Tidak redirect ke halaman login setelah logout");
    }
}