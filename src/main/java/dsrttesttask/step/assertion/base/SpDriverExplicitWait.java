package dsrttesttask.step.assertion.base;

import dsrttesttask.WebDriverCondition;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SpDriverExplicitWait<T extends WebDriver> extends SpExplicitWait<T> {

    public SpDriverExplicitWait(final WebDriverCondition<? super T> condition) {
        super(condition.desc(), condition);
    }

    public SpDriverExplicitWait(final WebDriverCondition<? super T> condition,
                                final Duration timeout) {
        super(condition.desc(), condition, timeout);
    }

    public SpDriverExplicitWait(final WebDriverCondition<? super T> condition,
                                final Duration timeout,
                                final Duration pollingInterval) {
        super(condition.desc(), condition, timeout, pollingInterval);
    }
}
