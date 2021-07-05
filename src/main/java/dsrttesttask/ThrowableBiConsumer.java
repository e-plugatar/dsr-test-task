package dsrttesttask;

@FunctionalInterface
public interface ThrowableBiConsumer<T, U> {

    void accept(T t, U u) throws Throwable;
}
