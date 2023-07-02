package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formatter {

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return date.format(formatter);
    }

    public static String formatCurrency(double amount) {
        BigDecimal roundedValue = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
        return "$" + roundedValue;
    }

    public static String formatPercent(int percent) {
        return String.format("%d%%", percent);
    }
}
