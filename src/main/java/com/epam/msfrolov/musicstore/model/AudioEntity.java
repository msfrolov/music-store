package com.epam.msfrolov.musicstore.model;

import java.time.Duration;
//"PT20.345S" -- parses as "20.345 seconds"
//"PT15M"     -- parses as "15 minutes" (where a minute is 60 seconds)

public abstract class AudioEntity {
    private Duration duration;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
