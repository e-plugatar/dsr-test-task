package dsrttesttask.step.assertion.base;

import dsrttesttask.ThrowableConsumer;
import dsrttesttask.StepAssertionError;
import io.qameta.allure.Allure;

public class SpAllureAssertion<T> implements ThrowableConsumer<T> {
    private final String stepName;
    private final ThrowableConsumer<T> delegate;

    public SpAllureAssertion(final String stepName, final ThrowableConsumer<T> delegate) {
        this.stepName = stepName;
        this.delegate = delegate;
    }

    @Override
    public final void accept(final T t) {
        Allure.step(stepContext -> {
            stepContext.name(this.stepName);
            try {
                this.delegate.accept(t);
            } catch (final Throwable throwable) {
                throw new StepAssertionError(throwable.getMessage(), throwable);
            }
        });
    }
}
