package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import util.DayChecker.DayType;

class ToolTypeTest {

  @Test
  public void Should_AddChargeDay() {
    ToolType toolType = new ToolType("Ladder", 1.99);
    toolType.addChargeDay(DayType.WEEKDAY);
    assertTrue(toolType.getChargeDays().contains(DayType.WEEKDAY));
  }

  @Test
  public void Should_RemoveChargeDay() {
    ToolType toolType = new ToolType("Ladder", 1.99);
    toolType.addChargeDay(DayType.WEEKDAY);
    toolType.removeChargeDay(DayType.WEEKDAY);
    assertFalse(toolType.getChargeDays().contains(DayType.WEEKDAY));
  }

  @Test
  public void Should_ReturnTrue_When_DayTypeIsChargeDay() {
    ToolType toolType = new ToolType("Ladder", 1.99);
    toolType.addChargeDay(DayType.WEEKDAY);
    assertTrue(toolType.isChargeDay(DayType.WEEKDAY));
  }

  @Test
  public void Should_ReturnFalse_When_DayTypeIsNotChargeDay() {
    ToolType toolType = new ToolType("Ladder", 1.99);
    assertFalse(toolType.isChargeDay(DayType.WEEKDAY));
  }
}
