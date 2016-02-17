package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

public abstract class CommercialMultimediaEntity extends MultimediaEntity {
    private Money price;

    public Money getPrice() {
        return price;
    }

    protected void setPrice(Money price) {
        this.price = price;
    }

}
