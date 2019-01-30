package com.kg.vnctestcontainer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.*;
import org.testcontainers.containers.wait.strategy.Wait;

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
        // webDriver.get("https://www.google.com/");
        // WebElement content = webDriver.findElementById("q");
        // assert content.getText().equals("Foobar");

        driver.get("https://wikipedia.org");
        WebElement searchInput = driver.findElementByName("search");

        searchInput.sendKeys("Rick Astley");
        searchInput.submit();

        WebElement otherPage = driver.findElementByLinkText("Rickrolling");
        otherPage.click();

        boolean expectedTextFound = driver.findElementsByCssSelector("p")
                .stream()
                .anyMatch(element -> element.getText().contains("meme"));

        assertTrue("The word 'meme' is found on a page about rickrolling", expectedTextFound);
    }
}
