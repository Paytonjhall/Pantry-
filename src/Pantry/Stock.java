package Pantry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stock {
  //This class will be what we use to keep track of all the items in the pantry
  //We will be saving it out using json and loading back into it the same way.

    private List<Ingredient> foodList;

    public Stock() {

    }
    public Stock(List<Ingredient> foodList) {
        this.foodList = foodList;
    }

  /**
   * Adds a food item to the stock
   * @param newItem - food item to add
   */
  public void addItem(Ingredient newItem) {
      if (foodList == null) {
          foodList = new ArrayList<Ingredient>();
      }

      int existingIndex = itemAlreadyInStock(newItem);
      if (existingIndex == -1) {
          foodList.add(newItem);
      } else {
          foodList.get(existingIndex).addQuantity(newItem.getNumUnits());
      }

  }

  /**
   * Removes a food item from the stock or does nothing if the item
   * is not part of the stock
   * @param item - food item to remove
   */

  public void removeItem(Ingredient item) {
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
  public boolean inStock(Ingredient item) {
      int index = findItem(item);
      if (index >= 0) {
          return true;
      }
      return false;
  }

    /**
     * Get the list of every item currently in stock
     */
  public List<Ingredient> getStock() {
      return foodList;
  }

  /**
   * Finds the index of the item if it exists in the stock
   * @param item - food item to find
   * @return index in the list if it exists, -1 otherwise
   */
  public int findItem(Ingredient item) {
    for (int i = 0; i < foodList.size(); i++) {
        if (Objects.equals(foodList.get(i).getName().toLowerCase(), item.getName().toLowerCase())) {
            return i;
        }
    }
    return -1;
  }

  public List<String> getFoodNames() {
    List<String> foodNames = new ArrayList<String>();
    if(foodList == null) foodList = new ArrayList<Ingredient>();
    for (Ingredient item : foodList) {
      foodNames.add(item.getName());
    }
    return foodNames;
  }

  public List<String> getFoodNamesWithQuantity() {
    List<String> foodNames = new ArrayList<String>();
    if(foodList == null) foodList = new ArrayList<Ingredient>();
    for (Ingredient item : foodList) {
        if (Objects.equals(item.getUnitType(), "") || Objects.equals(item.getUnitType(), "unknown")) {
            foodNames.add(item.getName() + " (" + item.getNumUnits() + ")");
        } else {
            foodNames.add(item.getName() + " (" + item.getNumUnits() + ")" + " - " + item.getUnitSize() + " " + item.getAbbreviation());
        }
    }
    return foodNames;
  }

  public void editFoodItem(Ingredient item, Ingredient newItem) {
      int index = findItem(item);
      if (index >= 0) {
          foodList.set(index, newItem);
      }
  }

  public Ingredient getFoodItem(String name) {
      for (Ingredient item : foodList) {
          if (item.getName().equalsIgnoreCase(name)) {
              return item;
          }
      }
      return null;
  }

  private int itemAlreadyInStock(Ingredient newItem) {
      for (int i = 0; i < foodList.size(); i++) {
          if (foodList.get(i).getName().equalsIgnoreCase(newItem.getName()) &&
                  foodList.get(i).getUnitType().equalsIgnoreCase(newItem.getUnitType()) &&
                  foodList.get(i).getUnitSize() == newItem.getUnitSize()) {
              return i;
          }
      }
      return -1;
  }
}
