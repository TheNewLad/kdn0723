package util;

import static util.DayChecker.*;
import static util.Formatter.*;

import datasource.ToolStore;
import java.time.LocalDate;
import model.Tool;
import model.ToolType;

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

  private int getChargeDays() {
    int chargeDays = 0;
    LocalDate date = checkoutDate.plusDays(1); // Add 1 to exclude the checkout date.
    LocalDate dueDate = getDueDate().plusDays(1); // Add 1 to include the due date.

    while (date.isBefore(dueDate)) {
      DayType dayType = getDayType(date);
      ToolType toolType = getTool().getToolType();

      if (shouldChargeForDayType(dayType, toolType)) {
        chargeDays++;
      }

      date = date.plusDays(1);
    }

    return chargeDays;
  }

  private boolean shouldChargeForDayType(DayType dayType, ToolType toolType) {
    switch (dayType) {
      case HOLIDAY:
        return toolType.isHolidayCharge();
      case WEEKEND:
        return toolType.isWeekendCharge();
      case WEEKDAY:
        return toolType.isWeekdayCharge();
      default:
        return false;
    }
  }

  private double getPreDiscountCharge() {
    ToolType toolType = getTool().getToolType();

    return getChargeDays() * toolType.getDailyCharge();
  }

  public void generateRentalAgreement() {
    Tool tool = getTool();
    ToolType toolType = tool.getToolType();

    RentalAgreement rentalAgreement =
        new RentalAgreement(
            tool.getToolCode(),
            toolType.getType(),
            tool.getBrand(),
            String.valueOf(getRentalDayCount()),
            formatDate(getCheckoutDate()),
            formatDate(getDueDate()),
            formatCurrency(toolType.getDailyCharge()),
            String.valueOf(getChargeDays()),
            formatCurrency(getPreDiscountCharge()),
            "",
            formatPercent(getDiscountPercent()),
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
