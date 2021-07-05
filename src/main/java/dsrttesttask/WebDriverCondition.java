package dsrttesttask;

import org.openqa.selenium.WebDriver;

public interface WebDriverCondition<T extends WebDriver> extends ThrowablePredicate<T> {

    String desc();
}
