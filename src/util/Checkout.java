package util;


import java.time.LocalDate;

public class Checkout {

    private final String toolCode;
    private final int rentalDayCount;
    private final int discountPercent;
    private final LocalDate checkoutDate;

    public Checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        validateRentalDayCount(rentalDayCount);
        validateDiscountPercent(discountPercent);

        this.toolCode = toolCode;
        this.rentalDayCount = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }

    private void validateRentalDayCount(int rentalDayCount) {
        if (rentalDayCount < 1) {
            throw new IllegalArgumentException("Rental day count is not 1 or greater.");
        }
    }

    private void validateDiscountPercent(int discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent is not in the range 0-100.");
        }
    }
}
