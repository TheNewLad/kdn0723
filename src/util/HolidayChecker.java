package util;

import java.time.LocalDate;

public class HolidayChecker {

    public static boolean isHoliday(LocalDate date) {
        return isIndependenceDay(date);
    }

    private static boolean isIndependenceDay(LocalDate date) {
        LocalDate independenceDay = LocalDate.of(date.getYear(), 7, 4);
        return date.equals(independenceDay);
    }
}
