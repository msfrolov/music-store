package com.epam.msfrolov.musicstore.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

import static com.epam.msfrolov.musicstore.model.Track.durationFormat;

@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.NONE)
public class User extends NamedEntity {
    public static User ADMIN = new User("admin");

    @XmlElement(name = "account")
    private Account account;

    @XmlElement(name = "boughtTracks", type = Playlist.class)
    private Playlist boughtTracks;
    private List<Playlist> playlists;

    public User() {
        this.account = new Account(this);
        this.boughtTracks = new Playlist(this);
        this.playlists = new ArrayList<>();
    }

    public User(String name) {
        this.setName(name);
        this.account = new Account(this);
        this.boughtTracks = new Playlist(this);
        this.playlists = new ArrayList<>();
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
        return "User{ " + getName() + " }";
    }

    public String toStringWithDetails() {
        return "   {User " + getName() +
                "\n" +
                "   Account " + account +
                "\n" +
                "   Bought track " +
                "number: " + boughtTracks.size() +
                "; duration: " + durationFormat(boughtTracks.getDuration()) +
                "\n" +
                "   Number playlists " + playlists.size() +
                '}';
    }


}
