import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MainMenu;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ChromeTestWomanTshirt {
    WebDriver driver;
    pages.MainMenu mainMenu;
    WebDriverWait wait;
    private BrowserMobProxy proxy;

    @BeforeTest
    public void beforeTestInit() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);

        proxy = new BrowserMobProxyServer();
        proxy.start();
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        options.setCapability(CapabilityType.PROXY, seleniumProxy);
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.newHar("browsermob-test");

        driver = new ChromeDriver(options);
        mainMenu = new MainMenu(driver);
        driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
        driver.get("http://automationpractice.com/index.php");
        wait = new WebDriverWait(driver,10,500);
    }

    @Test
    public void chromeSelectAndCheckWomanTshirtPage () {
        mainMenu.clickTshirtInWomanMenu();
        WebElement productList = driver.findElement(By.cssSelector("#center_column > ul"));
        assertTrue(productList.isDisplayed());
        List<WebElement> productItems = driver.findElements(By.cssSelector("#center_column > ul > li"));
        assertTrue(productItems.size()!=0);
    }

    @AfterTest
        public void afterAll () {
            driver.quit();
            Har har = proxy.getHar();
            proxy.stop();
        try {
            har.writeTo(new File("har_log_file.har"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
