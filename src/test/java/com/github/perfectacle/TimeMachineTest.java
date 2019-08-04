package com.github.perfectacle;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class TimeMachineTest {
    @Test
    void alwaysPM() {
        final LocalTime pm = LocalTime.of(12, 0, 0, 0);
        TimeMachine.travelAt(pm.plusNanos(1));
        assertTrue(TimeMachine.localTimeOfNow().isAfter(pm));
    }

    @Test
    void alwaysChristmas() {
        final LocalDate christmas = LocalDate.of(LocalDate.now().getYear(), 12, 25);
        TimeMachine.travelAt(christmas);
        assertTrue(TimeMachine.localDateOfNow().isEqual(christmas));
    }

    @Test
    void alwaysMyBirthdayInSouthKorea() {
        final ZonedDateTime birthday = ZonedDateTime.of(1993, 5, 30, 0, 0, 0, 0, ZoneId.of("Asia/Seoul"));
        TimeMachine.travelAt(birthday);
        assertTrue(TimeMachine.zonedDateTimeOfNow().isEqual(birthday));
    }

    @Test
    void whenNotFixed() {
        // Reset to time machine to avoid being affected by other tests.
        TimeMachine.reset();

        final Instant nowOfInstant = Instant.now();
        final Instant nowOfInstantWithTimeMachine = TimeMachine.instantOfNow();

        assertEquals(nowOfInstant.minusNanos(nowOfInstant.getNano()), nowOfInstantWithTimeMachine.minusNanos(nowOfInstantWithTimeMachine.getNano()));
        assertEquals(ZonedDateTime.now(ZoneOffset.UTC).withNano(0), TimeMachine.zonedDateTimeOfNow().withNano(0));
        assertEquals(OffsetDateTime.now(ZoneOffset.UTC).withNano(0), TimeMachine.offsetDateTimeOfNow().withNano(0));
        assertEquals(OffsetTime.now(ZoneOffset.UTC).withNano(0), TimeMachine.offsetTimeOfNow().withNano(0));
        assertEquals(LocalDateTime.now(ZoneOffset.UTC).withNano(0), TimeMachine.localDateTimeOfNow().withNano(0));
        assertEquals(LocalDate.now(ZoneOffset.UTC), TimeMachine.localDateOfNow());
        assertEquals(LocalTime.now(ZoneOffset.UTC).withNano(0), TimeMachine.localTimeOfNow().withNano(0));
    }
}