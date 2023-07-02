package util;

import org.junit.jupiter.api.Test;

class RentalAgreementTest {

    @Test
    public void Should_PrintRentalAgreement_When_PrintMethodCalled() {
        RentalAgreement rentalAgreement = new RentalAgreement("Tool Code", "Tool Type", "Tool Brand", 7, "7/4/23", "7/11/23", 2.99, 5, 14.95, 10, 1.50, 13.45);
    }
}