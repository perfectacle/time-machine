# 사용방법
[Javadoc 보기](https://perfectacle.github.io/time-machine/docs/javadoc/)

## 설치
Maven을 사용하는 경우
```xml
<dependency>
	<groupId>com.github.perfectacle</groupId>
	<artifactId>time-machine</artifactId>
	<version>1.0.0</version>
</dependency>
```

Gradle을 사용하는 경우
```groovy
implementation 'com.github.perfectacle:time-machine:1.0.0'
```

## 번역 문서
* [English](https://perfectacle.github.io/time-machine/)

# Java 8 Date/Time API의 now() 메서드 사용을 멈춰라.
Java 8 Date/Time API의 now() 메서드를 사용하는 코드가 있다고 가정해보자.

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

시간은 통제 불가능 영역이다.

```java
public class SomeClassTest {
    // 이 테스트는 오후에만 통과된다.
    @Test
    void someMethod() {
        assertTrue(SomeClass.someMethod());
    }
}
```

따라서 우리는 의미 없는 파라미터(현재 시간)을 계속해서 넘겨줘야한다.

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

우리는 의미 없는 행위(파라미터를 넘기는)를 반복함으로써 시간을 통제할 수 있다.
```java
public class SomeClassTest {
    // 이 테스트는 파라미터(현재 시간)를 넘김으로써 언제든 성공하는 테스트가 됐다.
    @Test
    void someMethod() {
        assertTrue(SomeClass.someMethod(LocalTime.of(13, 0, 0)));
    }
}
```

# Time Machine을 사용해라.
public 메서드 내부에서 수 십개의 private 메서드를 사용한다면, 매번 현재 시간을 파라미터로써 넘길 것인가?  
이런 비효율성을 제거하기 위해 Time Machine을 사용해야한다.

## 다양한 종류의 now 메서드를 사용하라
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

## 다양한 종류의 Java 8 Date/Time API를 사용해서 시간 여행을 떠나라.
```java
public class SomeClassTest {
    // 이 테스트는 항상 통과한다.
    @Test
    void someMethod() {
        TimeMachine.travelAt(LocalTime.of(13, 0, 0));
        assertTrue(SomeClass.someMethod());
    }
}
```

## 현실 세계의 현재 시간을 사용하기 위해 Time Machine을 리셋해라.
```java
public class SomeClassTest {
    @Test
    void whenNotTraveled() {
        // 다른 테스트의 영향을 받지 않으려면 Time Machine을 리셋해야한다.
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
