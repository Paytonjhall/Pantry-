package Pantry;

import Utils.Converter.IBaseUnit;
import Utils.Converter.UnitConverter;
import Utils.Converter.VolumeUnit;
import Utils.Converter.WholeUnit;

import java.util.*;

public class Stock {
  //This class will be what we use to keep track of all the items in the pantry
  //We will be saving it out using json and loading back into it the same way.

    private List<Ingredient> foodList;
    private ShoppingList shoppingList;

    public Stock() {}

    public Stock(List<Ingredient> foodList) {
        this.foodList = foodList;
        alphabetizeList();
    }

    public Stock(List<Ingredient> foodList, ShoppingList shoppingList) {
        this.foodList = foodList;
        this.shoppingList = shoppingList;
        alphabetizeList();
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
          updateUnits(existingIndex, newItem);
      }

      alphabetizeList();

  }

    /**
     * When adding a new pantry item, if the item is already in stock,
     * it determines which has the larger unit and converts the other to
     * that unit and unit size and adds the quantity
     * @param indexOriginal index of the exising item in stock
     * @param second the new item being added
     */
  private void updateUnits(int indexOriginal, Ingredient second) {
      Ingredient first = foodList.get(indexOriginal);

      IBaseUnit firstUnit = UnitConverter.stringToUnit(first.getUnitType());
      IBaseUnit secondUnit = UnitConverter.stringToUnit(second.getUnitType());

      if ((firstUnit instanceof VolumeUnit) &&
              (secondUnit instanceof VolumeUnit)){
          VolumeUnit firstVolumeUnit = (VolumeUnit) firstUnit;
          VolumeUnit secondVolumeUnit = (VolumeUnit) secondUnit;

          if (VolumeUnit.isLarger(firstVolumeUnit, secondVolumeUnit)) {
              double secondQuantity = VolumeUnit.convertToDestinationUnit(secondVolumeUnit, firstVolumeUnit, second.getQuantity());
              foodList.get(indexOriginal).addQuantity(secondQuantity);

          } else {
              double firstQuantity = VolumeUnit.convertToDestinationUnit(firstVolumeUnit, secondVolumeUnit, first.getQuantity());
              second.addQuantity(firstQuantity);
              foodList.set(indexOriginal, second);
          }
      } else if ((firstUnit instanceof WholeUnit) && (secondUnit instanceof WholeUnit)) {
          foodList.get(indexOriginal).addQuantity(second.getQuantity());
      } else {
          foodList.add(second);
      }
      // TODO:: ADD THE OPTION FOR WEIGHT HERE
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

  public void genterateShoppingList(){
      ShoppingList list = new ShoppingList();
        for (Ingredient item: foodList) {
            if(item.getMinUnits() >= item.getQuantity()){
                list.addItem(item);
            }
        }
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
        if (Objects.equals(item.getUnitType(), "") || Objects.equals(item.getUnitType(), "unknown") ||
            Objects.equals(item.getUnitType(), "whole item")) {
            foodNames.add(item.getName() + " (" + item.getQuantity() + ")");
        } else {
            foodNames.add(item.getName() + " (" + item.getQuantity() + " " + item.getAbbreviation() + ")");
        }
    }
    return foodNames;
  }

  public void editFoodItem(Ingredient item, Ingredient newItem) {
      int index = findItem(item);
      if (index >= 0) {
          foodList.set(index, newItem);
      }
      alphabetizeList();
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
          if (foodList.get(i).getName().equalsIgnoreCase(newItem.getName())) {
              return i;
          }
      }
      return -1;
  }

  private void alphabetizeList() {
      Collections.sort(foodList);
  }

    public ShoppingList getShoppingList() {
      if (shoppingList == null) {
          shoppingList = new ShoppingList();
      }
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
      if(shoppingList == null) shoppingList = new ShoppingList();
      this.shoppingList = shoppingList;
    }

    public void addShoppingListItem(Ingredient item) {
        if(shoppingList == null) shoppingList = new ShoppingList();
        this.shoppingList.addItem(item);
    }

    public void removeShoppingListItem(Ingredient item) {

      if(shoppingList == null) shoppingList = new ShoppingList();
      shoppingList.removeItem(item);
    }

    public void clearShoppingList() {
        if(shoppingList == null) shoppingList = new ShoppingList();
        shoppingList.clearList();
    }

    public List<Ingredient> getShoppingListItems() {
        if(shoppingList == null) shoppingList = new ShoppingList();
        return shoppingList.getItems();
    }

    public List<String> getShoppingListNames() {
        if(shoppingList == null) shoppingList = new ShoppingList();
        return shoppingList.getNames();
    }

    public List<String> getShoppingListNamesWithQuantity() {
        if(shoppingList == null) shoppingList = new ShoppingList();
        return shoppingList.getNamesWithQuantity();
    }

    public void setShoppingListItems(List<Ingredient> items) {
        if(shoppingList == null) shoppingList = new ShoppingList();
        shoppingList.setItems(items);
    }

    public void addShoppingListItems(List<Ingredient> items) {
        if(shoppingList == null) shoppingList = new ShoppingList();
        shoppingList.addItems(items);
    }

    public void editShoppingListItem(Ingredient item, Ingredient newItem) {
        if(shoppingList == null) shoppingList = new ShoppingList();
        shoppingList.editItem(item, newItem);
    }

}
