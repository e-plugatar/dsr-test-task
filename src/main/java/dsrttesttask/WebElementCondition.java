package dsrttesttask;

import org.openqa.selenium.WebElement;

import java.util.Optional;

public interface WebElementCondition<T extends WebElement> extends ThrowablePredicate<Optional<T>> {

    String name();
}
