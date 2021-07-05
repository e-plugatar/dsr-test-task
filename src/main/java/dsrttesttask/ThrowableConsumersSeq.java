package dsrttesttask;

import java.util.Objects;

public class ThrowableConsumersSeq<T> implements ThrowableConsumer<T> {
    private final ThrowableConsumer<? super T>[] consumers;

    @SafeVarargs
    public ThrowableConsumersSeq(final ThrowableConsumer<? super T>... consumers) {
        this.consumers = Objects.requireNonNull(consumers, "consumers");
    }

    @Override
    public final void accept(final T t) throws Throwable {
        for (final ThrowableConsumer<? super T> consumer : this.consumers) {
            consumer.accept(t);
        }
    }
}
