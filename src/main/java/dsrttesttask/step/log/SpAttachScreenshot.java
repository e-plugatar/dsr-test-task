package dsrttesttask.step.log;

import dsrttesttask.ThrowableConsumer;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.remote.Augmentable;
import org.openqa.selenium.remote.Augmenter;

import java.nio.charset.StandardCharsets;

public class SpAttachScreenshot implements ThrowableConsumer<WebDriver> {
    private final String attachmentName;
    private final AllureLifecycle allureLifecycle;

    public SpAttachScreenshot() {
        this("screenshot");
    }

    public SpAttachScreenshot(final String attachmentName) {
        this(attachmentName, Allure.getLifecycle());
    }

    public SpAttachScreenshot(final String attachmentName, final AllureLifecycle allureLifecycle) {
        this.attachmentName = attachmentName;
        this.allureLifecycle = allureLifecycle;
    }

    @Override
    public void accept(final WebDriver webDriver) {
        try {
            if (webDriver instanceof TakesScreenshot) {
                this.attachScreenshot(((TakesScreenshot) webDriver)
                        .getScreenshotAs(OutputType.BYTES));
                return;
            }
            if (webDriver instanceof WrapsDriver) {
                final WebDriver originDriver = ((WrapsDriver) webDriver).getWrappedDriver();
                if (originDriver instanceof TakesScreenshot) {
                    this.attachScreenshot(((TakesScreenshot) originDriver)
                            .getScreenshotAs(OutputType.BYTES));
                    return;
                }
            }
            if (webDriver.getClass().isAnnotationPresent(Augmentable.class)) {
                this.attachScreenshot(((TakesScreenshot) new Augmenter().augment(webDriver))
                        .getScreenshotAs(OutputType.BYTES));
                return;
            }
        } catch (final WebDriverException | UnsupportedOperationException ex) {
            this.attachText(ex.toString().getBytes(StandardCharsets.UTF_8));
        }
        this.attachText("Not found way to take screenshot".getBytes(StandardCharsets.UTF_8));
    }

    private void attachScreenshot(final byte[] bytes) {
        this.allureLifecycle.addAttachment(this.attachmentName, "image/png", "png", bytes);
    }

    private void attachText(final byte[] bytes) {
        this.allureLifecycle.addAttachment(this.attachmentName, "text/plain", "txt", bytes);
    }
}
