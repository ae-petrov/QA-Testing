import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class IeTestWomanTshirt {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void beforeTestInit() {
            System.setProperty("webdriver.ie.driver", "lib/IEDriverServer.exe");
            InternetExplorerOptions options = new InternetExplorerOptions();
            driver = new InternetExplorerDriver(options.requireWindowFocus());
            driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
            driver.get("http://automationpractice.com/index.php");
            wait = new WebDriverWait(driver,10,500);
    }

    @Test

    public void selectAndCheckWomanTshirtPage () {
        WebElement womanButtonMenu = driver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(1) > a"));
        womanButtonMenu.getLocation();
        Actions actions = new Actions(driver);
        actions.moveToElement(womanButtonMenu,5,3).perform();
        WebElement womanMenu = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(1)"))));
        womanMenu.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(1) > ul > li:nth-child(1) > ul > li:nth-child(1) > a")).click();
        WebElement productList = driver.findElement(By.cssSelector("#center_column > ul"));
        assertTrue(productList.isDisplayed());
        List<WebElement> productItems = driver.findElements(By.cssSelector("#center_column > ul > li"));
        assertTrue(productItems.size()!=0);
    }

    @AfterTest
    public void afterAll () {
        driver.quit();
    }

}
