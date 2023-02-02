package Recipe;

import Pantry.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    //This class will be what we use to keep track of all the recipes
    //We will be saving it out using json and loading back into it the same way.

    String name;
    String instructions;
    String time;
    List<Ingredient> ingredients;

    public Recipe(String name, String instructions, String time, List<Ingredient> ingredients) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getTime() {
        return time;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getIngredientsNames() {
        List<String> ingredientNames = new ArrayList<String>();
        for (Ingredient ingredient : ingredients) {
            ingredientNames.add(ingredient.getFoodName() + ": " + ingredient.getQuantity());
        }
        return ingredientNames;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setInstructions(String instructions) {
        this.instructions=instructions;
    }

    public void setTime(String time) {
        this.time=time;
    }
}
