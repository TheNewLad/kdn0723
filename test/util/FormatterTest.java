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

  @Test
  public void Should_ReturnFormattedCurrency_When_GivenDouble() {
    assertAll(
        () -> assertEquals("$1.00", Formatter.formatCurrency(1.0)),
        () -> assertEquals("$1.00", Formatter.formatCurrency(1.004)),
        () -> assertEquals("$1.01", Formatter.formatCurrency(1.005)),
        () -> assertEquals("$1.01", Formatter.formatCurrency(1.006)),
        () -> assertEquals("$1.01", Formatter.formatCurrency(1.009)),
        () -> assertEquals("$1.10", Formatter.formatCurrency(1.1)),
        () -> assertEquals("$1.11", Formatter.formatCurrency(1.105)),
        () -> assertEquals("$1.11", Formatter.formatCurrency(1.106)),
        () -> assertEquals("$1.11", Formatter.formatCurrency(1.109)),
        () -> assertEquals("$1.99", Formatter.formatCurrency(1.99)),
        () -> assertEquals("$1.99", Formatter.formatCurrency(1.994)),
        () -> assertEquals("$2.00", Formatter.formatCurrency(1.995)),
        () -> assertEquals("$2.00", Formatter.formatCurrency(1.996)),
        () -> assertEquals("$2.00", Formatter.formatCurrency(1.999)));
  }

  @Test
  public void Should_ReturnFormattedPercent_When_GivenInt() {
    assertAll(
        () -> assertEquals("0%", Formatter.formatPercent(0)),
        () -> assertEquals("100%", Formatter.formatPercent(100)));
  }
}
