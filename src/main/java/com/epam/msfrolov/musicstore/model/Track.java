package com.epam.msfrolov.musicstore.model;

public class Track extends CommercialMultimediaEntity implements Indexable {
    private static int INDEX;
    private Style style;

    public Track(String name) {
        this.setId(createIndex());
        this.setName(name);
        this.setStyle(Style.NON_STYLE);
    }

    public Track(String name, Style style) {
        this.setId(createIndex());
        this.setName(name);
        this.style = style;
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
