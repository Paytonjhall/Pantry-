package Recipe;

import Pantry.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Comparable<Recipe> {
    //This class will be what we use to keep track of all the recipes
    //We will be saving it out using json and loading back into it the same way.

    String name;
    String instructions;
    String time;
    List<Ingredient> ingredients;
    String image;
    Boolean breakfastTag;
    Boolean lunchTag;
    Boolean dinnerTag;
    Boolean mainCourseTag;
    Boolean sideDishTag;
    Boolean snackTag;
    Boolean dessertTag;
    Boolean vegetarianTag;
    Boolean glutenFreeTag;
    Boolean meatTag;

    public Recipe(String name, String instructions, String time, List<Ingredient> ingredients) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.time = time;
        this.breakfastTag = false;
        this.lunchTag = false;
        this.dinnerTag = false;
        this.mainCourseTag = false;
        this.sideDishTag = false;
        this.snackTag = false;
        this.dessertTag = false;
        this.vegetarianTag = false;
        this.glutenFreeTag = false;
        this.meatTag = false;
    }

    public Recipe(String name, String instructions, String time, List<Ingredient> ingredients, String image) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.time = time;
        this.image = image;
        this.breakfastTag = false;
        this.lunchTag = false;
        this.dinnerTag = false;
        this.mainCourseTag = false;
        this.sideDishTag = false;
        this.snackTag = false;
        this.dessertTag = false;
        this.vegetarianTag = false;
        this.glutenFreeTag = false;
        this.meatTag = false;
    }

    public Recipe(String name, String instructions, String time, List<Ingredient> ingredients, String image, Boolean breakfastTag, Boolean lunchTag, Boolean dinnerTag, Boolean mainCourseTag, Boolean sideDishTag, Boolean snackTag, Boolean dessertTag, Boolean vegetarianTag, Boolean glutenFreeTag, Boolean meatTag) {
        this.name = name;
        this.instructions = instructions;
        this.time = time;
        this.ingredients = ingredients;
        this.image = image;
        this.breakfastTag = breakfastTag;
        this.lunchTag = lunchTag;
        this.dinnerTag = dinnerTag;
        this.mainCourseTag = mainCourseTag;
        this.sideDishTag = sideDishTag;
        this.snackTag = snackTag;
        this.dessertTag = dessertTag;
        this.vegetarianTag = vegetarianTag;
        this.glutenFreeTag = glutenFreeTag;
        this.meatTag = meatTag;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getIngredientsNames() {
        List<String> ingredientNames = new ArrayList<String>();
        for (Ingredient ingredient : ingredients) {
            ingredientNames.add(ingredient.getName() + ": " + ingredient.getQuantity());
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Boolean getBreakfastTag() {
        return breakfastTag;
    }

    public void setBreakfastTag(Boolean breakfastTag) {
        this.breakfastTag = breakfastTag;
    }

    public Boolean getLunchTag() {
        return lunchTag;
    }

    public void setLunchTag(Boolean lunchTag) {
        this.lunchTag = lunchTag;
    }

    public Boolean getDinnerTag() {
        return dinnerTag;
    }

    public void setDinnerTag(Boolean dinnerTag) {
        this.dinnerTag = dinnerTag;
    }

    public Boolean getMainCourseTag() {
        return mainCourseTag;
    }

    public void setMainCourseTag(Boolean mainCourseTag) {
        this.mainCourseTag = mainCourseTag;
    }

    public Boolean getSideDishTag() {
        return sideDishTag;
    }

    public void setSideDishTag(Boolean sideDishTag) {
        this.sideDishTag = sideDishTag;
    }

    public Boolean getSnackTag() {
        return snackTag;
    }

    public void setSnackTag(Boolean snackTag) {
        this.snackTag = snackTag;
    }

    public Boolean getDessertTag() {
        return dessertTag;
    }

    public void setDessertTag(Boolean dessertTag) {
        this.dessertTag = dessertTag;
    }

    public Boolean getVegetarianTag() {
        return vegetarianTag;
    }

    public void setVegetarianTag(Boolean vegetarianTag) {
        this.vegetarianTag = vegetarianTag;
    }

    public Boolean getGlutenFreeTag() {
        return glutenFreeTag;
    }

    public void setGlutenFreeTag(Boolean glutenFreeTag) {
        this.glutenFreeTag = glutenFreeTag;
    }

    public Boolean getMeatTag() {
        return meatTag;
    }

    public void setMeatTag(Boolean meatTag) {
        this.meatTag = meatTag;
    }

    @Override
    public int compareTo(Recipe o) {
        return this.name.compareTo(o.name);
    }
}
