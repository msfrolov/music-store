package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

import java.util.ArrayList;

public class User extends NamedEntity implements Indexable {
    private static int INDEX;
    public static User ADMIN = new User("admin");
    private Money account = Money.parse("KZT 0");
    private ArrayList<Playlist> playlists;

    public User() {
        this.setId(createIndex());
    }

    public User(String name) {
        this();
        this.setName(name);
    }

    public Playlist createPlaylist(String name) {
        Playlist newPlaylist = new Playlist(this, name);
        playlists.add(newPlaylist);
        return newPlaylist;
    }

    public Money getAccount() {
        return account;
    }

    protected void setAccount(Money account) {
        this.account = account;
    }



    @Override
    public int createIndex() {
        return INDEX++;
    }

    @Override
    public String toString() {
        return "user" + this.getName() + ' ' +
                "@" + account;
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
