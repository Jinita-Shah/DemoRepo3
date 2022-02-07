import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.percy.selenium.Percy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for example App.
 */
public class AppTest {
   // String TEST_URL = "https://www.browserstack.com";

    String TEST_URL = "https://k8s.bsstag.com";
    WebDriverWait w;
    WebDriver driver;
    Percy percy;

    @BeforeEach
    public void startAppAndOpenBrowser() throws IOException {
        // Disable browser logs from being logged to stdout
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        // Create a threadpool with 1 thread and run our server on it.
        //serverExecutor = Executors.newFixedThreadPool(1);
        // server = App.startServer(serverExecutor);
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        percy = new Percy(driver);
        w=new WebDriverWait(driver,20);
    }

    @AfterEach
    public void closeBrowser() {
        // Close our test browser.
        driver.quit();
    }

    @Test
    public void loadsHomePage() {
        driver.get(TEST_URL+"/");
        percy.snapshot("Home Page",Arrays.asList(375, 480, 720, 1280, 1440, 1920));
    }
    @Test
    public void loadsAutomatePage() {
        driver.get(TEST_URL+"/integrations/automate");
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='all-integrations']/div/h2")));
        percy.snapshot("Automate Page",Arrays.asList(375, 480, 720, 1280, 1440, 1920));
    }

    @Test
    public void loadsDocsPage() {
        driver.get(TEST_URL+"/docs");
        percy.snapshot("Docs Page",Arrays.asList(375, 480, 720, 1280, 1440, 1920));
    }

    @Test
    public void loadsPricingPage() {
        driver.get(TEST_URL+"/pricing");
        percy.snapshot("Pricing page");
    }



}
