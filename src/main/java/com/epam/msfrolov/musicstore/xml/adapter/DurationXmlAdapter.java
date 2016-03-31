package com.epam.msfrolov.musicstore.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Duration;

public class DurationXmlAdapter extends XmlAdapter<String, Duration> {
    @Override
    public Duration unmarshal(String s) throws Exception {
        return Duration.ofSeconds(Long.valueOf(s));
    }

    @Override
    public String marshal(Duration duration) throws Exception {
        return String.valueOf(duration.getSeconds());
    }
}
