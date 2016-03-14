package com.epam.msfrolov.musicstore.xml.util;

import com.epam.msfrolov.musicstore.model.Style;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StyleXmlAdapter extends XmlAdapter<String, Style> {

    @Override
    public Style unmarshal(String s) throws Exception {
        return new Style(s);
    }

    @Override
    public String marshal(Style style) throws Exception {
        return style.toString();
    }
}
