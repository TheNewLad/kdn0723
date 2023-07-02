package util;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DayChecker {

  public static DayType getDayType(LocalDate date) {
    if (isHoliday(date)) {
      return DayType.HOLIDAY;
    }
    return isWeekday(date) ? DayType.WEEKDAY : DayType.WEEKEND;
  }

  private static boolean isWeekend(LocalDate date) {
    return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
  }

  private static boolean isWeekday(LocalDate date) {
    return !isWeekend(date);
  }

  private static boolean isHoliday(LocalDate date) {
    LocalDate laborDay = getLaborDay(date.getYear());
    LocalDate independenceDay = getObservableHoliday(LocalDate.of(date.getYear(), 7, 4));

    List<LocalDate> holidays = Arrays.asList(laborDay, independenceDay);

    return holidays.contains(date);
  }

  private static LocalDate getObservableHoliday(LocalDate date) {
    if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
      return date.minusDays(1);
    } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
      return date.plusDays(1);
    }

    return date;
  }

  private static LocalDate getLaborDay(int year) {
    return LocalDate.of(year, 9, 1).with(firstInMonth(DayOfWeek.MONDAY));
  }

  public enum DayType {
    WEEKDAY,
    WEEKEND,
    HOLIDAY
  }
}
