package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.xml.jaxb.StyleXmlAdapter;
import org.apache.commons.io.FileUtils;
import org.joda.money.Money;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.Duration;
import java.util.Comparator;
@XmlAccessorType(XmlAccessType.NONE)
public class Track extends CommercialMultimediaEntity {
    @XmlJavaTypeAdapter(StyleXmlAdapter.class)
    @XmlElement
    private Style style;

    public static final Comparator<Track> COMPARE_DURATION = (o1, o2) -> o1.getDuration().compareTo(o2.getDuration());
    public static final Comparator<Track> COMPARE_ID = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    public static final Comparator<Track> COMPARE_NAME = (o1, o2) -> o1.getName().compareTo(o2.getName());
    public static final Comparator<Track> COMPARE_STYLE = (o1, o2) -> o1.getStyle().compareTo(o2.getStyle());

    private Metadata details;

    public Track() {
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
    public void setPrice(Money price) {
        super.setPrice(price);
    }

    @Override
    public void setDuration(Duration duration) {
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
        return "{ " +
                "id: " + getId() +
                " name: " + getName() +
                " style: " + getStyle() +
                " duration: " + Track.durationFormat(getDuration()) +
                " price: " + getPrice() +
                " size: " + (getDetails() == null ? "-/-" : FileUtils.byteCountToDisplaySize(getDetails().SIZE_BYTE)) +
                " }";
    }

    public static String durationFormat(Duration duration) {
        if (duration == null)
            return "";
        long l = duration.getSeconds();
        return String.format("%d:%02d:%02d", l / 3600, (l % 3600) / 60, (l % 60));
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
}
