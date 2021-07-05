package dsrttesttask.page.page1.task1;

import dsrttesttask.ThrowableConsumersSeq;
import dsrttesttask.page.common.SpWaitAndCloseAddThisPopup;
import org.openqa.selenium.WebDriver;

public class SpTask1OpenPageWithoutPopup extends ThrowableConsumersSeq<WebDriver> {

    public SpTask1OpenPageWithoutPopup() {
        super(new SpTask1OpenPage(), new SpWaitAndCloseAddThisPopup());
    }
}
