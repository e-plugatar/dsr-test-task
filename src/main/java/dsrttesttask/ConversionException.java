package dsrttesttask;

/**
 * Thrown to indicate that the code has attempted to convert an unsuitable object to another.
 * It is close to {@link ClassCastException}, {@link NumberFormatException}, etc.
 */
public class ConversionException extends RuntimeException {

    public ConversionException(final String message) {
        super(message);
    }

    public ConversionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
