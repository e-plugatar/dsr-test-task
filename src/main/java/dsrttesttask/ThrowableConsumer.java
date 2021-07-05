package dsrttesttask;

@FunctionalInterface
public interface ThrowableConsumer<T> {

    void accept(T t) throws Throwable;
}
