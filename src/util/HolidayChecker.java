package util;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class HolidayChecker {

    public static boolean isHoliday(LocalDate date) {
        return isIndependenceDay(date) || isLaborDay(date);
    }

    private static boolean isIndependenceDay(LocalDate date) {
        LocalDate independenceDay = getIndependenceDay(date.getYear());
        return date.equals(independenceDay);
    }

    private static LocalDate getIndependenceDay(int year) {
        if (LocalDate.of(year, 7, 4).getDayOfWeek() == DayOfWeek.SATURDAY) {
            return LocalDate.of(year, 7, 3);
        } else if (LocalDate.of(year, 7, 4).getDayOfWeek() == DayOfWeek.SUNDAY) {
            return LocalDate.of(year, 7, 5);
        }

        return LocalDate.of(year, 7, 4);
    }

    private static boolean isLaborDay(LocalDate date) {
        LocalDate laborDay = getLaborDay(date.getYear());
        return date.equals(laborDay);
    }

    private static LocalDate getLaborDay(int year) {
        return LocalDate.of(year, 9, 1).with(firstInMonth(DayOfWeek.MONDAY));
    }
}
