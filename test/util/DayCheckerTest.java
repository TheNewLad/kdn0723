package util;

import static org.junit.jupiter.api.Assertions.*;
import static util.DayChecker.DayType.*;
import static util.DayChecker.getDayType;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DayCheckerTest {
  @Test
  public void Should_ReturnWeekday_When_WeekdayDateIsNotAHoliday() {
    LocalDate date = LocalDate.of(2023, 2, 10);
    assertEquals(WEEKDAY, getDayType(date));
  }

  @Test
  public void Should_ReturnWeekend_When_WeekendDateIsNotAHoliday() {
    LocalDate date = LocalDate.of(2023, 2, 11);
    assertEquals(WEEKEND, getDayType(date));
  }

  @Test
  public void Should_ReturnHoliday_When_WeekdayDateIsIndependenceDay() {
    LocalDate date = LocalDate.of(2023, 7, 4);
    assertEquals(HOLIDAY, getDayType(date));
  }

  @Test
  public void Should_ReturnWeekend_When_WeekendDateIsIndependenceDay() {
    LocalDate saturdayIndependenceDay = LocalDate.of(2020, 7, 4);
    LocalDate sundayIndependenceDay = LocalDate.of(2021, 7, 4);
    assertAll(
        () -> assertEquals(WEEKEND, getDayType(saturdayIndependenceDay)),
        () -> assertEquals(WEEKEND, getDayType(sundayIndependenceDay)));
  }

  @Test
  public void Should_ReturnHoliday_When_WeekdayDateIsIndependenceDayObserved() {
    LocalDate fridayIndependenceDayObserved = LocalDate.of(2020, 7, 3);
    LocalDate mondayIndependenceDayObserved = LocalDate.of(2021, 7, 5);
    assertAll(
            () -> assertEquals(HOLIDAY, getDayType(fridayIndependenceDayObserved)),
            () -> assertEquals(HOLIDAY, getDayType(mondayIndependenceDayObserved)));
  }

  @Test
  public void Should_ReturnHoliday_When_DateIsLaborDay() {
    LocalDate date = LocalDate.of(2023, 9, 4);
    assertTrue(HOLIDAY == getDayType(date));
  }
}
