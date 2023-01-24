package Pantry;

import java.util.List;

public class Stock {
  //This class will be what we use to keep track of all the items in the pantry
  //We will be saving it out using json and loading back into it the same way.

  List<Food> foodList;

    public Stock() {

    }

    public void addFood(Food food){
        foodList.add(food);
    }

    public void removeFood(Food food){
        foodList.remove(food);
    }

    public void removeFood(String name, int amount){
        for(Food food: foodList){
            if(food.name.equals(name)){
                food.quantity -= amount;
            }
        }
    }
}
