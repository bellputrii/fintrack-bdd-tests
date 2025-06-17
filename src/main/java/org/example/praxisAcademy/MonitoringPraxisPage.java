package org.example.praxisAcademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class MonitoringPraxisPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MonitoringPraxisPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    // ======= Locator Elements =======
    private By levelSelect = By.id("level-select");
    private By searchInput = By.id("search-praxis");
    private By tambahKontrakButton = By.id("tambah-kontrak-praxis");
    private By tableRows = By.cssSelector("tbody tr");
    private By namaSiswaCell = By.xpath("//table/tbody/tr/td[1]");
    private By kontrakIcon = By.cssSelector("td svg#kontrak");
    private By bayarIcon = By.cssSelector("td svg#bayar");
    By successMessage = By.xpath("/html/body/div/div/main/div/div[2]/p");
//    By successMessage = By.xpath("//p[contains(text(), 'berhasil')]");
//    By successMessage = By.xpath("/html/body/div/div/main/div/div[3]");

    // ======= Interactions =======

    // MonitoringPraxisPage.java
    private By namaSiswaCells = By.xpath("//table/tbody/tr/td[1]"); // Langsung ambil semua kolom 1

    public boolean isSiswaDisplayed(String namaSiswa) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(namaSiswaCells));
        List<WebElement> namaCells = driver.findElements(namaSiswaCells);
        for (WebElement cell : namaCells) {
            if (cell.getText().trim().equalsIgnoreCase(namaSiswa)) {
                return true;
            }
        }
        return false;
    }

    public String getSuccessMessage() {
        isOnMonitoringPraxisPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

    public boolean isOnMonitoringPraxisPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(levelSelect));
        return driver.getCurrentUrl().contains("https://fe-fintrack.vercel.app/pendapatan/praxis");
    }

    By namaSiswaBaris = By.xpath("td[1]"); // perhatikan: ini hanya untuk dalam konteks <tr>
    By tabelBaris = By.xpath("//table/tbody/tr");

    public void clickPembayaranButtonPraxis(String namaSiswaTarget) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabelBaris));
        List<WebElement> rows = driver.findElements(tabelBaris);

        for (WebElement row : rows) {
            WebElement namaCell = row.findElement(namaSiswaBaris);
            if (namaCell.getText().toLowerCase().contains(namaSiswaTarget.toLowerCase())) {
                WebElement bayarButton = row.findElement(bayarIcon);
                bayarButton.click();
                System.out.println("[ACTION] Klik tombol bayar untuk siswa: " + namaSiswaTarget);
                return;
            }
        }

        throw new RuntimeException("Siswa dengan nama '" + namaSiswaTarget + "' tidak ditemukan di tabel.");
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahKontrakButton));
        wait.until(ExpectedConditions.urlContains("https://fe-fintrack.vercel.app/pendapatan/praxis"));
    }


//    public boolean isOnMonitoringPraxisPage() {
//        return driver.getCurrentUrl().contains("https://fe-fintrack.vercel.app/pendapatan/praxis");
//    }

    public String getPaymentStatus(String studentName, String paymentType) {
        try {
            // First find the student row
            int rowIndex = findStudentRowIndex(studentName);

            // Determine accessor key based on payment type
            String accessorKey;
            switch (paymentType.toLowerCase()) {
                case "kbm":
                    accessorKey = "tagihan_uang_kbm";
                    break;
                case "spp":
                    accessorKey = "tagihan_uang_spp";
                    break;
                case "pemeliharaan":
                    accessorKey = "tagihan_uang_pemeliharaan";
                    break;
                case "sumbangan":
                    accessorKey = "tagihan_uang_sumbangan";
                    break;
                default:
                    throw new IllegalArgumentException("Unknown payment type: " + paymentType);
            }

            // Build XPath for the status cell
            // Menggunakan XPath yang lebih spesifik untuk mencari span yang berisi status
            String xpath = String.format(
                    "//tr[contains(@data-row-key,'%d')]//td[@data-column-key='%s']//span[contains(@class,'text-green-600')]",
                    rowIndex, accessorKey);

            // Coba cari element dengan status "Lunas" (text-green-600)
            List<WebElement> lunasElements = driver.findElements(By.xpath(xpath));
            if (!lunasElements.isEmpty()) {
                return "Lunas";
            }

            // Jika tidak ditemukan status Lunas, cek nilai aktual
            xpath = String.format(
                    "//tr[contains(@data-row-key,'%d')]//td[@data-column-key='%s']//span",
                    rowIndex, accessorKey);

            WebElement statusCell = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            String value = statusCell.getText().trim();

            // Handle special cases
            if (value.equals("-")) {
                return "-";
            }

            // Jika berupa angka, berarti belum lunas
            if (value.matches("[0-9,.]+")) {
                return value;
            }

            return value; // Untuk kasus lainnya
        } catch (Exception e) {
            System.err.println("Error getting payment status for " + paymentType + ": " + e.getMessage());
            return "Error";
        }
    }

    private int findStudentRowIndex(String studentName) {
        // Find all student rows and locate the matching one
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("/html/body/div/div/main/div/div[3]/table/tbody/tr")));

        for (int i = 0; i < rows.size(); i++) {
            WebElement nameCell = rows.get(i).findElement(By.xpath("./td[2]")); // Assuming name is in column 2
            if (nameCell.getText().trim().equalsIgnoreCase(studentName)) {
                return i + 1; // XPath indices start at 1
            }
        }
        throw new NoSuchElementException("Student not found: " + studentName);
    }

    public void clickKontrakSiswaButton(String namaSiswaTarget) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            WebElement namaCell = row.findElement(namaSiswaCell);
            if (namaCell.getText().toLowerCase().contains(namaSiswaTarget.toLowerCase())) {
                WebElement riwayatButton = row.findElement(kontrakIcon);
                riwayatButton.click();
                System.out.println("[ACTION] Klik tombol riwayat pembayaran untuk siswa: " + namaSiswaTarget);
                return;
            }
        }

        throw new RuntimeException("Siswa dengan nama '" + namaSiswaTarget + "' tidak ditemukan di tabel.");
    }

    public void waitUntilLoadedPraxis() {
        wait.until(ExpectedConditions.urlContains("https://fe-fintrack.vercel.app/pendapatan/praxis"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahKontrakButton)); // optionally also wait for a unique element
    }

    public String getCurrentUrlPraxis() {
        return driver.getCurrentUrl();
    }

    public void selectLevel(String level) {
        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(levelSelect));
        select.findElement(By.xpath(".//option[. = '" + level + "']")).click();
    }

    public void clickAddKontrakPraxisButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tambahKontrakButton));
        driver.findElement(tambahKontrakButton).click();
    }

    public void searchByKeyword(String keyword) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        search.clear();
        search.sendKeys(keyword);
    }

    public void clickKontrakIconByNama(String namaSiswa) {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
        for (WebElement row : rows) {
            String nama = row.findElement(namaSiswaCell).getText();
            if (nama.equalsIgnoreCase(namaSiswa)) {
                row.findElement(kontrakIcon).click();
                break;
            }
        }
    }

    public void clickBayarIconByNama(String namaSiswa) {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
        for (WebElement row : rows) {
            String nama = row.findElement(namaSiswaCell).getText();
            if (nama.equalsIgnoreCase(namaSiswa)) {
                row.findElement(bayarIcon).click();
                break;
            }
        }
    }

//    public boolean isSiswaDisplayed(String namaSiswa) {
//        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
//        for (WebElement row : rows) {
//            WebElement namaCell = row.findElement(By.xpath("./td[1]"));
//            if (namaCell.getText().trim().equalsIgnoreCase(namaSiswa)) {
//                return true;
//            }
//        }
//        return false;
//    }


    //
//    public void clickPembayaranButton(String namaSiswaTarget) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
//        List<WebElement> rows = driver.findElements(tableRows);
//
//        for (WebElement row : rows) {
//            WebElement namaCell = row.findElement(namaSiswaCell);
//            if (namaCell.getText().toLowerCase().contains(namaSiswaTarget.toLowerCase())) {
//                WebElement bayarButton = row.findElement(bayarIcon);
//                bayarButton.click();
//                System.out.println("[ACTION] Klik tombol bayar untuk siswa: " + namaSiswaTarget);
//                return;
//            }
//        }
//
//        throw new RuntimeException("Siswa dengan nama '" + namaSiswaTarget + "' tidak ditemukan di tabel.");
//    }
}
