package dsrttesttask.condition.element;

import dsrttesttask.WebElementCondition;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class CnElementHidden implements WebElementCondition<WebElement> {

    public CnElementHidden() {
    }

    @Override
    public boolean test(final Optional<WebElement> webElement) {
        if (webElement.isPresent()) {
            final WebElement element = webElement.get();
            final Dimension size = element.getSize();
            return !element.isDisplayed() || (size.getHeight() < 1 && size.getWidth() < 1);
        }
        return true;
    }

    @Override
    public String name() {
        return "hidden";
    }
}
