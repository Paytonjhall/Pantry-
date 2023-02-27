package Pantry;

import java.util.List;

public class ShoppingList {

    private List<FoodItem> items;

    public ShoppingList() {}

    public ShoppingList(List<FoodItem> items) {
        this.items = items;
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public void addItems(FoodItem item) {
        items.add(item);
    }

}
