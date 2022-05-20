package com.github.aaric.quartz.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * TupleTests
 *
 * @author Aaric, created on 2022-05-20T14:19.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
public class TupleTests {

    private static Pair<Integer, Integer> yearMonth(LocalDate date) {
        return ImmutablePair.of(date.getYear(), date.getMonthValue());
    }

    @Test
    public void testPair() {
        Assertions.assertDoesNotThrow(() -> {
            Pair<Integer, Integer> yearMonth = yearMonth(LocalDate.now());
            log.info("{}-{}", yearMonth.getLeft(), yearMonth.getRight());
        });
    }
}
