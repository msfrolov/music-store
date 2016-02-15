package com.epam.msfrolov.musicstore.model;

import org.apache.commons.io.FileUtils;
import org.joda.money.Money;

import java.time.Duration;
import java.util.Comparator;

public class Track extends CommercialMultimediaEntity {

    private Style style;

    public static final Comparator<Track> COMPARE_DURATION = (o1, o2) -> o1.getDuration().compareTo(o2.getDuration());
    public static final Comparator<Track> COMPARE_ID = (o1, o2) -> o1.getId().compareTo(o2.getId());
    public static final Comparator<Track> COMPARE_NAME = (o1, o2) -> o1.getName().compareTo(o2.getName());
    public static final Comparator<Track> COMPARE_STYLE = (o1, o2) -> o1.getStyle().compareTo(o2.getStyle());

    private Track(){
    }

    public Track(String name, Style style, Duration duration, Money price, Metadata details) {
        this.setName(name);
        this.setStyle(style);
        this.setDuration(duration);
        this.setPrice(price);
        this.setDetails(details);
    }

    @Override
    protected void setPrice(Money price) {
        super.setPrice(price);
    }

    @Override
    protected void setDuration(Duration duration) {
        super.setDuration(duration);
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Track track = (Track) o;

        return style != null ? style.equals(track.style) : track.style == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (style != null ? style.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getName() + ' ' +
                getStyle() + ' ' +
                getDuration() + ' ' +
                getPrice();
    }


}
