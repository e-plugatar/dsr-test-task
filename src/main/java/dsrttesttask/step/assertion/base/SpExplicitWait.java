package dsrttesttask.step.assertion.base;

import dsrttesttask.ThrowableConsumer;
import dsrttesttask.ThrowablePredicate;
import dsrttesttask.property.PsDefaultPollingInterval;
import dsrttesttask.property.PsDefaultWaitTimeout;
import dsrttesttask.StepAssertionError;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SpExplicitWait<T extends WebDriver> implements ThrowableConsumer<T> {
    private final String description;
    private final Duration timeout;
    private final Duration pollingInterval;
    private final ThrowablePredicate<? super T> condition;

    public SpExplicitWait(final String description,
                          final ThrowablePredicate<? super T> condition) {
        this(description, condition, new PsDefaultWaitTimeout().value());
    }

    public SpExplicitWait(final String description,
                          final ThrowablePredicate<? super T> condition,
                          final Duration timeout) {
        this(description, condition, timeout, new PsDefaultPollingInterval().value());
    }

    public SpExplicitWait(final String description,
                          final ThrowablePredicate<? super T> condition,
                          final Duration timeout,
                          final Duration pollingInterval) {
        this.description = description;
        this.condition = condition;
        this.timeout = timeout;
        this.pollingInterval = pollingInterval;
    }

    @Override
    public final void accept(final T driver) throws Throwable {
        Allure.step(stepContext -> {
            stepContext.name(this.description);
            final long timeoutNanos = timeout.toNanos();
            final long pollingIntervalMillis = pollingInterval.toMillis();
            final long startNanos = System.nanoTime();
            boolean lastResult = false;
            Throwable lastThrowable = null;
            while (!lastResult && (System.nanoTime() - startNanos < timeoutNanos)) {
                try {
                    lastThrowable = null;
                    lastResult = this.condition.test(driver);
                } catch (final Throwable throwable) {
                    lastThrowable = throwable;
                    lastResult = false;
                }
                if (!lastResult) {
                    Thread.sleep(pollingIntervalMillis);
                }
            }
            if (!lastResult) {
                final Error error = new StepAssertionError(
                        "Timeout after " + this.timeout + " - " + this.description
                );
                if (lastThrowable != null) {
                    error.initCause(lastThrowable);
                }
                throw error;
            }
        });
    }
}
