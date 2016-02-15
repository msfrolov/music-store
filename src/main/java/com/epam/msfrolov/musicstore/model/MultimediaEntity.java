package com.epam.msfrolov.musicstore.model;

import java.time.Duration;

//"PT20.345S" -- parses as "20.345 seconds"
//"PT15M"     -- parses as "15 minutes" (where a minute is 60 seconds)


public abstract class MultimediaEntity extends NamedEntity {
    private Duration duration;
    private Metadata details;

    public Metadata getDetails() {
        return details;
    }

    protected void setDetails(Metadata details) {
        this.details = details;
    }

    public Duration getDuration() {
        return duration;
    }

    protected void setDuration(Duration duration) {
        this.duration = duration;
    }
}
