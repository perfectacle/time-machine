package com.github.perfectacle;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class TimeMachineTest {
    @Test
    void now() {
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