package dsrttesttask.step.assertion.element;

import dsrttesttask.condition.element.CnElementAlwaysTrueIfExist;
import dsrttesttask.step.assertion.base.SpElementExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SpClear extends SpElementExplicitWait<WebDriver> {

    public SpClear(final By locator) {
        super(locator, new CnElementAlwaysTrueIfExist<>(
                "clear",
                optElement -> optElement.ifPresent(WebElement::clear)
        ));
    }
}
