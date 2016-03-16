package com.epam.msfrolov.musicstore.xml.adapter;

import com.epam.msfrolov.musicstore.model.Style;
import com.epam.msfrolov.musicstore.model.Track;
import org.joda.money.Money;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Duration;

public class TrackXmlAdapter extends XmlAdapter<String, Track> {

    @Override
    public Track unmarshal(String s) throws Exception {
        String[] strings = s.split("^");
        Track track = new Track();
        track.setName(strings[0]);
        track.setStyle(new Style(strings[1]));
        track.setDuration(Duration.ofSeconds(Long.valueOf(strings[2])));
        track.setPrice(Money.parse(strings[3]));
        return track;
    }

    @Override
    public String marshal(Track track) throws Exception {
        return track.getName() +
                "^" +
                track.getStyle().toString() +
                "^" +
                track.getDuration().getSeconds() +
                "^" +
                track.getPrice().toString();
    }
}
