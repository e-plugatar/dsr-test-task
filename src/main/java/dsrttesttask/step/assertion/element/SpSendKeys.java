package dsrttesttask.step.assertion.element;

import dsrttesttask.condition.element.CnElementAlwaysTrueIfExist;
import dsrttesttask.step.assertion.base.SpElementExplicitWait;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpSendKeys extends SpElementExplicitWait<WebDriver> {

    public SpSendKeys(final By locator, final CharSequence... keysToSend) {
        super(locator, new CnElementAlwaysTrueIfExist<>(
                "sendKeys " + ArrayUtils.toString(keysToSend),
                optElement -> optElement.ifPresent(element -> element.sendKeys(keysToSend))
        ));
    }
}
