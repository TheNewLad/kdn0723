package util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DayCheckerTest {
    @Test
    public void Should_ReturnFalse_When_DateIsNotAHoliday() {
        LocalDate date = LocalDate.of(2023, 2, 10);
        assertFalse(DayChecker.isHoliday(date));
    }

    @Test
    public void Should_ReturnTrue_When_WeekdayDateIsIndependenceDay() {
        LocalDate date = LocalDate.of(2023, 7, 4);
        assertTrue(DayChecker.isHoliday(date));
    }

    @Test
    public void Should_ReturnFalse_When_WeekendDateIsIndependenceDay() {
        LocalDate saturdayIndependenceDay = LocalDate.of(2020, 7, 4);
        LocalDate sundayIndependenceDay = LocalDate.of(2021, 7, 4);
        assertAll(
                () -> assertFalse(DayChecker.isHoliday(saturdayIndependenceDay)),
                () -> assertFalse(DayChecker.isHoliday(sundayIndependenceDay))
        );
    }

    @Test
    public void Should_ReturnTrue_When_WeekdayDateIsIndependenceDayObserved() {
        LocalDate saturdayIndependenceDay = LocalDate.of(2020, 7, 3);
        LocalDate sundayIndependenceDay = LocalDate.of(2021, 7, 5);
        assertAll(
                () -> assertTrue(DayChecker.isHoliday(saturdayIndependenceDay)),
                () -> assertTrue(DayChecker.isHoliday(sundayIndependenceDay))
        );
    }

    @Test
    public void Should_ReturnTrue_When_DateIsLaborDay() {
        LocalDate date = LocalDate.of(2023, 9, 4);
        assertTrue(DayChecker.isHoliday(date));
    }
}