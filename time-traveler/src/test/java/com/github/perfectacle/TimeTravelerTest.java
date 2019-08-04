package com.github.perfectacle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeTravelerTest {
    @BeforeEach
    void setup() {
        // Reset time to avoid being affected by other tests.
        TimeTraveler.reset();
    }

    @Test
    void alwaysPM() {
        final LocalTime pm = LocalTime.of(12, 0, 0, 0);
        TimeTraveler.travelAt(pm.plusNanos(1));
        assertTrue(TimeMachine.localTimeOfNow().isAfter(pm));
    }

    @Test
    void alwaysChristmas() {
        final LocalDate christmas = LocalDate.of(LocalDate.now().getYear(), 12, 25);
        TimeTraveler.travelAt(christmas);
        assertTrue(TimeMachine.localDateOfNow().isEqual(christmas));
    }

    @Test
    void alwaysMyBirthdayInSouthKorea() {
        final ZonedDateTime birthday = ZonedDateTime.of(1993, 5, 30, 0, 0, 0, 0, ZoneId.of("Asia/Seoul"));
        TimeTraveler.travelAt(birthday);

        assertTrue(TimeMachine.zonedDateTimeOfNow().isEqual(birthday));
    }
}