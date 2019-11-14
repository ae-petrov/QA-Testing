package art.lebedev.test.pages.components;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainMenuBlock extends ElementsContainer {

    private static final By MAIN_MENU_Inventory = By.linkText("Инвентарь");

    public static void clicks() {
        $(MAIN_MENU_Inventory).click();
    }
}
