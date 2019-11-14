package art.lebedev.test;

import art.lebedev.test.listeners.TestListener;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.openqa.selenium.Proxy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static art.lebedev.test.pages.MainMenu.enterToMainPage;
import static org.testng.Assert.assertTrue;
@Listeners({VideoListener.class, TestListener.class})


public class IdeaMatrixTest {


    /*@Test
    public void ideaMatrixSearchTest () {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        WebDriverManager.chromedriver().setup();
        String searchPhrase = "автоматизатор";

        open("https://www.artlebedev.ru/");

        $(By.linkText("Инвентарь")).click();
        $(".items-wrapper").should(Condition.visible);
        $("#item-matrix").click();
        $(By.name("Word")).setValue(searchPhrase).pressEnter();
        $(".content-margin").should(Condition.visible);
        $(".content-margin").shouldHave(Condition.text(searchPhrase));
    }

    @Test
    public void pageObjectTest () {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        WebDriverManager.chromedriver().setup();
        String searchPhrase = "автоматизатор";

        open("https://www.artlebedev.ru/");

        MainMenu mainMenu = new MainMenu();

        Inventory inventory = mainMenu.clickOnInventory();
        assertTrue(inventory.isItemsWrapperVisible());
        IdeaMatrix ideamatrix = inventory.clickOnIdeaMatrix();
        ideamatrix.inputPhrase(searchPhrase);
        assertTrue(ideamatrix.areResultsVisible());
        assertTrue(ideamatrix.whatIsInTheResultText().contains(searchPhrase));
    }*/

    private String searchPhrase = "автоматизатор";


    @BeforeClass (alwaysRun = true)
    public void beforeTest() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.headless = false;
        BasicConfigurator.configure();
        WebDriverManager.chromedriver().setup();

    }

    @Test
    @Video
    public void stepByStepTest () {

        enterToMainPage()
                .clickOnInventory()
                .checkAreItemsWrapperVisible()
                .clickOnIdeaMatrix()
                .inputPhraseForStepByStep(searchPhrase)
                .checkAreReusultsVisible()
                .checkAreResultsContainsSearchPhrase(searchPhrase);
    }
}
