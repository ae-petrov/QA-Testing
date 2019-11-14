package art.lebedev.test.pages;

import art.lebedev.test.pages.components.MainMenuBlock;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MainMenu extends BasePage<MainMenu> {

    private MainMenuBlock mainMenuBlock;

    public static MainMenu enterToMainPage() {
        open("https://www.artlebedev.ru/");
        return page(MainMenu.class);
    }

    public Inventory clickOnInventory() {
       //$(By.linkText("Инвентарь")).click();
        mainMenuBlock.clicks();
        return page(Inventory.class);
    }
}
