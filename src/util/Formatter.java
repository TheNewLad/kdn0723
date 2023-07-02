package util;

import java.time.LocalDate;

public class Formatter {

    public static String formatDate(LocalDate date) {
        return String.format("%02d/%02d/%02d", date.getMonthValue(), date.getDayOfMonth(), date.getYear() % 100);
    }

    public static String formatCurrency(double amount) {
        return String.format("$%.2f", Math.round(amount * 100.0) / 100.0);
    }

    public static String formatPercent(int percent) {
        return String.format("%d%%", percent);
    }
}
