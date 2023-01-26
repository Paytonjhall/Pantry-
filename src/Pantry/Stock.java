package Pantry;

import java.util.List;

public class Stock {
  //This class will be what we use to keep track of all the items in the pantry
  //We will be saving it out using json and loading back into it the same way.

  //TODO: Functions to implement
    // add to stock
    // remove from stock
    // edit from stock
    // search from stock

  List<FoodItem> foodList;

    public Stock() {

    }
    public Stock(List<FoodItem> foodList) {
        this.foodList = foodList;
    }
}
