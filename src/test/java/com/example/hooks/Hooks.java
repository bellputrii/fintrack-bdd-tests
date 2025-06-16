package com.example.hooks;

import com.example.support.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        System.out.println("[HOOK] Persiapan sebelum scenario dijalankan");

        // Bisa juga kamu login otomatis di sini
        // atau clear state duluan jika dibutuhkan
    }

    @After
    public void tearDown() {
        System.out.println("[HOOK] Cleanup sesudah scenario");
    }
}
