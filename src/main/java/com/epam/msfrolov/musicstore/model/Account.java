package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

public class Account extends BaseEntity{
    private User owner;
    private Money value;

    public Account(User user){
        this.owner = user;
    }

    public void writeOff(Money value) {
        if (value.isGreaterThan(this.value))
            throw new IllegalArgumentException();
        this.value = this.value.minus(value);
    }

    public void accrual(Money value) {
        this.value = this.value.plus(value);
    }

    public boolean canSpend(Money value) {
        return !value.isGreaterThan(this.value);
    }
}

