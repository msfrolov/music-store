package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

public abstract class CommercialMultimediaEntity extends MultimediaEntity {
    private Money price;

    protected Money getPrice() {
        return price;
    }

    protected void setPrice(Money price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CommercialMultimediaEntity that = (CommercialMultimediaEntity) o;

        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
