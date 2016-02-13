package com.epam.msfrolov.musicstore.model;

public class Style extends NamedEntity implements Comparable<Style>, Indexable {
    private static int INDEX;
    public static Style NON_STYLE;
    static {
        NON_STYLE = new Style("NON STYLE");
    }

    public Style(String name) {
        this.setId(createIndex());
        this.setName(name);
    }

    @Override
    public int createIndex() {
        return INDEX++;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Style style) {
        return this.getName().compareTo(style.getName());
    }
}
