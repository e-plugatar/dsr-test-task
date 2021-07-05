package dsrttesttask.property;

import java.util.function.Function;

public class PsString extends PsBase<String> {

    public PsString(final String name) {
        super(name, Function.identity());
    }
}
