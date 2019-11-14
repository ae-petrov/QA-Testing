package art.lebedev.test.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class IdeaMatrix extends BasePage<IdeaMatrix> {

 //   public void inputPhrase (String phrase) {
 //       $(By.name("Word")).setValue(phrase).pressEnter();
 //   }

    public IdeaMatrix inputPhraseForStepByStep (String phrase) {
        $(By.name("Word")).setValue(phrase).pressEnter();
        return this;
    }

 //   public boolean areResultsVisible () {
 //       return $(".content-margin").isDisplayed();
 //   }

    public IdeaMatrix checkAreReusultsVisible () {
        $(".content-margin").should(Condition.visible);
        return this;
    }

  //  public String whatIsInTheResultText () {
  //      return $(".content-margin").getText();
  //  }

    public IdeaMatrix checkAreResultsContainsSearchPhrase (String phrase) {
        $(".content-margin").shouldHave(Condition.text(phrase));
        return this;

    }







}
