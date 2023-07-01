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
}