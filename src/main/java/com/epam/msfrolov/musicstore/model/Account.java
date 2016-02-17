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

    @Override
    public String toString() {
        return "Account{" +
                "owner=" + owner +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Account account = (Account) o;

        return owner != null ? owner.equals(account.owner) : account.owner == null && (value != null ? value.equals(account.value) : account.value == null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}

