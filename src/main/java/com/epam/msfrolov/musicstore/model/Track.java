package com.epam.msfrolov.musicstore.model;

import org.apache.commons.io.FileUtils;
import org.joda.money.Money;

import java.time.Duration;
import java.util.Comparator;

public class Track extends CommercialMultimediaEntity {

    private Style style;

    public static final Comparator<Track> COMPARE_DURATION = (o1, o2) -> o1.getDuration().compareTo(o2.getDuration());
    public static final Comparator<Track> COMPARE_ID = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    public static final Comparator<Track> COMPARE_NAME = (o1, o2) -> o1.getName().compareTo(o2.getName());
    public static final Comparator<Track> COMPARE_STYLE = (o1, o2) -> o1.getStyle().compareTo(o2.getStyle());

    private Metadata details;

    private Track() {
    }

    public Track(String name, Style style, Duration duration, Money price, Metadata details) {
        if (price == null || duration == null || style == null || name == null) throw new IllegalArgumentException();
        this.setName(name);
        this.setStyle(style);
        this.setDuration(duration);
        this.setPrice(price);
        this.setDetails(details);
    }

    public Metadata getDetails() {
        return details;
    }

    protected void setDetails(Metadata details) {
        this.details = details;
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
    public String toString() {
        return "{ name: " + getName() +
                " style: " + getStyle() +
                " duration: " + Track.durationFormat(getDuration()) +
                " price: " + getPrice() +
                " size: " + FileUtils.byteCountToDisplaySize(getDetails().SIZE_BYTE) +
                " }";
    }

    public static String durationFormat(Duration duration) {
        if (duration == null)
            return "";
        long l = duration.getSeconds();
        return String.format("%d:%02d:%02d", l / 3600, (l % 3600) / 60, (l % 60));
    }


}
