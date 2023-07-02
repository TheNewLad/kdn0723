package model;

import static util.DayChecker.DayType;

import java.util.HashSet;
import java.util.Set;

public class ToolType {
  private final String type;
  private double dailyCharge;
  private Set<DayType> chargeDays;

  public ToolType(String type, double dailyCharge, Set<DayType> chargeDays) {
    this.type = type;
    this.dailyCharge = dailyCharge;
    this.chargeDays = chargeDays;
  }

  public ToolType(String type, double dailyCharge) {
    this(type, dailyCharge, new HashSet<>());
  }

  public String getType() {
    return type;
  }

  public double getDailyCharge() {
    return dailyCharge;
  }

  public void setDailyCharge(double dailyCharge) {
    this.dailyCharge = dailyCharge;
  }

  public Set<DayType> getChargeDays() {
    return chargeDays;
  }

  public void setChargeDays(Set<DayType> chargeDays) {
    this.chargeDays = chargeDays;
  }

  public void addChargeDay(DayType dayType) {
    chargeDays.add(dayType);
  }

  public void removeChargeDay(DayType dayType) {
    chargeDays.remove(dayType);
  }
}
