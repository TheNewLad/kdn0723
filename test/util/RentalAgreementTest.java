package util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalAgreementTest {

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
    public void Should_PrintRentalAgreement_When_PrintMethodCalled() {
        RentalAgreement rentalAgreement = new RentalAgreement("Tool Code", "Tool Type", "Tool Brand", 7, "7/4/23", "7/11/23", 2.99, 5, 14.95, 10, 1.50, 13.45);
        rentalAgreement.print();

        String consoleOutput = outputStream.toString();
        String expectedOutput = "Tool code: Tool Code\n" +
                "Tool type: Tool Type\n" +
                "Tool brand: Tool Brand\n" +
                "Rental days: 7\n" +
                "Check out date: 7/4/23\n" +
                "Due date: 7/11/23\n" +
                "Daily rental charge: 2.99\n" +
                "Charge days: 5\n" +
                "Pre-discount charge: 14.95\n" +
                "Discount percent: 10.0\n" +
                "Discount amount: 1.5\n" +
                "Final charge: 13.45\n";

        assertEquals(expectedOutput, consoleOutput);
    }
}