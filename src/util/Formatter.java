package util;

import java.time.LocalDate;

public class Formatter {

    public static String formatDate(LocalDate date) {
        return String.format("%02d/%02d/%02d", date.getMonthValue(), date.getDayOfMonth(), date.getYear() % 100);
    }
}
