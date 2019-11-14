package art.lebedev.test.pages;

public class BasePage<T extends BasePage> {
    public T chooseMenu() {
        //TODO
        return (T) this;
    }
}
