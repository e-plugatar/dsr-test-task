package dsrttesttask.page.page1.task1;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class LtTask1Button extends ByChained {

    public LtTask1Button() {
        super(new LtTask1Base(), new By.ByCssSelector("[type='button']"));
    }
}
