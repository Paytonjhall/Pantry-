package Pantry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingList {

    private List<Ingredient> items;

    public ShoppingList() {}

    public Ingredient getItem(int index) {
        return items.get(index);
    }

    public Ingredient getItem(String name) {
        for (Ingredient item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public ShoppingList(List<Ingredient> items) {
        this.items = items;
    }

    public List<Ingredient> getItems() {
        return items;
    }

    public void addItem(Ingredient item) {
        items.add(item);
    }

    public void addItems(List<Ingredient> item) {
        items.addAll(item);
    }

    public void removeItem(Ingredient item) {
        items.remove(item);
    }

    public void clearList() {
        items.clear();
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (Ingredient item : items) {
            names.add(item.getName());
        }
        return names;
    }

    public List<String> getNamesWithQuantity() {
        List<String> names = new ArrayList<>();
        for (Ingredient item : items) {
            names.add(item.getName() + " (" + item.getQuantity() + ")");
        }
        return names;
    }

    public void setItems(List<Ingredient> items) {
        this.items = items;
    }

    public void setList(List<Ingredient> items) {
        this.items = items;
    }

    public void editItem(Ingredient item, Ingredient newItem) {
        int index = items.indexOf(item);
        items.set(index, newItem);
    }

    public void alphabetizeList() {
        Collections.sort(items);
    }


}
