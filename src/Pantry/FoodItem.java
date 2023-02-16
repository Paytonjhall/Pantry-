package Pantry;

public class FoodItem {
  //Basic FoodItem Class

  private String name;
  private int quantity = 0;

  public FoodItem() {

  }

  public FoodItem(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
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

  public int getQuantity() {
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
    if ((item.name.equals(this.name)) && (item.quantity == this.quantity)) {
      return true;
    }

    return false;
  }
}
