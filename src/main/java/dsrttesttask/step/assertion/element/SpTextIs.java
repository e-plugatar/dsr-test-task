package dsrttesttask.step.assertion.element;

import dsrttesttask.condition.element.CnElementTextIs;
import dsrttesttask.step.assertion.base.SpElementExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpTextIs extends SpElementExplicitWait<WebDriver> {

    public SpTextIs(final By locator, final String text) {
        super(locator, new CnElementTextIs(text));
    }
}
