package dsrttesttask.driver.closable;

import dsrttesttask.ThrowableConsumer;
import dsrttesttask.ThrowableObjBooleanConsumer;
import dsrttesttask.ThrowableRunnable;
import dsrttesttask.ThrowableSupplier;
import dsrttesttask.func.OnFailThrowableRunnableSeq;
import dsrttesttask.func.SuppressThrowableRunnablesSeq;
import org.openqa.selenium.WebDriver;

public class ClosableDriverAction<D extends WebDriver> implements ThrowableRunnable {
    private final ThrowableSupplier<? extends D> createDriver;
    private final ThrowableConsumer<? super D> beforeAction;
    private final ThrowableObjBooleanConsumer<? super D> beforeClose;
    private final ThrowableConsumer<? super D> action;

    public ClosableDriverAction(final ThrowableSupplier<? extends D> createDriver,
                                final ThrowableConsumer<? super D> beforeAction,
                                final ThrowableObjBooleanConsumer<? super D> beforeClose,
                                final ThrowableConsumer<? super D> action) {
        this.createDriver = createDriver;
        this.beforeAction = beforeAction;
        this.beforeClose = beforeClose;
        this.action = action;
    }

    @Override
    public final void run() throws Throwable {
        final D driver = this.createDriver.get();
        new OnFailThrowableRunnableSeq(
                new SuppressThrowableRunnablesSeq(
                        () -> this.beforeClose.accept(driver, true),
                        driver::quit
                ),
                () -> {
                    this.beforeAction.accept(driver);
                    this.action.accept(driver);
                }
        ).run();
        new SuppressThrowableRunnablesSeq(
                () -> this.beforeClose.accept(driver, false),
                driver::quit
        ).run();
    }
}
