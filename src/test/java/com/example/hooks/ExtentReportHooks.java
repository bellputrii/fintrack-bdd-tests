package com.example.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.example.utils.ExtentReportManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ExtentReportHooks {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest test = extent.createTest(scenario.getName());
        scenarioTest.set(test);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            scenarioTest.get().log(Status.FAIL, "Scenario failed: " + scenario.getName());
        } else {
            scenarioTest.get().log(Status.PASS, "Scenario passed: " + scenario.getName());
        }
    }

    @AfterAll
    public static void afterAll() {
        extent.flush();
    }

    public static ExtentTest getScenarioTest() {
        return scenarioTest.get();
    }
}
