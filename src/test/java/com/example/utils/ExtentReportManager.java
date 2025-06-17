package com.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    public static ExtentReports getInstance() {
        if (extent == null) {
            if(extent == null) {
                String reportPath = System.getProperty("user.dir") + "/test-output/GenerateExtentReport.html";
                sparkReporter = new ExtentSparkReporter(reportPath);
                sparkReporter.config().setDocumentTitle("Automation Test Report");
                sparkReporter.config().setReportName("Automation Test Report");
                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);
                extent.setSystemInfo("OS", System.getProperty("os.name"));
                extent.setSystemInfo("Browser", "Chrome");
                extent.setSystemInfo("Tester", "Annisa & Belda");
                extent.setSystemInfo("Environment", "Staging");
            }
        }
        return extent;
    }
}
