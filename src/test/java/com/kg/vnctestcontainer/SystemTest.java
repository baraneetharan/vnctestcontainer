package com.kg.vnctestcontainer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.*;
import org.testcontainers.containers.wait.strategy.Wait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// import org.testcontainers.utility.MountableFile;​
import java.io.File;
// import java.util.stream.Stream;​

public class SystemTest {
    private static Network net = Network.newNetwork();
    @ClassRule
    public static BrowserWebDriverContainer browser = (BrowserWebDriverContainer) new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("build"))
            .withNetwork(net);

    @Test
    public void seleniumTest() {
        RemoteWebDriver driver = browser.getWebDriver();
        driver.get("https://saucelabs.com/test/guinea-pig");
        String heading = driver.findElement(By.xpath("/html/body/h1")).getText();
        System.out.println("*************************************************");
        System.out.println("*************************************************");
        System.out.println("Selenium Test Container test runs");
        System.out.println("*************************************************");
        System.out.println("*************************************************");
        assertEquals("This page is a Selenium sandbox", heading);
    }
}
