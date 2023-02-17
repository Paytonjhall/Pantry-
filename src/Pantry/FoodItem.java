package Pantry;

import Utils.Converter.IBaseUnit;
import Utils.Converter.VolumeUnit;

public class FoodItem {
  //Basic FoodItem Class

  private String name;
  private double quantity = 0;

  private String quantityUnit;

  public FoodItem() {

  }

  public FoodItem(String name, double quantity) {
    this.name = cleanupName(name);
    this.quantity = quantity;
    quantityUnit = "Unknown";
  }

  public FoodItem(String name, double quantity, String quantityUnit) {
    this.name = cleanupName(name);
    this.quantity = quantity;
    this.quantityUnit = quantityUnit;
  }

  public String cleanupName(String name) {
    String cleanUp = name.strip();
    String fullyClean = cleanUp.substring(0, 1).toUpperCase() + cleanUp.substring(1);
    return fullyClean;
  }

  public void setName(String name) {
    this.name = cleanupName(name);
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  /**
   * Get the total quantity of the food item in the standardized unit
   * For volume, the quantity returned is in fluid ounces.
   */
  public double getBaseUnitQuantity() {
    return IBaseUnit.convertUnits(IBaseUnit.stringToUnit(quantityUnit), quantity);
  }

  public String getAbbreviation() {
    return IBaseUnit.stringToUnit(quantityUnit).getUnitString();
  }

  /**
   * Update the quantity of the food item
   * @param newQuantity the additional quantity to add
   */
  public void addQuantity(int newQuantity) {
      quantity += newQuantity;
  }

  /**
   * Increment the quantity of the food item
   * by 1
   */
  public void addQuantity() {
    quantity += 1;
  }

  /**
   * Remove a certain quantity from the food item
   * @param newQuantity - the quantity to remove
   */
  public void removeQuantity(int newQuantity) {
      quantity -= newQuantity;
      if (quantity < 0) {
        quantity = 0;
      }
  }

  /**
   * Decrement the item quantity by 1
   */
  public void removeQuantity() {
      quantity -= 1;
      if (quantity < 0) {
        quantity = 0;
      }
  }

  public double getQuantity() {
    if(quantity < 0) {
      quantity = 0;
    }
    return quantity;
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
    if ((item.name.equals(this.name)) && (item.quantity == this.quantity) && (item.quantityUnit.equals(this.quantityUnit))) {
      return true;
    }

    return false;
  }
}
