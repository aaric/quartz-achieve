package com.github.aaric.quartz.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * NewTimeTests
 *
 * @author Aaric, created on 2022-05-17T08:52.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
public class NewTimeTests {

    @Test
    public void testZone() {
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        log.info("{}", zoneId);
        zoneId = ZoneOffset.of("+08:00");
        log.info("{}", zoneId);
    }

    @Test
    public void testDate() {
        Assertions.assertDoesNotThrow(() -> {
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LocalDate date = LocalDate.now();
            date = date.with(ChronoField.MONTH_OF_YEAR, 6)
                    .withDayOfMonth(1);
            log.info("{}", date);
//            log.info("{}", dateFormat.format(date)); -- error: Cannot format given Object as a Date);
            log.info("{}-{}-{}", date.getYear(), date.getMonthValue(), date.getDayOfMonth());
            log.info("{}-{}-{}", date.get(ChronoField.YEAR_OF_ERA), date.get(ChronoField.MONTH_OF_YEAR), date.get(ChronoField.DAY_OF_MONTH));
            date = LocalDate.parse("2022-05-17", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            log.info("{}", date);
            date = date.plusDays(30);
            log.info("{}", date.format(DateTimeFormatter.ofPattern("MMM dd, YYYY")));
        });
    }

    @Test
    public void testTime() {
        Assertions.assertDoesNotThrow(() -> {
            LocalTime time = LocalTime.now();
            time = time.with(ChronoField.SECOND_OF_MINUTE, 30).withSecond(15);
            log.info("{}", time);
            log.info("{}:{}:{}", time.getHour(), time.getMinute(), time.getSecond());
            log.info("{}:{}:{}.{}", time.get(ChronoField.HOUR_OF_DAY), time.get(ChronoField.MINUTE_OF_HOUR),
                    time.get(ChronoField.SECOND_OF_MINUTE), time.get(ChronoField.MILLI_OF_SECOND));
            time = LocalTime.parse("12:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
            log.info("{}", time);
            log.info("{}", time.format(DateTimeFormatter.ofPattern("HH:mm:ss.S")));
        });
    }

    @Test
    public void testDateTime() {
        Assertions.assertDoesNotThrow(() -> {
            LocalDateTime dateTime = LocalDateTime.now();
            log.info("{}", dateTime.truncatedTo(ChronoUnit.DAYS));
            dateTime = LocalDateTime.parse("2022-06-01 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            log.info("{}", dateTime);
            log.info("{}", dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
        });
    }

    @Test
    public void testDuration() {
        Assertions.assertDoesNotThrow(() -> {
            Instant start = Instant.now();
            TimeUnit.SECONDS.sleep(1);
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            log.info("{}", duration.toMillis());
        });
    }
}
