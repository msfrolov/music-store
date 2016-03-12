package com.epam.msfrolov.musicstore.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BaseEntity {
    @XmlElement
    private Integer id;

    public Integer getId() {
        if (id == null)
            return -1;
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public boolean equals(Object o) {
        BaseEntity that = (BaseEntity) o;
        if (this.getId() == null & that.getId() == null) throw new IllegalArgumentException();
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
