package User;
import Pantry.Ingredient;
import Pantry.Stock;
import Recipe.Recipe;
import Recipe.RecipeBook;

import java.util.ArrayList;
import java.util.List;

public class User {
  String username;
  String password;
  Stock stock;
  RecipeBook recipeBook;
  Boolean subscribed; //Paying customer

  //Register
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.stock = new Stock();
        this.recipeBook = new RecipeBook();
        this.subscribed = false;
    }

  //TODO: Functions to write
    // search from recipe list


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock=stock;
  }

  public RecipeBook getRecipeBook() {
      if(recipeBook == null) {
          recipeBook = new RecipeBook();
      }
    return recipeBook;
  }

  public void setRecipeBook(RecipeBook recipeBook) {
    this.recipeBook=recipeBook;
  }

  public Boolean getSubscribed() {
    return subscribed;
  }

  public void setSubscribed(Boolean subscribed) {
    this.subscribed=subscribed;
  }

  public void addToStock(Ingredient item) {
      if(stock == null) {
          stock = new Stock();
      }
      stock.addItem(item);
  }

  public List<Recipe> getRecipesUserCanMake() {
      List<Recipe> recipes = new ArrayList<Recipe>();
      for(Recipe recipe : recipeBook.getRecipeList()) {
          boolean makeable = true;
            for(Ingredient ingredient : recipe.getIngredients()) {
                if(!stock.inStock(ingredient) || !(stock.getFoodItem(ingredient.getName()).getBaseUnitQuantity() >= ingredient.getBaseUnitQuantity())) {
                    makeable = false;
                }
            }
          if(makeable) recipes.add(recipe);
      }
      return recipes;
  }

  public boolean isMakeable(Recipe recipe) {
      boolean makeable = true;
      for(Ingredient ingredient : recipe.getIngredients()) {
          if(!stock.inStock(ingredient) || !(stock.getFoodItem(ingredient.getName()).getQuantity() >= ingredient.getQuantity())) {
              makeable = false;
          }
      }
      return makeable;
  }

  public boolean makeRecipe(Recipe recipe) {
      if(isMakeable(recipe)) {
          for(Ingredient ingredient : recipe.getIngredients()) {
              stock.removeAmountFromStock(ingredient, ingredient.getQuantity());
          }
          return true;
      }
      return false;
  }

  public List<String> getStringsUserCanMake(){
        List<Recipe> recipes = getRecipesUserCanMake();
        List<String> strings = new ArrayList<String>();
        for(Recipe recipe : recipes) {
            strings.add(recipe.getName());
        }
        return strings;
  }

  public void addRecipe(Recipe recipe) {
      if(recipeBook == null) {
          recipeBook = new RecipeBook();
      }
      recipeBook.addRecipe(recipe);
  }

  public List<String> getIngredientStringList(Recipe recipe){
        List<String> ingredientNames = new ArrayList<String>();
        if(recipe.getIngredients()!=null) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (haveIngredient(ingredient)) {
                    String itemName = ingredient.getName();
                    Ingredient itemInStock = stock.getFoodItem(itemName);
                    ingredientNames.add(itemName + ": " + ingredient.getQuantity() + " " + ingredient.getAbbreviation() +
                            " (in stock: " + itemInStock.getQuantity() + " " + itemInStock.getAbbreviation() + ")");
                }
                else {
                    ingredientNames.add(ingredient.getName() + ": " + ingredient.getQuantity() + " " + ingredient.getAbbreviation() +
                            " (not in stock)");
                }

            }
        }
        return ingredientNames;
    }

  public boolean haveIngredient(Ingredient ingredient) {
      if(stock == null) {
          stock = new Stock();
      }
      if(stock.inStock(ingredient) &&
              stock.getFoodItem(ingredient.getName()).getBaseUnitQuantity() >= ingredient.getBaseUnitQuantity())
            return true;
      return false;
  }

  public double haveIngredientQuantity(Ingredient ingredient) {
      if(stock == null) {
          stock = new Stock();
      }
      if(stock.inStock(ingredient))
            return stock.getFoodItem(ingredient.getName()).getQuantity();
      return 0;
  }


}
