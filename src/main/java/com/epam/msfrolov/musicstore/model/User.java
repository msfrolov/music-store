package com.epam.msfrolov.musicstore.model;

import java.util.ArrayList;
import java.util.List;

public class User extends NamedEntity {
    public static User ADMIN = new User("admin");
    private Account account;
    private Playlist boughtTracks = new Playlist(this);
    private List<Playlist> playlists = new ArrayList<>();

    public User(String name) {
        this.setName(name);
        this.account = new Account(this);
    }

    public Playlist getBoughtTracks() {
        return boughtTracks;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
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
        return getName();

    }
}
