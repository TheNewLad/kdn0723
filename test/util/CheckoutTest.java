package util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
