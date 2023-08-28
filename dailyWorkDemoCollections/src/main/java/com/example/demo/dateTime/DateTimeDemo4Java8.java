package com.example.demo.dateTime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-06-28 14:25
 **/

public class DateTimeDemo4Java8 {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        //System.out.println(now.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        System.out.println(now.format(DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm:ss")));
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        Duration du = Duration.between(now, end);
        System.out.println(du.toDays());
        System.out.println(du.toHours());
        System.out.println(du.toMinutes());
        System.out.println("-----------------");
        System.out.println(Instant.now());
        System.out.println(Instant.now().plus(8, ChronoUnit.HOURS));
    }


    public static void localDateTime2Date() {

        String timeString = "2023-08-25 10:30:00";
        String pattern = "yyyy-MM-dd HH:mm:ss";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.parse(timeString, formatter);

        Date date = convertToDate(localDateTime);
        System.out.println(date);

    }

    private static Date convertToDate(LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }
}
