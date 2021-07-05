package dsrttesttask.condition.element;

import dsrttesttask.WebElementCondition;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class CnElementVisible implements WebElementCondition<WebElement> {

    public CnElementVisible() {
    }

    @Override
    public boolean test(final Optional<WebElement> webElement) {
        return webElement.filter(element -> {
            final Dimension size = element.getSize();
            final boolean is = element.isDisplayed();
            return element.isDisplayed() && size.getHeight() > 0 && size.getWidth() > 0;
        }).isPresent();
    }

    @Override
    public String name() {
        return "visible";
    }
}
