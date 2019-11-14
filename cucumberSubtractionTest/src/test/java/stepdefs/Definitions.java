package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import func.SubtractFunc;

import static org.junit.Assert.assertEquals;


public class Definitions {

    SubtractFunc subtractFunc;
    int actualResult;

    @Given("I create a new subtract function")
    public void iCreateNewObject() {subtractFunc = new SubtractFunc();}

    @When("I add (.*) and (.*) to the function")
    public void iAddAAndBToTheFunction(int a, int b) {actualResult = subtractFunc.subtractBfromA(a,b);}

    @Then("I get (.*) as a result")
    public void iGetResult(int expectedResult) {assertEquals(expectedResult,actualResult);}



}
