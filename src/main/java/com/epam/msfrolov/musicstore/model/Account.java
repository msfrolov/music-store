package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.xml.util.MoneyXmlAdapter;
import org.joda.money.Money;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.NONE)
public class Account extends BaseEntity {

    private User owner;

    @XmlJavaTypeAdapter(MoneyXmlAdapter.class)
    @XmlElement(name = "value")
    private Money value;

    public Account() {
        this(User.ADMIN);

    }

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

    public User getOwner() {
        return owner;
    }

    public Money getAccountStatement() {
        return value;
    }

    @Override
    public String toString() {
        return "Account{" +
                "owner=" + owner.getName() +
                ", value=" + value +
                '}';
    }


}

