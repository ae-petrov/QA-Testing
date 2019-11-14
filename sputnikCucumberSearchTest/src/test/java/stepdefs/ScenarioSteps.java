package stepdefs;

import cucumber.api.java.After;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ResultPage;
import pages.SearchPage;

import static org.junit.Assert.assertTrue;

public class ScenarioSteps {
    SearchPage searchPage;
    ResultPage resultPage;

    @Before
    public void userOpensHomePageOfSputnik() {
        searchPage.open();
        assertTrue(searchPage.compatibleWithUrl("https://www.sputnik.ru/"));
    }

    @After
    public void afterAllScenarioSteps () {
        resultPage.getDriver().quit();
    }

    @Given("User clicks on input search field")
    public void clickOnField () {
        searchPage.clickSearchInput();
    }

    @When("User searches for (.*)")
    public void putTextOnField(String text) {
        searchPage.inputToSearchField(text);
    }

    @When("User click search button")
    public void clickSearchButton () {
        searchPage.clickSearchButton();
    }

    @Then("User sees the list of results")
    public void areResultsVisible() {
        assertTrue(resultPage.areResultsDisplayed());
    }

    @Then("(.*) should be displayed")
    public void areResultsDisplayed(String expectedPhrase) {
        String title = resultPage.getTitle();
        assertTrue(title.contains(expectedPhrase));
    }
}
