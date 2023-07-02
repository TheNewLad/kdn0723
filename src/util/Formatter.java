package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Formatter {

    public static String formatDate(LocalDate date) {
        return String.format("%02d/%02d/%02d", date.getMonthValue(), date.getDayOfMonth(), date.getYear() % 100);
    }

    public static String formatCurrency(double amount) {
        BigDecimal roundedValue = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
        return "$" + roundedValue;
    }

    public static String formatPercent(int percent) {
        return String.format("%d%%", percent);
    }
}
