package dsrttesttask.page.page1.task1;

import dsrttesttask.condition.element.CnElementVisible;
import dsrttesttask.step.assertion.base.SpElementExplicitWait;
import org.openqa.selenium.WebDriver;

public class SpTask1MsgResultVisible extends SpElementExplicitWait<WebDriver> {

    public SpTask1MsgResultVisible() {
        super(new LtTask1MsgResult(), new CnElementVisible());
    }
}
