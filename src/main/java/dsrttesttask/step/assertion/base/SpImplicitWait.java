package dsrttesttask.step.assertion.base;

import java.time.Duration;

public class SpImplicitWait<T> extends SpAllureAssertion<T> {

    public SpImplicitWait(final Duration duration) {
        super(
                "Implicit wait " + duration,
                driver -> Thread.sleep(duration.toMillis())
        );
    }
}
