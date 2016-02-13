package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

import java.time.Duration;

public class Track extends CommercialMultimediaEntity implements Indexable {
    private static int INDEX;
    private Style style;


    private Track(){
        this.setId(createIndex());
    }

    public Track(String name) {
        this();
        this.setName(name);
        this.setStyle(Style.NON_STYLE);
    }

    public Track(String name, Style style) {
        this();
        this.setName(name);
        this.style = style;
    }
    public Track(String name, Style style, Duration duration, Money price) {
        this(name, style);
        this.setDuration(duration);
        this.setPrice(price);
    }

    @Override
    protected void setPrice(Money price) {
        super.setPrice(price);
    }

    @Override
    protected void setDuration(Duration duration) {
        super.setDuration(duration);
    }

    public void correctionPrice(Money price) {
        this.setPrice(price);
        AllLists.recalculateValues(this);
    }

    public void correctionDuration(Duration duration) {
        this.setDuration(duration);
        AllLists.recalculateValues(this);
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    @Override
    public int createIndex() {
        return 0;
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
