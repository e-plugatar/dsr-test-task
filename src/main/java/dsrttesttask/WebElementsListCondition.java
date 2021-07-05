package dsrttesttask;

import dsrttesttask.ThrowablePredicate;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface WebElementsListCondition<T extends WebElement> extends ThrowablePredicate<List<T>> {

    String name();
}
