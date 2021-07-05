package dsrttesttask.step.assertion.base;

import dsrttesttask.WebDriverCondition;
import dsrttesttask.WebElementCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Optional;

public class SpElementExplicitWait<T extends WebDriver> extends SpDriverExplicitWait<T> {

    public SpElementExplicitWait(final By locator,
                                 final WebElementCondition<? super WebElement> condition) {
        super(new CnWebDriverOfCnWebElement<>(locator, condition));
    }

    public SpElementExplicitWait(final By locator,
                                 final WebElementCondition<? super WebElement> condition,
                                 final Duration timeout) {
        super(new CnWebDriverOfCnWebElement<>(locator, condition), timeout);
    }

    public SpElementExplicitWait(final By locator,
                                 final WebElementCondition<? super WebElement> condition,
                                 final Duration timeout,
                                 final Duration pollingInterval) {
        super(new CnWebDriverOfCnWebElement<>(locator, condition), timeout, pollingInterval);
    }

    private static class CnWebDriverOfCnWebElement<T extends WebDriver> implements WebDriverCondition<T> {
        private final By locator;
        private final WebElementCondition<? super WebElement> condition;

        public CnWebDriverOfCnWebElement(final By locator,
                                         final WebElementCondition<? super WebElement> condition) {
            this.locator = locator;
            this.condition = condition;
        }

        @Override
        public boolean test(final T driver) throws Throwable {
            WebElement webElement = null;
            try {
                webElement = driver.findElement(this.locator);
            } catch (final NoSuchElementException ignored) {
            }
            return this.condition.test(Optional.ofNullable(webElement));
        }

        @Override
        public String desc() {
            return "Element [" + this.locator + "] " + this.condition.name();
        }
    }
}
