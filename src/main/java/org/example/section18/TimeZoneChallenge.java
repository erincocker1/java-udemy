package org.example.section18;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneChallenge {
    public static void main(String[] args) {
        printMeetingTimes("America/New_York", "Australia/Sydney", 2025, 3, 24, 7, 20);
        System.out.println("------------------------------------------------------------");
        printMeetingTimes("America/Los_Angeles", "Europe/London", 2025, 3, 24, 14, 23);
    }

    private static void printMeetingTimes(String zone1Id, String zone2Id, int year,
                                          int month, int dayOfMonth, int earliestHour, int latestHour) {
        ZoneId zone1 = ZoneId.of(zone1Id);
        ZoneId zone2 = ZoneId.of(zone2Id);

        ZonedDateTime zone1Time = LocalDateTime.of(year, month, dayOfMonth, earliestHour, 0).atZone(zone1);
        ZonedDateTime startingDateTime = zone1Time;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/uuuu");

        while (zone1Time.isBefore(startingDateTime.plusDays(5))) {
            ZonedDateTime zone2Time = zone1Time.withZoneSameInstant(zone2);
            if (zone2Time.getDayOfWeek() != DayOfWeek.SATURDAY &&
                    zone2Time.getDayOfWeek() != DayOfWeek.SUNDAY &&
                    zone2Time.getHour() >= earliestHour && zone2Time.getHour() <= latestHour) {
                System.out.println(zone1Id + ": " + zone1Time.format(formatter) + "    " + zone2Id + ": " + zone2Time.format(formatter));
            }

            zone1Time = zone1Time.plusHours(1);
            while (zone1Time.getHour() > latestHour || zone1Time.getHour() < earliestHour) {
                zone1Time = zone1Time.plusHours(1);
            }
        }
    }
}
