package com.epam.msfrolov.musicstore.model;

import java.time.Duration;

public abstract class MultimediaEntity extends NamedEntity {
    private Duration duration = Duration.ZERO;

    public Duration getDuration() {
        return duration;
    }

    protected void setDuration(Duration duration) {
        this.duration = duration;
    }
}
