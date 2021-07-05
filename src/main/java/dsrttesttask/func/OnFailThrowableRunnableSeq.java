package dsrttesttask.func;

import dsrttesttask.ThrowableRunnable;

import java.util.Objects;

public class OnFailThrowableRunnableSeq implements ThrowableRunnable {
    private final ThrowableRunnable onFailRunnable;
    private final ThrowableRunnable[] runnables;

    public OnFailThrowableRunnableSeq(final ThrowableRunnable onFailRunnable,
                                      final ThrowableRunnable... runnables) {
        this.onFailRunnable = Objects.requireNonNull(onFailRunnable, "onFailRunnable");
        this.runnables = Objects.requireNonNull(runnables, "runnables");
    }

    @Override
    public final void run() throws Throwable {
        Throwable mainThrowable = null;
        for (final ThrowableRunnable runnable : this.runnables) {
            try {
                runnable.run();
            } catch (final Throwable t) {
                mainThrowable = t;
                break;
            }
        }
        if (mainThrowable != null) {
            try {
                this.onFailRunnable.run();
            } catch (final Throwable t) {
                mainThrowable.addSuppressed(t);
            }
            throw mainThrowable;
        }
    }
}
