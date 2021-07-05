package dsrttesttask.page.common;

import dsrttesttask.ThrowableConsumersSeq;
import dsrttesttask.condition.element.CnElementHidden;
import dsrttesttask.condition.element.CnElementVisible;
import dsrttesttask.step.assertion.base.SpElementExplicitWait;
import dsrttesttask.step.assertion.element.SpClick;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpWaitAndCloseAddThisPopup extends ThrowableConsumersSeq<WebDriver> {
    private static final By CLOSE_BTN = new By.ByCssSelector("#at-cv-lightbox-close");

    public SpWaitAndCloseAddThisPopup() {
        super(
                new SpElementExplicitWait<>(CLOSE_BTN, new CnElementVisible()),
                new SpClick(CLOSE_BTN),
                new SpElementExplicitWait<>(CLOSE_BTN, new CnElementHidden())
        );
    }
}
