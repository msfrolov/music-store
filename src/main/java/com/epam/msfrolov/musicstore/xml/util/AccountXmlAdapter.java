package com.epam.msfrolov.musicstore.xml.util;

import com.epam.msfrolov.musicstore.model.Account;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AccountXmlAdapter extends XmlAdapter<String, Account> {
    private static final Logger log = LoggerFactory.getLogger(AccountXmlAdapter.class);

    @Override
    public Account unmarshal(String s) throws Exception {
        Account account = new Account();
        account.accrual(Money.parse(s));
        return account;
    }

    @Override
    public String marshal(Account account) throws Exception {
        return account.getAccountStatement().toString();
    }
}