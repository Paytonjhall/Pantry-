package Pantry;

import java.util.ArrayList;
import java.util.List;

public class Stock {
  //This class will be what we use to keep track of all the items in the pantry
  //We will be saving it out using json and loading back into it the same way.

    private List<FoodItem> foodList;

    public Stock() {

    }
    public Stock(List<FoodItem> foodList) {
        this.foodList = foodList;
    }

  /**
   * Adds a food item to the stock
   * @param newItem - food item to add
   */
  public void addItem(FoodItem newItem) {
    if(foodList == null) {
            foodList = new ArrayList<FoodItem>();
        }
      foodList.add(newItem);
  }

  /**
   * Removes a food item from the stock or does nothing if the item
   * is not part of the stock
   * @param item - food item to remove
   */

  public void removeItem(FoodItem item) {
      int index = findItem(item);
      if (index >= 0) {
        foodList.remove(index);
      }
  }

  /**
   * Determines whether a given food item is in stock or not
   * @param item - item to find
   * @return true if item is currently in stock, false if not
   */
  public boolean inStock(FoodItem item) {
      int index = findItem(item);
      if (index >= 0) {
          return true;
      }
      return false;
  }

    /**
     * Get the list of every item currently in stock
     */
  public List<FoodItem> getStock() {
      return foodList;
  }

  /**
   * Finds the index of the item if it exists in the stock
   * @param item - food item to find
   * @return index in the list if it exists, -1 otherwise
   */
  private int findItem(FoodItem item) {
    for (int i = 0; i < foodList.size(); i++) {
        if (foodList.get(i) == item) {
            return i;
        }
    }
    return -1;
  }

  public List<String> getFoodNames() {
    List<String> foodNames = new ArrayList<String>();
    if(foodList == null) foodList = new ArrayList<FoodItem>();
    for (FoodItem item : foodList) {
      foodNames.add(item.getName());
    }
    return foodNames;
  }

  public List<String> getFoodNamesWithQuantity() {
    List<String> foodNames = new ArrayList<String>();
    if(foodList == null) foodList = new ArrayList<FoodItem>();
    for (FoodItem item : foodList) {
      foodNames.add(item.getName() + " (" + item.getQuantity() + ")");
    }
    return foodNames;
  }
}
