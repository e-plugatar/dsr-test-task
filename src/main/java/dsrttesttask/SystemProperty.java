package dsrttesttask;

/**
 * ...
 *
 * @param <T> the property type
 */
public interface SystemProperty<T> {

    /**
     * ...
     *
     * @return the property value
     * @throws ConversionException if this property value can not be converted to {@code T} type
     * @throws SecurityException   if a security manager exists and it's impossible to get
     *                             access to the specified system property
     */
    T value();
}
