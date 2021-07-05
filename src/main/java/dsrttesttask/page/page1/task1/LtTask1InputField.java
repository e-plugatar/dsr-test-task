package dsrttesttask.page.page1.task1;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class LtTask1InputField extends ByChained {

    public LtTask1InputField() {
        super(new LtTask1Base(), new By.ByCssSelector("input#user-message"));
    }
}
