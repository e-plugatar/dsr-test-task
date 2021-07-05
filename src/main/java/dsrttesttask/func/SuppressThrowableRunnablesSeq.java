package dsrttesttask.func;

import dsrttesttask.ThrowableRunnable;

import java.util.Objects;

public class SuppressThrowableRunnablesSeq implements ThrowableRunnable {
    private final ThrowableRunnable[] runnables;

    public SuppressThrowableRunnablesSeq(final ThrowableRunnable... runnables) {
        this.runnables = Objects.requireNonNull(runnables, "runnables");
    }

    @Override
    public final void run() throws Throwable {
        Throwable mainThrowable = null;
        for (final ThrowableRunnable runnable : this.runnables) {
            try {
                runnable.run();
            } catch (final Throwable t) {
                if (mainThrowable == null) {
                    mainThrowable = t;
                } else {
                    mainThrowable.addSuppressed(t);
                }
            }
        }
        if (mainThrowable != null) {
            throw mainThrowable;
        }
    }
}
