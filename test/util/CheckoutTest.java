package util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckoutTest {

    private static final PrintStream systemOut = System.out;
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final PrintStream testOut = new PrintStream(outputStream);

    @BeforeAll
    public static void setUp() {
        System.setOut(testOut);
    }

    @AfterAll
    public static void tearDown() {
        System.setOut(systemOut);
    }

    @Test
    public void Should_ThrowException_When_RentalDayCountIsLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            Checkout checkout = new Checkout("LADW", 0, 1, LocalDate.of(2023, 7, 3));
        });
    }

    @Test
    public void Should_NotThrowException_When_RentalDayCountIsOneOrGreater() {
        Checkout checkout1 = new Checkout("LADW", 1, 1, LocalDate.of(2023, 7, 3));
        Checkout checkout2 = new Checkout("LADW", 1000, 1, LocalDate.of(2023, 7, 3));
    }

    @Test
    public void Should_ThrowException_When_DiscountPercentageIsOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> {
            Checkout checkout1 = new Checkout("LADW", 1, -1, LocalDate.of(2023, 7, 3));
            Checkout checkout2 = new Checkout("LADW", 1, 101, LocalDate.of(2023, 7, 3));
        });
    }

    @Test
    public void Should_NotThrowException_When_DiscountPercentageIsInBounds() {
        Checkout checkout1 = new Checkout("LADW", 1, 0, LocalDate.of(2023, 7, 3));
        Checkout checkout2 = new Checkout("LADW", 1, 100, LocalDate.of(2023, 7, 3));
    }

    @Test
    void RentalAgreement_Should_ContainToolCode_OnCheckout() {
        Checkout checkout = new Checkout("LADW", 1, 0, LocalDate.of(2023, 7, 3));
        checkout.generateRentalAgreement();

        String consoleOutput = outputStream.toString();
        String expectedOutput = "Tool code: LADW";

        assertTrue(consoleOutput.contains(expectedOutput));
    }
}