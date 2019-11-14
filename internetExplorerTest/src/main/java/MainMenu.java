package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenu {

    WebDriver driver;
    By womanMainMenu = By.cssSelector("#block_top_menu > ul > li:nth-child(1) > a");
    By womanMainMenuAdd = By.cssSelector("#block_top_menu > ul > li:nth-child(1)");
    By tShirtTitle = By.cssSelector("#block_top_menu > ul > li:nth-child(1) > ul > li:nth-child(1) > ul > li:nth-child(1) > a");

    public MainMenu(WebDriver driver) {this.driver = driver;}

    public void clickTshirtInWomanMenu() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(womanMainMenu), +5,+3).perform();
        WebDriverWait wait = new WebDriverWait(driver,10,500);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(womanMainMenuAdd)));
        driver.findElement(tShirtTitle).click();
    }
}
