package util;

public class RentalAgreement {
    private final String toolCode;
    private final String toolType;
    private final String toolBrand;
    private final int rentalDays;
    private final String checkoutDate;
    private final String dueDate;
    private final double dailyRentalCharge;
    private final int chargeDays;
    private final double preDiscountCharge;
    private final double discountPercent;
    private final double discountAmount;
    private final double finalCharge;

    public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays, String checkoutDate, String dueDate, double dailyRentalCharge, int chargeDays, double preDiscountCharge, double discountPercent, double discountAmount, double finalCharge) {
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
}
