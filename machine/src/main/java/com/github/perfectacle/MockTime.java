package com.github.perfectacle;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZoneOffset;

final class MockTime {
    static Clock clock = Clock.systemUTC();
    static ZoneId timeZone = ZoneOffset.UTC;
}
