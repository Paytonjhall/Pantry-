package Recipe;

import Pantry.FoodItem;
import Pantry.Ingredient;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
    //This class will be what we use to keep track of all the recipes
    //We will be saving it out using json and loading back into it the same way.

    String name;
    String instructions;
    String time;
    List<FoodItem> ingredients;
    String image;
    List<String> tags;

    public Recipe(String name, String instructions, String time, List<FoodItem> ingredients) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.time = time;
    }

    public Recipe(String name, String instructions, String time, List<FoodItem> ingredients, String image) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.time = time;
        this.image = image;
    }

    public Recipe(String name, String instructions, String time, List<FoodItem> ingredients, String image, List<String> tags) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.time = time;
        this.image = image;
        this.tags = tags;
    }

    public Recipe(){

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

    public List<FoodItem> getIngredients() {
        return ingredients;
    }

    public List<String> getIngredientsNames() {
        List<String> ingredientNames = new ArrayList<String>();
        for (FoodItem ingredient : ingredients) {
            ingredientNames.add(ingredient.getName() + ": " + ingredient.getQuantity());
        }
        return ingredientNames;
    }

    public void setIngredients(List<FoodItem> ingredients) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTags() {
        if (tags == null) {
            tags = new ArrayList<String>();
        }
        return tags;
    }

    public void setTags(List<String> tags) { this.tags = tags; }

    public void addTag(String tag) {
        if (tags == null) {
            tags = new ArrayList<String>();
        }
        tags.add(tag);
    }
}
