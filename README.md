# Stop to use Java 8 Date/Time API of now() method
Suppose you have a code that uses the now() method in the Date/Time API of Java 8.

```java
public class SomeClass {
    public static boolean someMethod() {
        return someMethod2();
    }
    
    private static boolean someMethod2() {
        return LocalTime.now().isAfter(LocalTime.of(12, 0, 0));
    }
}
```

Time is out of control.

```java
public class SomeClassTest {
    // In only PM, this test passed
    @Test
    void someMethod() {
        assertTrue(SomeClass.someMethod());
    }
}
```

Therefore, meaningless parameters(current time of now) must be passed continuously.

```java
public class SomeClass {
    public static boolean someMethod(final LocalTime now) {
        return someMethod2(now);
    }
    
    private static boolean someMethod2(final LocalTime now) {
        return now.isAfter(LocalTime.of(12, 0, 0));
    }
}
```

We can control time only by repeating this meaningless act(passing the parameters).
```java
public class SomeClassTest {
    // This test passed in always by passing parameter(current time of now)
    @Test
    void someMethod() {
        assertTrue(SomeClass.someMethod(LocalTime.of(13, 0, 0)));
    }
}
```

# Use Time Machine
If the public method internally calls dozens of private methods, would you pass the current time to the parameter each time?
To eliminate this inefficiency, you should use Time Machine.

## Use various kinds of now method
```java
public class SomeClass {
    public static boolean someMethod() {
        return someMethod2();
    }
    
    private static boolean someMethod2() {
        return TimeMachine.nowOfLocalTime().isAfter(LocalTime.of(12, 0, 0));
    }
}
```

## Time travel at the time with various kinds of Java 8 Date/Time API
```java
public class SomeClassTest {
    // This test passed in always
    @Test
    void someMethod() {
        TimeMachine.travelAt(LocalTime.of(13, 0, 0));
        assertTrue(SomeClass.someMethod());
    }
}
```

## Reset of now for real world
```java
public class SomeClassTest {
    @Test
    void whenNotFixed() {
        // Reset to time machine to avoid being affected by other tests.
        TimeMachine.reset();
    
        final Instant nowOfInstant = Instant.now();
        final Instant nowOfInstantWithTimeMachine = TimeMachine.nowOfInstant();
    
        assertEquals(nowOfInstant.minusNanos(nowOfInstant.getNano()), nowOfInstantWithTimeMachine.minusNanos(nowOfInstantWithTimeMachine.getNano()));
        assertEquals(ZonedDateTime.now(ZoneOffset.UTC).withNano(0), TimeMachine.nowOfZonedDateTime().withNano(0));
        assertEquals(OffsetDateTime.now(ZoneOffset.UTC).withNano(0), TimeMachine.nowOfOffsetDateTime().withNano(0));
        assertEquals(OffsetTime.now(ZoneOffset.UTC).withNano(0), TimeMachine.nowOfOffsetTime().withNano(0));
        assertEquals(LocalDateTime.now(ZoneOffset.UTC).withNano(0), TimeMachine.nowOfLocalDateTime().withNano(0));
        assertEquals(LocalDate.now(ZoneOffset.UTC), TimeMachine.nowOfLocalDate());
        assertEquals(LocalTime.now(ZoneOffset.UTC).withNano(0), TimeMachine.nowOfLocalTime().withNano(0));
    }
}
```

## How to use
[Javadoc](./docs/index.html)
