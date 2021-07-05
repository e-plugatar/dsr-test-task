package dsrttesttask.page.page1.task1;

import dsrttesttask.condition.element.CnElementHidden;
import dsrttesttask.step.assertion.base.SpElementExplicitWait;
import org.openqa.selenium.WebDriver;

public class SpTask1MsgResultHidden extends SpElementExplicitWait<WebDriver> {

    public SpTask1MsgResultHidden() {
        super(new LtTask1MsgResult(), new CnElementHidden());
    }
}
