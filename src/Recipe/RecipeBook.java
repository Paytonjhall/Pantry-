package Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeBook {

  ArrayList<Recipe> recipeList;


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

    public void removeRecipe(Recipe recipe) {
        if(recipeList!=null)recipeList.remove(recipe);
    }

    public void editRecipe(Recipe oldRecipe, Recipe newRecipe) {
        if(recipeList!=null) {
            recipeList.remove(oldRecipe);
            recipeList.add(newRecipe);
        }
    }

  public List<String> getRecipeStringList(){
    List<String> recipeNames = new ArrayList<String>();
    for(Recipe recipe: recipeList){
      recipeNames.add(recipe.getName());
    }
    return recipeNames;
  }

  public List<String> searchRecipe(String search){
    List<String> recipeNames = new ArrayList<String>();
    for(Recipe recipe: recipeList){
      if(recipe.getName().contains(search))recipeNames.add(recipe.getName());
    }
    return recipeNames;
  }
}
