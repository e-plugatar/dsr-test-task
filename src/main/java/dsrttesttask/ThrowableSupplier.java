package dsrttesttask;

@FunctionalInterface
public interface ThrowableSupplier<T> {

    T get() throws Throwable;
}
