package dsrttesttask.condition.list;

import dsrttesttask.ThrowableConsumer;
import dsrttesttask.WebElementsListCondition;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CnWebElementsListAlwaysTrue<T extends WebElement> implements WebElementsListCondition<T> {
    private final String name;
    private final ThrowableConsumer<List<T>> action;

    public CnWebElementsListAlwaysTrue(final String name, final ThrowableConsumer<List<T>> action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public boolean test(final List<T> elements) throws Throwable {
        this.action.accept(elements);
        return true;
    }

    @Override
    public String name() {
        return this.name;
    }
}
