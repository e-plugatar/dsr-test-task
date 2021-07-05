package dsrttesttask.page.page1.task1;

import dsrttesttask.ThrowableConsumersSeq;
import dsrttesttask.condition.element.CnElementVisible;
import dsrttesttask.step.assertion.base.SpElementExplicitWait;
import org.openqa.selenium.WebDriver;

public class SpTask1InputFieldAndButtonVisible extends ThrowableConsumersSeq<WebDriver> {

    public SpTask1InputFieldAndButtonVisible() {
        super(
                new SpElementExplicitWait<>(new LtTask1InputField(), new CnElementVisible()),
                new SpElementExplicitWait<>(new LtTask1Button(), new CnElementVisible())
        );
    }
}
