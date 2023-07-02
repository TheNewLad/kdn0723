package util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class FormatterTest {
    @Test
    public void Should_ReturnFormattedDate_When_GivenLocalDate() {
        LocalDate date = LocalDate.of(2023, 7, 3);
        assertEquals("07/03/23", Formatter.formatDate(date));
    }
}
