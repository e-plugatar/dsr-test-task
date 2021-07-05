package dsrttesttask;

public class StepAssertionError extends AssertionError {

    public StepAssertionError() {
        super();
    }

    public StepAssertionError(final String message) {
        super(message);
    }

    public StepAssertionError(final String message, final Throwable cause) {
        super(message, cause);
    }
}
