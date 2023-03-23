package Recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeBook {

  ArrayList<Recipe> recipeList;


  public ArrayList<Recipe> getRecipeList() {
    return recipeList;
  }

  public void setRecipeList(ArrayList<Recipe> recipeList) {
    this.recipeList=recipeList;
    alphabetizeRecipeBook();
  }

  public void addRecipe(Recipe recipe) {
    if(recipeList!=null)recipeList.add(recipe);
    else {
      recipeList = new ArrayList<Recipe>();
      recipeList.add(recipe);
    }

    alphabetizeRecipeBook();
  }

    public void removeRecipe(Recipe recipe) {
        if(recipeList!=null)recipeList.remove(recipe);
    }

    public void editRecipe(Recipe oldRecipe, Recipe newRecipe) {
        if(recipeList!=null) {
            recipeList.remove(oldRecipe);
            recipeList.add(newRecipe);
        }

        if (!(oldRecipe.name.equals(newRecipe))) {
          alphabetizeRecipeBook();
        }
    }

  public List<String> getRecipeStringList(){
    List<String> recipeNames = new ArrayList<String>();
    if(recipeList!=null) {
      for (Recipe recipe : recipeList) {
        recipeNames.add(recipe.getName());
      }
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

  public List<String> getBreakfastRecipes() {
    List<String> recipeNames = new ArrayList<String>();
    for(Recipe recipe: recipeList) {
      if (recipe.getBreakfastTag()) {
        recipeNames.add(recipe.getName());
      }
    }
    return recipeNames;
  }

  private void alphabetizeRecipeBook() {
    Collections.sort(recipeList);
  }
}
