package dsrttesttask.condition.element;

import dsrttesttask.ThrowableConsumer;
import dsrttesttask.WebElementCondition;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class CnElementAlwaysTrueIfExist<T extends WebElement> implements WebElementCondition<T> {
    private final String name;
    private final ThrowableConsumer<Optional<T>> action;

    public CnElementAlwaysTrueIfExist(final String name, final ThrowableConsumer<Optional<T>> action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public final boolean test(final Optional<T> webElement) throws Throwable {
        if (!webElement.isPresent()) {
            return false;
        }
        this.action.accept(webElement);
        return true;
    }

    @Override
    public final String name() {
        return this.name;
    }
}
