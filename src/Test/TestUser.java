package Test;

import Pantry.Ingredient;
import Recipe.*;
import User.User;
import View.LoginView;
import View.MainView;

import java.util.ArrayList;
import java.util.List;

public class TestUser {

public static void main(String[] args) {

    testUserRecipe();

  }

  public static void testUserRecipe(){
    User user = new User("test", "test");
    List<Ingredient> ingredients = new ArrayList<Ingredient>();
    ingredients.add(new Ingredient("apple", 1));

    Recipe recipe = new Recipe("hot apple", "boil apple in water", "5 minutes", ingredients);
    Recipe recipe2 = new Recipe("cold apple", "freeze apple", "5 minutes", ingredients);
    Recipe recipe3 = new Recipe("apple", "just an apple", "0 minutes", ingredients);


    RecipeBook recipeBook = new RecipeBook();
    recipeBook.addRecipe(recipe);
    recipeBook.addRecipe(recipe2);
    recipeBook.addRecipe(recipe3);

    user.setRecipeBook(recipeBook);
    MainView mainView = new MainView(user);
  }

  public static void testUser(){
    LoginView loginView = new LoginView();
  }
}
