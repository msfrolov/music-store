package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

public class Account extends BaseEntity {
    private User owner;
    private Money value;

    public Account(User user) {
        this.owner = user;
        this.value = Money.parse("KZT 0");
    }

    public boolean writeOff(Money value) {
        if (value.isGreaterThan(this.value))
            return false;
        this.value = this.value.minus(value);
        return true;
    }

    public void accrual(Money value) {
        this.value = this.value.plus(value);
    }

    public boolean canSpend(Money value) {
        return !value.isGreaterThan(this.value);
    }

    @Override
    public String toString() {
        return "Account{" +
                "owner=" + owner +
                ", value=" + value +
                '}';
    }
}

