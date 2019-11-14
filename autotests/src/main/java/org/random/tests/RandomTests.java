package org.random.tests;

import com.sun.org.apache.xpath.internal.objects.XString;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomTests {
    static WebDriver driver;
    String MaxNumberGenerator = "";
    Boolean fixedMaxNumberGenerator = false; // true - fixed max(change in line30); false - random depends on date

    @Test

    void randomNumbersAreNotReapeatedTest() {

        if (fixedMaxNumberGenerator == true) {
            MaxNumberGenerator = "150"; //Change value if necessary to change fixed maximum generator number
        }
        else {
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("D");
            MaxNumberGenerator = formatForDateNow.format(dateNow);
        }

        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://random.org");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("#homepage-generator iframe")));
        driver.findElement(By.id("true-random-integer-generator-max")).clear(); // clean maximum field
        driver.findElement(By.id("true-random-integer-generator-max")).sendKeys(MaxNumberGenerator); // set new value maximum field
        //first click
        driver.findElement(By.id("true-random-integer-generator-button")).click();
        wait.until(ExpectedConditions.textMatches(By.id("true-random-integer-generator-result"), Pattern.compile("^(?!\\s*$).+")));
        String firstNumber = driver.findElement(By.id("true-random-integer-generator-result")).getText();
        assertTrue(firstNumber != null);
        //second click
        driver.findElement(By.id("true-random-integer-generator-button")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//*[@id='true-random-integer-generator-result']/img")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//*[@id='true-random-integer-generator-result']/img")));
        String secondNumber = driver.findElement(By.id("true-random-integer-generator-result")).getText();
        assertNotEquals(firstNumber, secondNumber);
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

}
