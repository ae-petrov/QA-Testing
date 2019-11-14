import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FlightSeekAndPurchaseTest {

    private WebDriver driver; //объявление для того, чтобы можно было обращаться из разных методов
    private String mainUrl = "http://blazedemo.com/"; //объявление основного адреса
    private Random rnd = new Random();

    @BeforeTest  //инициализируется драйвер, устанавливается неявное ожидание и открывется страница
        public void beforeAllTestInit() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
        driver.get(mainUrl);
        }


    @Test

    public void seekToPurchaseTest() {

// searching of random route

        Select depSelect = new Select(driver.findElement(By.name("fromPort")));
        Select arrivalSelect = new Select(driver.findElement(By.name("toPort")));
        depSelect.selectByIndex(rnd.nextInt(depSelect.getOptions().size())); //рандомный выбор dep города
        arrivalSelect.selectByIndex(rnd.nextInt(arrivalSelect.getOptions().size())); //рандомный выбор arr города
        String selectedDepCity = depSelect.getFirstSelectedOption().getText(); //запомнить выбранный dep город
        String selectedArrivalCity = arrivalSelect.getFirstSelectedOption().getText();//запомнить выбранный Arr город
        WebElement findButton = new WebDriverWait(driver, 5,1000).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("body > div.container > form > div > input"))));
        findButton.click();

// check page title

        WebElement header = driver.findElement(By.cssSelector("body > div.container > h3"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(
                ExpectedConditions.and(
                    ExpectedConditions.textToBePresentInElement(header,selectedArrivalCity),
                    ExpectedConditions.textToBePresentInElement(header,selectedDepCity)));

//check names in  departs and Arrives columns
        WebElement depTableHeader = driver.findElement(By.cssSelector("body > div.container > table > thead > tr > th:nth-child(4)"));
        WebElement arrTableHeader = driver.findElement(By.cssSelector("body > div.container > table > thead > tr > th:nth-child(5)"));
        wait.until(
                ExpectedConditions.and(
                    ExpectedConditions.textToBePresentInElement(depTableHeader,selectedDepCity),
                    ExpectedConditions.textToBePresentInElement(arrTableHeader,selectedArrivalCity)));

// get list of flights and random pick, remember all key parametres

        WebElement flightTable = driver.findElement(By.cssSelector("body > div.container > table > tbody"));
                List<WebElement> tableRows = flightTable.findElements(By.cssSelector("body > div.container > table > tbody > tr"));
        WebElement selectedRowFlight = tableRows.get(rnd.nextInt(tableRows.size()));
        WebElement chooseButton = selectedRowFlight.findElement(By.cssSelector("td"));
        String selectedFlightNumber = selectedRowFlight.findElement(By.name("flight")).getAttribute("value");
        String selectedAirline = selectedRowFlight.findElement(By.name("airline")).getAttribute("value");
        String selectedPrice = selectedRowFlight.findElement(By.name("price")).getAttribute("value");
        chooseButton.click();

//check if everything transport to next page correct (route, flight, aircompany, price, full price)
        wait.until(
                ExpectedConditions.and(
                        ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("body > div.container > h2")),selectedArrivalCity),
                        ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("body > div.container > h2")),selectedDepCity),
                        ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("body > div.container > p:nth-child(2)")),selectedAirline),
                        ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("body > div.container > p:nth-child(3)")),selectedFlightNumber),
                        ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("body > div.container > p:nth-child(4)")),selectedPrice)
                ));

        WebElement feesAndTaxes = driver.findElement(By.cssSelector("body > div.container > p:nth-child(5)")); //находит элемент сборов
        WebElement totalCost = driver.findElement(By.cssSelector("body > div.container > p:nth-child(7) > em")); //находит элемент общей стоимости
        BigDecimal feesSum = new BigDecimal(feesAndTaxes.getText().substring(feesAndTaxes.getText().indexOf(": ")+2));
        BigDecimal totalSum = new BigDecimal(selectedPrice);
        BigDecimal visibleSum = new BigDecimal(totalCost.getText());
        BigDecimal checkSum = feesSum.add(totalSum);
        assertEquals(checkSum, visibleSum); // сравнивает с отображенной

//submit
        driver.findElement(By.id("inputName")).sendKeys("Test Name");
        driver.findElement(By.id("address")).sendKeys("Test address");
        driver.findElement(By.id("city")).sendKeys("Test city");
        driver.findElement(By.id("state")).sendKeys("Test state");
        driver.findElement(By.id("zipCode")).sendKeys("Test 000000");

        Select cardSelect = new Select(driver.findElement(By.id("cardType")));
        cardSelect.selectByIndex(rnd.nextInt(cardSelect.getOptions().size()));
        String enteredCardNumber = "1234 5678 9874 5632";
        driver.findElement(By.id("creditCardNumber")).sendKeys(enteredCardNumber);
        driver.findElement(By.id("creditCardMonth")).clear();
        String enteredCardMonth = "12";
        driver.findElement(By.id("creditCardMonth")).sendKeys(enteredCardMonth);
        driver.findElement(By.id("creditCardYear")).clear();
        String enteredCardYear = "2019";
        driver.findElement(By.id("creditCardYear")).sendKeys(enteredCardYear);
        driver.findElement(By.id("nameOnCard")).sendKeys("Test Card Holder Name");

        driver.findElement(By.cssSelector("body > div.container > form > div:nth-child(12) > div > input")).click();

// check ID, Status, Amount , last 4 digits of card and expiration date

        WebElement purchaseTable = driver.findElement(By.cssSelector("body > div.container > div > table > tbody"));
        assertTrue(purchaseTable.findElement(By.cssSelector("tr > td:nth-child(2)")).getText() != null);
        assertTrue(purchaseTable.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText() != null);
        assertTrue(purchaseTable.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).getText() != null);

        String cardNumber = purchaseTable.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(2)")).getText();
        wait.until(ExpectedConditions.textMatches(By.cssSelector("tr:nth-child(4) > td:nth-child(2)"), Pattern.compile("^[\\s\\S]{12}[0-9]{4}$")));
        assertEquals(enteredCardNumber.substring(enteredCardNumber.length()-4), cardNumber.substring(cardNumber.length()-4));
        String expirationDate = driver.findElement(By.cssSelector("tr:nth-child(5) > td:nth-child(2)")).getText();
        assertEquals(expirationDate.substring(expirationDate.indexOf(" /")+2),enteredCardYear);
        assertEquals(expirationDate.substring(0,2),enteredCardMonth);
    }

    @AfterTest
    public void afterAll() {
        driver.quit();
    }

}