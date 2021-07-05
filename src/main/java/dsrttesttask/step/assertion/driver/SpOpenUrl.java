package dsrttesttask.step.assertion.driver;

import dsrttesttask.condition.driver.CnDriverAlwaysTrue;
import dsrttesttask.step.assertion.base.SpDriverExplicitWait;
import org.openqa.selenium.WebDriver;

public class SpOpenUrl extends SpDriverExplicitWait<WebDriver> {

    public SpOpenUrl(final String url) {
        super(new CnDriverAlwaysTrue<>(
                "open " + url,
                driver -> driver.navigate().to(url))
        );
    }
}
