package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

//TODO implem. inc. and dec. account
public class User extends NamedEntity implements Indexable {
    private static int INDEX;
    private static User ADMIN = new User("admin");
    private Money account;


    public User() {
        this.setId(createIndex());
    }

    public User(String name) {
        this.setId(createIndex());
        this.setName(name);
    }

    private Money getAccount() {
        return account;
    }

    private void setAccount(Money account) {
        this.account = account;
    }

    @Override
    public int createIndex() {
        return INDEX++;
    }

    @Override
    public String toString() {
        return "User: " + this.getName() + ' ' +
                "account:" + account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return account != null ? account.equals(user.account) : user.account == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }

}
