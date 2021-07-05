package dsrttesttask;

@FunctionalInterface
public interface ThrowablePredicate<T> {

    boolean test(T t) throws Throwable;
}
