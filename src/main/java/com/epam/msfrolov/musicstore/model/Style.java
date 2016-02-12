package com.epam.msfrolov.musicstore.model;

public class Style extends NamedEntity implements Indexable {
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
}
