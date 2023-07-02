package util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckoutTest {

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
}