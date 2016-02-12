package com.epam.msfrolov.musicstore.model;

public abstract class NamedEntity extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
