package dsrttesttask.condition.driver;

import dsrttesttask.WebDriverCondition;
import org.openqa.selenium.WebDriver;

public class CnTitleIs implements WebDriverCondition<WebDriver> {
    private final String expectedTitle;

    public CnTitleIs(final String expectedTitle) {
        this.expectedTitle = expectedTitle;
    }

    @Override
    public boolean test(final WebDriver driver) throws Throwable {
        return driver.getTitle().equals(this.expectedTitle);
    }

    @Override
    public String desc() {
        return "title is " + this.expectedTitle;
    }
}
