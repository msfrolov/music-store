package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.xml.util.DurationXmlAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.Duration;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class MultimediaEntity extends NamedEntity {
    @XmlJavaTypeAdapter(DurationXmlAdapter.class)
    @XmlElement
    private Duration duration = Duration.ZERO;

    public Duration getDuration() {
        return duration;
    }

    protected void setDuration(Duration duration) {
        this.duration = duration;
    }
}
