package com.epam.msfrolov.musicstore.model;

import com.epam.msfrolov.musicstore.xml.util.MoneyXmlAdapter;
import org.joda.money.Money;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class CommercialMultimediaEntity extends MultimediaEntity {
    @XmlJavaTypeAdapter(MoneyXmlAdapter.class)
    @XmlElement
    private Money price;

    public Money getPrice() {
        return price;
    }

    protected void setPrice(Money price) {
        this.price = price;
    }

}
