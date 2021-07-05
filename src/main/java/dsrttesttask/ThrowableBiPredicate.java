package dsrttesttask;

@FunctionalInterface
public interface ThrowableBiPredicate<T, U> {

    boolean test(T t, U u) throws Throwable;
}
