package dsrttesttask;

@FunctionalInterface
public interface ThrowableObjBooleanConsumer<T> {

    void accept(T t, boolean value) throws Throwable;
}
