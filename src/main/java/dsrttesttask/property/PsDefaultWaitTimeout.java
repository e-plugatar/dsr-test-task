package dsrttesttask.property;

import java.time.Duration;

public final class PsDefaultWaitTimeout extends PsBase<Duration> {

    public PsDefaultWaitTimeout() {
        super("default.waitTimeoutMs", str -> Duration.ofMillis(Integer.parseInt(str)));
    }
}
