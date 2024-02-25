package com.serheev.springshelldoc.schema;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// https://stackoverflow.com/a/36157446/548473
public class LocalDateTimeXmlAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public String marshal(LocalDateTime ldt) {
        return ldt.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public LocalDateTime unmarshal(String str) {
        return LocalDateTime.parse(str);
    }
}
