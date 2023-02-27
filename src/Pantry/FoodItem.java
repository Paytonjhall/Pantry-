package Pantry;

import Utils.Converter.IBaseUnit;

public class FoodItem {
  //Basic FoodItem Class

  private String name;

  private int numUnits = 0;
  private double unitSize = 0;

  private int minUnits = 0;

  private String unitType;

  public FoodItem() {

  }

  public FoodItem(String name, int numUnits) {
    this(name, numUnits, 1, "Unknown");
  }

  public FoodItem(String name, double unitSize, String unitType) {
    this(name, 1, unitSize, unitType);
  }

  public FoodItem(String name, int numUnits, double unitSize, String unitType) {
    this.name = cleanupName(name);
    this.unitSize = unitSize;
    this.unitType = unitType;
    this.numUnits = numUnits;
  }

  public String cleanupName(String name) {
    String cleanUp = name.strip();
    String fullyClean = cleanUp.substring(0, 1).toUpperCase() + cleanUp.substring(1);
    return fullyClean;
  }

  public void setName(String name) {
    this.name = cleanupName(name);
  }

  public void setUnitSize(double unitSize) {
    this.unitSize = unitSize;
  }

  public int getNumUnits() {
    return numUnits;
  }

  public String getUnitType() {
    return unitType;
  }

  public double getQuantity() {
    return unitSize * numUnits;
  }

  public int getMinUnits() {
    return minUnits;
  }

  public void setMinUnits(int minUnits) {
    this.minUnits = minUnits;
  }

  /**
   * Get the total quantity of the food item in the standardized unit
   * For volume, the quantity returned is in fluid ounces.
   */
  public double getBaseUnitQuantity() {
    return numUnits * IBaseUnit.convertUnits(IBaseUnit.stringToUnit(unitType), unitSize);
  }

  public String getAbbreviation() {
    return IBaseUnit.stringToUnit(unitType).getUnitString();
  }

  /**
   * Update the quantity of the food item
   * @param newQuantity the additional quantity to add
   */
  public void addQuantity(int newQuantity) {
      numUnits += newQuantity;
  }

  /**
   * Increment the quantity of the food item
   * by 1
   */
  public void addQuantity() {
    numUnits += 1;
  }

  /**
   * Remove a certain quantity from the food item
   * @param newQuantity - the quantity to remove
   */
  public void removeQuantity(int newQuantity) {
      numUnits -= newQuantity;
      if (numUnits < 0) {
        numUnits = 0;
      }
  }

  /**
   * Decrement the item quantity by 1
   */
  public void removeQuantity() {
    numUnits -= 1;
      if (numUnits < 0) {
        numUnits = 0;
      }
  }

  public double getUnitSize() {
    if(unitSize < 0) {
      unitSize = 0;
    }
    return unitSize;
  }

  public String getName() {
    return name;
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
    if ((item.name.equals(this.name)) && (item.unitSize == this.unitSize) && (item.unitType.equals(this.unitType))) {
      return true;
    }

    return false;
  }
}
