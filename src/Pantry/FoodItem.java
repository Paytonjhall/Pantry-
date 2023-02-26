package Pantry;

import Utils.Converter.IBaseUnit;

public class FoodItem implements Comparable<FoodItem> {
  //Basic FoodItem Class

  private String name;

  private double quantity = 0;

  private String unitType;

  public FoodItem() {

  }

  public FoodItem(String name, int numUnits) {
    this(name, numUnits, 1, "Unknown");
  }

  public FoodItem(String name, double quantity, String unitType) {
    this(name, 1, quantity, unitType);
  }

  public FoodItem(String name, double quantity, double unitSize, String unitType) {
    this(name, 1, quantity * unitSize, unitType);
  }

  public FoodItem(String name, int numUnits, double unitSize, String unitType) {
    this.name = cleanupName(name);
    this.unitType = unitType;
    this.quantity = numUnits * unitSize;
  }

  public double getQuantity() {
    return quantity;
  }

  public void addQuantity(double quantity) {
    this.quantity += quantity;
  }


  public String cleanupName(String name) {
    String cleanUp = name.strip();
    String fullyClean = cleanUp.substring(0, 1).toUpperCase() + cleanUp.substring(1);
    return fullyClean;
  }

  public void setName(String name) {
    this.name = cleanupName(name);
  }

  public String getUnitType() {
    return unitType;
  }


  /**
   * Get the total quantity of the food item in the standardized unit
   * For volume, the quantity returned is in fluid ounces.
   */
  public double getBaseUnitQuantity() {
    return IBaseUnit.convertUnits(IBaseUnit.stringToUnit(unitType), quantity);
  }

  public String getAbbreviation() {
    return IBaseUnit.stringToUnit(unitType).getUnitString();
  }

  /**
   * Remove a certain quantity from the food item
   * @param newQuantity - the quantity to remove
   */
  public void removeQuantity(int newQuantity) {
      this.quantity -= newQuantity;
      if (this.quantity < 0) {
        this.quantity = 0;
      }
  }


  public String getName() {
    return name;
  }

  public void setUnitType(String unitType) {
    this.unitType = unitType;
  }


  @Override
  public boolean equals(Object o) {
    // Check if object is compared to itself
    if (o == this) {
      return true;
    }

    // Check if o is even a fooditem
    if (!(o instanceof FoodItem)) {
      return false;
    }

    FoodItem item = (FoodItem) o;

    // Check if the members are the same
    if ((item.name.equals(this.name)) && (item.quantity == this.quantity) && (item.unitType.equals(this.unitType))) {
      return true;
    }

    return false;
  }

  @Override
  public int compareTo(FoodItem o) {
    return this.name.compareTo(o.getName());
  }
}
