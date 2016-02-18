package com.epam.msfrolov.musicstore.factory1;

import com.epam.msfrolov.musicstore.model.Metadata;
import com.epam.msfrolov.musicstore.model.Style;
import com.epam.msfrolov.musicstore.model.Track;
import com.epam.msfrolov.musicstore.util.ServiceRandom;
import org.joda.money.Money;

import java.time.Duration;

public class TrackFactory {

    public static Track createTrack() {
        Metadata details = new Metadata(ServiceRandom.getRandomAlbum(), ServiceRandom.getRandomArtist(),
                ServiceRandom.getRandomFileSize(), ServiceRandom.getRandomComment());
        String name = ServiceRandom.getRandomName();
        Duration duration = ServiceRandom.getRandomDuration();
        Money price = ServiceRandom.getRandomPrise();
        Style style = new Style(ServiceRandom.getRandomStyle());
        Track track = new Track(name, style, duration, price, details);
        track.setId(ServiceRandom.getRandomId());
        return track;
    }
}
