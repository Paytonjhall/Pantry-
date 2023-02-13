package Test;

import Pantry.FoodItem;
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

    Recipe recipe = new Recipe("apple cider", "I don't actually know how to make apple cider", "15 minutes", ingredients);
    Recipe recipe2 = new Recipe("sliced apples", "cut apples into 8 equal pieces, serve with peanut butter.", "5 minutes", ingredients);
    Recipe recipe3 = new Recipe("apple pie", "Cook the whole apple inside of a pie crust, super easy, kids love it. ", "45 minutes", ingredients, "src/Recipe/Photos/leaf.png");

    FoodItem apple = new FoodItem("apple", 5);
    user.addToStock(apple);
    FoodItem banana = new FoodItem("banana", 15);
    user.addToStock(banana);
    FoodItem orange = new FoodItem("orange", 25);
    user.addToStock(orange);


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
