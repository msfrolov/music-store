package com.epam.msfrolov.musicstore.model;

import java.util.*;

public class Style extends NamedEntity implements Comparable<Style> {
    private static int INDEX;
    public static final Style NON_STYLE;

    private static List<Style> STYLES_LIST = new ArrayList<>();

    static {
        NON_STYLE = new Style("NON STYLE");
    }

    public Style(String name) {
        this.setName(name);
        STYLES_LIST.add(this);
    }

    public static List<Style> getStylesList() {
        return Collections.unmodifiableList(STYLES_LIST);
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
