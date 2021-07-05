package dsrttesttask.condition.list;

import dsrttesttask.WebElementsListCondition;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CnWebElementsListSize implements WebElementsListCondition<WebElement> {
    private final int expectedSize;

    public CnWebElementsListSize(final int expectedSize) {
        this.expectedSize = expectedSize;
    }

    @Override
    public boolean test(final List<WebElement> webElements) throws Throwable {
        return webElements.size() == this.expectedSize;
    }

    @Override
    public String name() {
        return "size = " + this.expectedSize;
    }
}
