package util;

import static util.Formatter.formatDate;

import datasource.ToolStore;
import java.time.LocalDate;
import model.Tool;

public class Checkout {

  private final Tool tool;
  private final int rentalDayCount;
  private final int discountPercent;
  private final LocalDate checkoutDate;

  public Checkout(
      String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
    validateRentalDayCount(rentalDayCount);
    validateDiscountPercent(discountPercent);

    this.tool =
        ToolStore.getInstance()
            .findTool(
                toolCode); // I assume that the tool code is valid and exists in the database at
                           // this point.
    this.rentalDayCount = rentalDayCount;
    this.discountPercent = discountPercent;
    this.checkoutDate = checkoutDate;
  }

  public Tool getTool() {
    return tool;
  }

  public int getRentalDayCount() {
    return rentalDayCount;
  }

  public int getDiscountPercent() {
    return discountPercent;
  }

  public LocalDate getCheckoutDate() {
    return checkoutDate;
  }

    public LocalDate getDueDate() {
        return checkoutDate.plusDays(rentalDayCount);
    }

  public void generateRentalAgreement() {
    RentalAgreement rentalAgreement =
        new RentalAgreement(
            getTool().getToolCode(),
            getTool().getToolType().getType(),
            getTool().getBrand(),
            String.valueOf(getRentalDayCount()),
            formatDate(checkoutDate),
            formatDate(getDueDate()),
            "",
            "",
            "",
            "",
            "",
            "");
    rentalAgreement.print();
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
