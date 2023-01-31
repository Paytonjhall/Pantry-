package Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeBook {

  ArrayList<Recipe> recipeList;

  //TODO: Functions to implement
  // add to recipe list
  // remove from recipe list
  // edit from recipe list
  // search from recipe list


  public ArrayList<Recipe> getRecipeList() {
    return recipeList;
  }

  public void setRecipeList(ArrayList<Recipe> recipeList) {
    this.recipeList=recipeList;
  }

  public void addRecipe(Recipe recipe) {
    if(recipeList!=null)recipeList.add(recipe);
    else {
      recipeList = new ArrayList<Recipe>();
      recipeList.add(recipe);
    }
  }

  public List<String> getRecipeStringList(){
    List<String> recipeNames = new ArrayList<String>();
    for(Recipe recipe: recipeList){
      recipeNames.add(recipe.getName());
    }
    return recipeNames;
  }
}
