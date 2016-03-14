package com.epam.msfrolov.musicstore.xml.util;

import org.joda.money.Money;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MoneyXmlAdapter extends XmlAdapter<String,Money> {

    @Override
    public Money unmarshal(String s) throws Exception {
        return Money.parse(s);
    }

    @Override
    public String marshal(Money money) throws Exception {
        return money.toString();
    }
}
