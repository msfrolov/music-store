package com.epam.msfrolov.musicstore.model;

import org.joda.money.Money;

import java.time.Duration;
import java.util.ArrayList;

public class User extends NamedEntity {
    public static User ADMIN = new User("admin");
    private Money account = Money.parse("KZT 0");
    private ArrayList<Playlist> playlists;

    public User() {
    }

    public User(String name) {
        this();
        this.setName(name);
    }

    public static User createRandomUser(){
        return new User();
    }

    public Playlist createPlaylist(String name) {
        Playlist newPlaylist = new Playlist(this, name);
        playlists.add(newPlaylist);
        return newPlaylist;
    }

    public void plusMoney(int i) {
        this.account.plus(Money.parse("KZT " + (i)));
    }

    public void minusMoney(int i) {
        this.account.minus(Money.parse("KZT " + (i)));
    }

    public void plusMoney(Money i) {
        this.account.plus(i);
    }

    public void minusMoney(Money i) {
        this.account.minus(i);
    }

    public boolean buyTrack(Track track, Playlist playlist) {
        if (playlist.getOwner() != this)
            throw new IllegalArgumentException();
        if (this.account.isLessThan(track.getPrice()))
            return false;

        this.minusMoney(track.getPrice());
        playlist.add(track);
        return true;
    }

    public boolean buyAlbum(Track track, Playlist playlist) {
        if (playlist.getOwner() != this)
            throw new IllegalArgumentException();
        if (this.account.isLessThan(track.getPrice()))
            return false;

        this.minusMoney(track.getPrice());
        playlist.add(track);
        return true;
    }

    public Money getAccount() {
        return account;
    }

    protected void setAccount(Money account) {
        this.account = account;
    }


    @Override
    public String toString() {
        return "user" + this.getName() + ' ' +
                " " + account;
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
