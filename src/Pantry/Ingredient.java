package Pantry;

public class Ingredient {
    private String foodName;
    private int quantity; //int or string?

    public Ingredient(String foodName, int quantity) {
        this.foodName=foodName;
        this.quantity=quantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName=foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }
}
