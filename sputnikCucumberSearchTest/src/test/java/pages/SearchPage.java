package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.sputnik.ru/")
public class SearchPage extends PageObject {


    @FindBy(css = "#js-search-input")
    WebElementFacade searchInput;

    @FindBy(css = ".b-search-form__bt")
    WebElementFacade searchButton;

    public void inputToSearchField(String text) {
        searchInput.type(text);
    }

    public void clickSearchInput() {
        searchInput.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

}
