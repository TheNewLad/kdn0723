package util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    assertThrows(
        IllegalArgumentException.class,
        () -> {
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
    assertThrows(
        IllegalArgumentException.class,
        () -> {
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
  public void RentalAgreement_Should_ContainToolCode_OnCheckout() {
    Checkout checkout = new Checkout("LADW", 1, 0, LocalDate.of(2023, 7, 3));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Tool code: LADW";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void RentalAgreement_Should_ContainToolType_OnCheckout() {
    Checkout checkout = new Checkout("LADW", 1, 0, LocalDate.of(2023, 7, 3));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Tool type: Ladder";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void RentalAgreement_Should_ContainToolBrand_OnCheckout() {
    Checkout checkout = new Checkout("LADW", 1, 0, LocalDate.of(2023, 7, 3));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Tool brand: Werner";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void RentalAgreement_Should_ContainRentalDays_OnCheckout() {
    Checkout checkout = new Checkout("LADW", 1, 0, LocalDate.of(2023, 7, 3));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Rental days: 1";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void RentalAgreement_Should_ContainCheckoutDate_OnCheckout() {
    Checkout checkout = new Checkout("LADW", 1, 0, LocalDate.of(2023, 7, 3));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Check out date: 07/03/23";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void RentalAgreement_Should_HaveCalculatedDueDate_OnCheckout() {
    Checkout checkout = new Checkout("LADW", 1, 0, LocalDate.of(2023, 7, 3));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Due date: 07/04/23";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void RentalAgreement_Should_ContainDailyRentalCharge_OnCheckout() {
    Checkout checkout = new Checkout("LADW", 1, 0, LocalDate.of(2023, 7, 3));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Daily rental charge: $1.99";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void RentalAgreement_Should_ContainCorrectChargeDays_When_LadderIsRented() {
    Checkout checkout = new Checkout("LADW", 5, 0, LocalDate.of(2023, 6, 30));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Charge days: 4";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void RentalAgreement_Should_ContainCorrectChargeDays_When_ChainsawIsRented() {
    Checkout checkout = new Checkout("CHNS", 5, 0, LocalDate.of(2023, 6, 30));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Charge days: 3";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void RentalAgreement_Should_ContainCorrectChargeDays_When_DewaltJackhammerIsRented() {
    Checkout checkout = new Checkout("JAKD", 5, 0, LocalDate.of(2023, 6, 30));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Charge days: 2";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void RentalAgreement_Should_ContainCorrectChargeDays_When_RigidJackhammerIsRented() {
    Checkout checkout = new Checkout("JAKR", 5, 0, LocalDate.of(2023, 6, 30));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Charge days: 2";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

    @Test
    public void RentalAgreement_Should_ContainPreDiscountCharge_OnCheckout() {
        Checkout checkout = new Checkout("LADW", 5, 0, LocalDate.of(2023, 6, 30));
        checkout.generateRentalAgreement();

        String consoleOutput = outputStream.toString();
        String expectedOutput = "Pre-discount charge: $7.96";

        assertTrue(consoleOutput.contains(expectedOutput));
    }

    @Test
    public void RentalAgreement_Should_ContainDiscountPercentage_OnCheckout() {
        Checkout checkout = new Checkout("LADW", 5, 10, LocalDate.of(2023, 6, 30));
        checkout.generateRentalAgreement();

        String consoleOutput = outputStream.toString();
        String expectedOutput = "Discount percent: 10%";

        assertTrue(consoleOutput.contains(expectedOutput));
    }

    @Test
    public void RentalAgreement_Should_ContainDiscountAmount_OnCheckout() {
        Checkout checkout = new Checkout("LADW", 5, 10, LocalDate.of(2023, 6, 30));
        checkout.generateRentalAgreement();

        String consoleOutput = outputStream.toString();
        String expectedOutput = "Discount amount: $0.80";

        assertTrue(consoleOutput.contains(expectedOutput));
    }

    @Test
    public void RentalAgreement_Should_ContainFinalCharge_OnCheckout() {
    Checkout checkout = new Checkout("LADW", 5, 10, LocalDate.of(2023, 6, 30));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Final charge: $7.16";

    assertTrue(consoleOutput.contains(expectedOutput));
  }

  @Test
  public void Test1_Should_ThrowIllegalArgumentException_When_DiscountCodeIsOutOfRange() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Checkout checkout = new Checkout("JAKR", 5, 101, LocalDate.of(2015, 9, 3));
        });
  }

  @Test
  public void Test2_Should_PrintFinalChargeOf_3_58() {
    Checkout checkout = new Checkout("LADW", 3, 10, LocalDate.of(2020, 7, 2));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Tool code: LADW\n" +
            "Tool type: Ladder\n" +
            "Tool brand: Werner\n" +
            "Rental days: 3\n" +
            "Check out date: 07/02/20\n" +
            "Due date: 07/05/20\n" +
            "Daily rental charge: $1.99\n" +
            "Charge days: 2\n" +
            "Pre-discount charge: $3.98\n" +
            "Discount percent: 10%\n" +
            "Discount amount: $0.40\n" +
            "Final charge: $3.58\n";

    assertEquals(expectedOutput, consoleOutput);
  }

  @Test
  public void Test3_Should_PrintFinalChargeOf_3_35() {
    Checkout checkout = new Checkout("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Tool code: CHNS\n" +
            "Tool type: Chainsaw\n" +
            "Tool brand: Stihl\n" +
            "Rental days: 5\n" +
            "Check out date: 07/02/15\n" +
            "Due date: 07/07/15\n" +
            "Daily rental charge: $1.49\n" +
            "Charge days: 3\n" +
            "Pre-discount charge: $4.47\n" +
            "Discount percent: 25%\n" +
            "Discount amount: $1.12\n" +
            "Final charge: $3.35\n";

    assertEquals(expectedOutput, consoleOutput);
  }

  @Test
  public void Test4_Should_PrintFinalChargeOf_8_97() {
    Checkout checkout = new Checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Tool code: JAKD\n" +
            "Tool type: Jackhammer\n" +
            "Tool brand: DeWalt\n" +
            "Rental days: 6\n" +
            "Check out date: 09/03/15\n" +
            "Due date: 09/09/15\n" +
            "Daily rental charge: $2.99\n" +
            "Charge days: 3\n" +
            "Pre-discount charge: $8.97\n" +
            "Discount percent: 0%\n" +
            "Discount amount: $0.00\n" +
            "Final charge: $8.97\n";

    assertEquals(expectedOutput, consoleOutput);
  }

  @Test
  public void Test5_Should_PrintFinalChargeOf_14_95() {
    Checkout checkout = new Checkout("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Tool code: JAKR\n" +
            "Tool type: Jackhammer\n" +
            "Tool brand: Ridgid\n" +
            "Rental days: 9\n" +
            "Check out date: 07/02/15\n" +
            "Due date: 07/11/15\n" +
            "Daily rental charge: $2.99\n" +
            "Charge days: 5\n" +
            "Pre-discount charge: $14.95\n" +
            "Discount percent: 0%\n" +
            "Discount amount: $0.00\n" +
            "Final charge: $14.95\n";

    assertEquals(expectedOutput, consoleOutput);
  }

  @Test
  public void Test6_Should_PrintFinalChargeOf_1_50() {
    Checkout checkout = new Checkout("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
    checkout.generateRentalAgreement();

    String consoleOutput = outputStream.toString();
    String expectedOutput = "Tool code: JAKR\n" +
            "Tool type: Jackhammer\n" +
            "Tool brand: Ridgid\n" +
            "Rental days: 4\n" +
            "Check out date: 07/02/20\n" +
            "Due date: 07/06/20\n" +
            "Daily rental charge: $2.99\n" +
            "Charge days: 1\n" +
            "Pre-discount charge: $2.99\n" +
            "Discount percent: 50%\n" +
            "Discount amount: $1.50\n" +
            "Final charge: $1.50\n";

    assertEquals(expectedOutput, consoleOutput);
  }
}
