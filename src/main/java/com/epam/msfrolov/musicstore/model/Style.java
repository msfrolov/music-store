package com.epam.msfrolov.musicstore.model;

public class Style extends NamedEntity implements Comparable<Style> {

    public static final Style NON_STYLE;

    static {
        NON_STYLE = new Style("NON STYLE");
    }

    public Style() {
    }

    public Style(String name) {
        this.setName(name);
    }

    @Override
    public int compareTo(Style style) {
        return this.getName().compareTo(style.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
