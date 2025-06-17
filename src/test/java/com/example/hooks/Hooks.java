package com.example.hooks;

import com.example.support.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.edge.EdgeDriver;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setup() {
        if (context.getDriver() == null) {
            context.setDriver(new EdgeDriver());
        }
    }

    @After
    public void tearDown() {
        context.quitDriver();
    }
}
