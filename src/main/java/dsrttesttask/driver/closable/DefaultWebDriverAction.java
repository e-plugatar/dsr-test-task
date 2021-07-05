package dsrttesttask.driver.closable;

import dsrttesttask.ThrowableConsumer;
import dsrttesttask.ThrowableConsumersSeq;
import dsrttesttask.ThrowableSupplier;
import dsrttesttask.property.PsDefaultDriverName;
import dsrttesttask.step.log.SpAttachPageSource;
import dsrttesttask.step.log.SpAttachScreenshot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DefaultWebDriverAction extends ClosableDriverAction<WebDriver> {

    @SafeVarargs
    public DefaultWebDriverAction(final ThrowableConsumer<? super WebDriver>... actions) {
        this(
                () -> driverByName(new PsDefaultDriverName().value()),
                new ThrowableConsumersSeq<>(actions)
        );
    }

    @SafeVarargs
    public DefaultWebDriverAction(final ThrowableSupplier<? extends WebDriver> createDriver,
                                  final ThrowableConsumer<? super WebDriver>... actions) {
        this(createDriver, new ThrowableConsumersSeq<>(actions));
    }

    public DefaultWebDriverAction(final ThrowableConsumer<? super WebDriver> action) {
        this(
                () -> driverByName(new PsDefaultDriverName().value()),
                action
        );
    }

    public DefaultWebDriverAction(final ThrowableSupplier<? extends WebDriver> createDriver,
                                  final ThrowableConsumer<? super WebDriver> action) {
        super(
                createDriver,
                driver -> {},
                (driver, isFailed) -> {
                    if (isFailed) {
                        new SpAttachPageSource().accept(driver);
                        new SpAttachScreenshot().accept(driver);
                    }
                },
                action
        );
    }

    @SafeVarargs
    public static void exec(final ThrowableConsumer<? super WebDriver>... actions) throws Throwable {
        new DefaultWebDriverAction(actions).run();
    }

    private static WebDriver driverByName(final String driverName) {
        switch (driverName.toLowerCase()) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default:
                throw new RuntimeException("Not found driver for name = " + driverName);
        }
    }
}
