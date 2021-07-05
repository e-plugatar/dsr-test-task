package dsrttesttask;

@FunctionalInterface
public interface ThrowableFunction<T, R> {

    R apply(T t) throws Throwable;
}
