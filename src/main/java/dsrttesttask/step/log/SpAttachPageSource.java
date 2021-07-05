package dsrttesttask.step.log;

import dsrttesttask.ThrowableConsumer;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.nio.charset.StandardCharsets;

public class SpAttachPageSource implements ThrowableConsumer<WebDriver> {
    private final String attachmentName;
    private final AllureLifecycle allureLifecycle;

    public SpAttachPageSource() {
        this("pageSource");
    }

    public SpAttachPageSource(final String attachmentName) {
        this(attachmentName, Allure.getLifecycle());
    }

    public SpAttachPageSource(final String attachmentName, final AllureLifecycle allureLifecycle) {
        this.attachmentName = attachmentName;
        this.allureLifecycle = allureLifecycle;
    }

    @Override
    public void accept(final WebDriver webDriver) {
        try {
            final byte[] pageSource = webDriver.getPageSource().getBytes(StandardCharsets.UTF_8);
            this.allureLifecycle.addAttachment(this.attachmentName, "text/html", "html", pageSource);
        } catch (final WebDriverException ex) {
            this.allureLifecycle.addAttachment(this.attachmentName, "text/plain", "txt",
                    ex.toString().getBytes(StandardCharsets.UTF_8));
        }
    }
}
