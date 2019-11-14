package art.lebedev.test.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Inventory extends BasePage<Inventory> {

 //   public boolean isItemsWrapperVisible () {
 //       return $(".items-wrapper").isDisplayed();
 //   }

    public Inventory checkAreItemsWrapperVisible () {
        $(".items-wrapper").should(Condition.visible);
        return this;
    }

    public IdeaMatrix clickOnIdeaMatrix () {
        $("#item-matrix").click();
        return page(IdeaMatrix.class);
    }
}
