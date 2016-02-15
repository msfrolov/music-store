package com.epam.msfrolov.musicstore.model;

import java.util.*;

public class Style extends NamedEntity implements Comparable<Style> {

    public static final Style NON_STYLE;

    static {
        NON_STYLE = new Style("NON STYLE");
    }

    public Style(String name) {
        this.setName(name);
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

    @Override
    public String toString() {
        return this.getName();
    }
}
