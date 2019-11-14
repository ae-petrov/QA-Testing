import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;


public class OpenURLandTakeScreenshot {
    private WebDriver driver;
    private static String mainUrl="https://yandex.ru/";


    @BeforeTest
        public void beforeTest () {
            System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(false);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
            driver.get(mainUrl);

    }

    @Test
        public void getYandexScreenshot () {
            assertEquals(driver.getCurrentUrl(),mainUrl);
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
            FileUtils.copyFile(screenshotFile,  new File("./src/"+screenshotFile.getName()));
            System.out.println("yep");
       } catch (IOException e) {
           e.printStackTrace();
        }}


    @AfterTest
        public void afterAll() {
        driver.quit();
    }


}
