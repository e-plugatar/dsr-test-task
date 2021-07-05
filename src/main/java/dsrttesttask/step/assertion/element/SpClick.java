package dsrttesttask.step.assertion.element;

import dsrttesttask.condition.element.CnElementAlwaysTrueIfExist;
import dsrttesttask.step.assertion.base.SpElementExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SpClick extends SpElementExplicitWait<WebDriver> {

    public SpClick(final By locator) {
        super(locator, new CnElementAlwaysTrueIfExist<>(
                "click",
                optElement -> optElement.ifPresent(WebElement::click)
        ));
    }
}
