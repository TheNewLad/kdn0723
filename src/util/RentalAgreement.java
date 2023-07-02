package util;

public class RentalAgreement {
    private final String toolCode;
    private final String toolType;
    private final String toolBrand;
    private final String rentalDays;
    private final String checkoutDate;
    private final String dueDate;
    private final String dailyRentalCharge;
    private final String chargeDays;
    private final String preDiscountCharge;
    private final String discountPercent;
    private final String discountAmount;
    private final String finalCharge;

    public RentalAgreement(String toolCode, String toolType, String toolBrand, String rentalDays, String checkoutDate, String dueDate, String dailyRentalCharge, String chargeDays, String preDiscountCharge, String discountPercent, String discountAmount, String finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public void print() {
        System.out.println("Tool code: " + toolCode);
        System.out.println("Tool type: " + toolType);
        System.out.println("Tool brand: " + toolBrand);
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate);
        System.out.println("Due date: " + dueDate);
        System.out.println("Daily rental charge: " + dailyRentalCharge);
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + preDiscountCharge);
        System.out.println("Discount percent: " + discountPercent);
        System.out.println("Discount amount: " + discountAmount);
        System.out.println("Final charge: " + finalCharge);
    }
}
