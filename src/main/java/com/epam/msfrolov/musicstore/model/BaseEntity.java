package com.epam.msfrolov.musicstore.model;

public abstract class BaseEntity {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == 0);
    }
}
