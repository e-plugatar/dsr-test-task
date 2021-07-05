package dsrttesttask.property;

import dsrttesttask.ConversionException;
import dsrttesttask.SystemProperty;

import java.util.function.Function;

public class PsBase<T> implements SystemProperty<T> {
    private final String name;
    private final Function<? super String, ? extends T> convert;

    public PsBase(final String name, final Function<? super String, ? extends T> convert) {
        this.name = name;
        this.convert = convert;
    }

    @Override
    public final T value() {
        if (this.name.isEmpty()) {
            throw new ConversionException("Value [null] for property [<empty_string>]");
        }
        final String value = System.getProperty(this.name);
        if (value == null) {
            throw new ConversionException("Value [null] for property [" + this.name + "]");
        }
        try {
            return this.convert.apply(value);
        } catch (final Exception ex) {
            throw new ConversionException("Value [" + value + "] for property [" + this.name + "]", ex);
        }
    }
}
