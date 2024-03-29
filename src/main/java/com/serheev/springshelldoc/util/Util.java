package com.serheev.springshelldoc.util;

import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class Util {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // https://stackoverflow.com/a/77190837/548473
    public static ZonedDateTime toDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault());
    }

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }

    public static Map<String, Object> parseParams(List<String> params) {
        Map<String, Object> map = new HashMap<>();
        for (String parameter : params) {
            String[] param = parameter.split("=");
            String key = param[0].trim();
            String value = param[1].trim();
            if (key.endsWith("Date")) {
                map.put(key, LocalDate.parse(value));
            } else if (key.endsWith("Time")) {
                map.put(key, LocalTime.parse(value));
            } else {
                throw new IllegalArgumentException("Unknown filter parameter: '" + key + "'");
            }
        }
        return map;
    }
}
