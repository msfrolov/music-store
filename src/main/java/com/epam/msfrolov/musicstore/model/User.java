package com.epam.msfrolov.musicstore.model;

import java.util.ArrayList;
import java.util.List;

public class User extends NamedEntity {
    public static User ADMIN = new User("admin");
    private Account account;
    private List<Track> boughtTracks = new ArrayList<>();
    private List<Playlist> playlists = new ArrayList<>();

    public User(String name) {
        this.setName(name);
    }

    public List<Track> getBoughtTracks() {
        return boughtTracks;
    }

    public Playlist createPlaylist(String name) {
        Playlist newPlaylist = new Playlist(this, name);
        playlists.add(newPlaylist);
        return newPlaylist;
    }

    public Account getAccount() {
        return account;
    }

    protected void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public String toString() {
        return "user: " + this.getName() + ' ' +
                " " + this.account;
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
