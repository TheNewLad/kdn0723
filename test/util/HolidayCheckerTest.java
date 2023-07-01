package util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HolidayCheckerTest {
    @Test
    public void Should_ReturnFalse_When_DateIsNotAHoliday() {
        LocalDate date = LocalDate.of(2023, 2, 10);
        assertFalse(HolidayChecker.isHoliday(date));
    }

    @Test
    public void Should_ReturnTrue_When_WeekdayDateIsIndependenceDay() {
        LocalDate date = LocalDate.of(2023, 7, 4);
        assertTrue(HolidayChecker.isHoliday(date));
    }

    @Test
    public void Should_ReturnFalse_When_WeekendDateIsIndependenceDay() {
        LocalDate saturdayIndependenceDay = LocalDate.of(2020, 7, 4);
        LocalDate sundayIndependenceDay = LocalDate.of(2021, 7, 4);
        assertAll(
                () -> assertFalse(HolidayChecker.isHoliday(saturdayIndependenceDay)),
                () -> assertFalse(HolidayChecker.isHoliday(sundayIndependenceDay))
        );
    }

    @Test
    public void Should_ReturnTrue_When_WeekdayDateIsIndependenceDayObserved() {
        LocalDate saturdayIndependenceDay = LocalDate.of(2020, 7, 3);
        LocalDate sundayIndependenceDay = LocalDate.of(2021, 7, 5);
        assertAll(
                () -> assertTrue(HolidayChecker.isHoliday(saturdayIndependenceDay)),
                () -> assertTrue(HolidayChecker.isHoliday(sundayIndependenceDay))
        );
    }

    @Test
    public void Should_ReturnTrue_When_DateIsLaborDay() {
        LocalDate date = LocalDate.of(2023, 9, 4);
        assertTrue(HolidayChecker.isHoliday(date));
    }
}