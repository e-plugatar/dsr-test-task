package dsrttesttask.condition.driver;

import dsrttesttask.ThrowableConsumer;
import dsrttesttask.WebDriverCondition;
import org.openqa.selenium.WebDriver;

public class CnDriverAlwaysTrue<T extends WebDriver> implements WebDriverCondition<T> {
    private final String name;
    private final ThrowableConsumer<WebDriver> action;

    public CnDriverAlwaysTrue(final String name, final ThrowableConsumer<WebDriver> action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public final boolean test(final T driver) throws Throwable {
        this.action.accept(driver);
        return true;
    }

    @Override
    public final String desc() {
        return this.name;
    }
}
