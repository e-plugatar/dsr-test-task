package dsrttesttask.condition.element;

import dsrttesttask.WebElementCondition;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class CnElementTextIs implements WebElementCondition<WebElement> {
    private final String text;

    public CnElementTextIs(final String text) {
        this.text = text;
    }

    @Override
    public boolean test(final Optional<WebElement> webElement) {
        return webElement.filter(element -> this.text.equals(element.getText()))
                .isPresent();
    }

    @Override
    public String name() {
        return "text is " + this.text;
    }
}
