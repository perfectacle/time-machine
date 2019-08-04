package com.github.perfectacle;

import java.time.*;

/**
 * Time travel at the time with various kinds of Java 8 Date/Time API
 *
 * Created by perfectacle on 04/08/2019
 */
public class TimeMachine {
    private static Clock clock = Clock.systemUTC();
    private static ZoneId timeZone = ZoneOffset.UTC;

    private TimeMachine() {}

    /**
     * Time travel at the time zone(offset and region)
     *
     * @param zone The time zone(offset and region) what you want to travel
     * @return The time zone(offset and region) what you want to travel
     */
    public static ZoneId travelAt(final ZoneId zone) {
        timeZone = zone;
        return timeZone;
    }

    /**
     * Time travel at the instant of time
     *
     * @param time The instant of time what you want to travel
     * @return The instant of time what you want to travel
     */
    public static Instant travelAt(final Instant time) {
        clock = Clock.fixed(time, timeZone);
        return time;
    }

    /**
     * Time travel at the time with time zone(offset and region) and date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS)
     *
     * @param time The time with time zone(offset and region) and date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) what you want to travel
     * @return The time with time zone(offset and region) and date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) what you want to travel
     */
    public static ZonedDateTime travelAt(final ZonedDateTime time) {
        timeZone = time.getZone();
        clock = Clock.fixed(time.toInstant(), timeZone);
        return time;
    }

    /**
     * Time travel at the time with time zone(offset) and date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS)
     *
     * @param time The time with time zone(offset) and date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) what you want to travel
     * @return The time with time zone(offset) and date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) what you want to travel
     */
    public static OffsetDateTime travelAt(final OffsetDateTime time) {
        timeZone = time.getOffset();
        clock = Clock.fixed(time.toInstant(), timeZone);
        return time;
    }

    /**
     * Time travel at the time with time zone(offset) and clock time(HH:mm:ss.SSS)
     *
     * @param time The time with time zone(offset) and clock time(HH:mm:ss.SSS) what you want to travel
     * @return The time with time zone(offset) and clock time(HH:mm:ss.SSS) what you want to travel
     */
    public static OffsetTime travelAt(final OffsetTime time) {
        timeZone = time.getOffset();
        clock = Clock.fixed(time.atDate(LocalDate.now()).toInstant(), timeZone);
        return time;
    }

    /**
     * Time travel at the time with date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS)
     *
     * @param time The time with date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) what you want to travel
     * @return The time with time date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) what you want to travel
     */
    public static LocalDateTime travelAt(final LocalDateTime time) {
        clock = Clock.fixed(time.atZone(timeZone).toInstant(), timeZone);
        return time;
    }

    /**
     * Time travel at the time with date(yyyy-MM-dd)
     *
     * @param time The time with date(yyyy-MM-dd) what you want to travel
     * @return The time with time date(yyyy-MM-dd) what you want to travel
     */
    public static LocalDate travelAt(final LocalDate time) {
        clock = Clock.fixed(time.atStartOfDay(timeZone).toInstant(), timeZone);
        return time;
    }

    /**
     * Time travel at the time with clock time(HH:mm:ss.SSS)
     *
     * @param time The time with clock time(HH:mm:ss.SSS) what you want to travel
     * @return The time with clock time(HH:mm:ss.SSS) what you want to travel
     */
    public static LocalTime travelAt(final LocalTime time) {
        clock = Clock.fixed(time.atDate(LocalDate.now()).atZone(timeZone).toInstant(), timeZone);
        return time;
    }

    /**
     * Returns the instant of time when you traveled
     *
     * @return The instant of time when you traveled
     */
    public static Instant instantOfNow() {
        return Instant.now(clock);
    }

    /**
     * Returns the time with time zone(offset and region) and date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) when you traveled
     *
     * @return The time with time zone(offset and region) and date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) when you traveled
     */
    public static ZonedDateTime zonedDateTimeOfNow() {
        return ZonedDateTime.now(clock);
    }

    /**
     * Returns the time with time zone(offset) and date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) when you traveled
     *
     * @return The time with time zone(offset) and date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) when you traveled
     */
    public static OffsetDateTime offsetDateTimeOfNow() {
        return OffsetDateTime.now(clock);
    }

    /**
     * Returns the time with time zone(offset) and clock time(HH:mm:ss.SSS) when you traveled
     *
     * @return The time with time zone(offset) and clock time(HH:mm:ss.SSS) when you traveled
     */
    public static OffsetTime offsetTimeOfNow() {
        return OffsetTime.now(clock);
    }

    /**
     * Returns the time with date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) when you traveled
     *
     * @return The time with date(yyyy-MM-dd) and clock time(HH:mm:ss.SSS) when you traveled
     */
    public static LocalDateTime localDateTimeOfNow() {
        return LocalDateTime.now(clock);
    }

    /**
     * Returns the time with date(yyyy-MM-dd) when you traveled
     *
     * @return The time with date(yyyy-MM-dd) when you traveled
     */
    public static LocalDate localDateOfNow() {
        return LocalDate.now(clock);
    }

    /**
     * Returns the time with clock time(HH:mm:ss.SSS) when you traveled
     *
     * @return The time with clock time(HH:mm:ss.SSS) when you traveled
     */
    public static LocalTime localTimeOfNow() {
        return LocalTime.now(clock);
    }

    /**
     * Reset time of now for real world
     */
    public static void reset() {
        clock = Clock.systemUTC();
        timeZone = ZoneOffset.UTC;
    }
}
