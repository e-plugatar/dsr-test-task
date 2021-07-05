package dsrttesttask.property;

import java.time.Duration;

public final class PsDefaultPollingInterval extends PsBase<Duration> {

    public PsDefaultPollingInterval() {
        super("default.pollingIntervalMs", str -> Duration.ofMillis(Integer.parseInt(str)));
    }
}
