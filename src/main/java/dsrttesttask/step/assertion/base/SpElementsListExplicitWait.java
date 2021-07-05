package dsrttesttask.step.assertion.base;

import dsrttesttask.WebDriverCondition;
import dsrttesttask.WebElementsListCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class SpElementsListExplicitWait<T extends WebDriver> extends SpDriverExplicitWait<T> {

    public SpElementsListExplicitWait(final By locator,
                                      final WebElementsListCondition<WebElement> condition) {
        super(new CnWebDriverOfCnWebElementList<>(locator, condition));
    }

    public SpElementsListExplicitWait(final By locator,
                                      final WebElementsListCondition<WebElement> condition,
                                      final Duration timeout) {
        super(new CnWebDriverOfCnWebElementList<>(locator, condition), timeout);
    }

    public SpElementsListExplicitWait(final By locator,
                                      final WebElementsListCondition<WebElement> condition,
                                      final Duration timeout,
                                      final Duration pollingInterval) {
        super(new CnWebDriverOfCnWebElementList<>(locator, condition), timeout, pollingInterval);
    }

    private static class CnWebDriverOfCnWebElementList<T extends WebDriver> implements WebDriverCondition<T> {
        private final By locator;
        private final WebElementsListCondition<WebElement> condition;

        public CnWebDriverOfCnWebElementList(final By locator,
                                             final WebElementsListCondition<WebElement> condition) {
            this.locator = locator;
            this.condition = condition;
        }

        @Override
        public boolean test(final T driver) throws Throwable {
            return this.condition.test(driver.findElements(this.locator));
        }

        @Override
        public String desc() {
            return "ElementsList [" + this.locator + "] " + this.condition.name();
        }
    }
}
