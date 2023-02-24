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


    // Add food to users stock
    FoodItem apple = new FoodItem("apple", 5);
    user.addToStock(apple);
    FoodItem banana = new FoodItem("banana", 15);
    user.addToStock(banana);
    FoodItem orange = new FoodItem("orange", 25);
    user.addToStock(orange);
    FoodItem egg = new FoodItem("egg", 36);
    user.addToStock(egg);
    FoodItem milk = new FoodItem("milk", 4);
    user.addToStock(milk);
    FoodItem bread = new FoodItem("bread", 2);
    user.addToStock(bread);
    FoodItem cheese = new FoodItem("cheese", 4);
    user.addToStock(cheese);
    FoodItem butter = new FoodItem("butter", 4);
    user.addToStock(butter);
    FoodItem chicken = new FoodItem("chicken", 2);
    user.addToStock(chicken);
    FoodItem rice = new FoodItem("rice", 16);
    user.addToStock(rice);
    FoodItem beans = new FoodItem("beans", 12);
    user.addToStock(beans);
    FoodItem tomatoes = new FoodItem("tomatoes", 4);
    user.addToStock(tomatoes);

    // some food user doesn't have in stock used in recipes.
    FoodItem flour = new FoodItem("flour", 4);
    FoodItem sugar = new FoodItem("sugar", 2);
    FoodItem salt = new FoodItem("salt", 1);
    FoodItem pepper = new FoodItem("pepper", 2);
    FoodItem water = new FoodItem("water", 4);
    FoodItem tortilla = new FoodItem("tortilla", 4);
    FoodItem salsa = new FoodItem("chocolate chips", 2);




    List<FoodItem> ingredients1 = new ArrayList<FoodItem>();
    List<FoodItem> ingredients2 = new ArrayList<FoodItem>();
    List<FoodItem> ingredients3 = new ArrayList<FoodItem>();
    List<FoodItem> ingredients4 = new ArrayList<FoodItem>();
    List<FoodItem> ingredients5 = new ArrayList<FoodItem>();

    ingredients1.add(new FoodItem("apple", 1));

    Recipe recipe = new Recipe("apple cider", "I don't actually know how to make apple cider", "15 minutes", ingredients1);

    ingredients2.add(new FoodItem("bread", 1));
    ingredients2.add(new FoodItem("cheese", 1));
    ingredients2.add(new FoodItem("butter", 1));
    Recipe recipe2 = new Recipe("Grilled Cheese", "Grill two slices of bread with cheese in between on griddle or pan. Apply butter generously.", "5 minutes", ingredients2);

    ingredients3.add(new FoodItem("chicken", 1));
    ingredients3.add(new FoodItem("rice", 2));
    ingredients3.add(new FoodItem("beans", 1));
    Recipe recipe3 = new Recipe("Chicken with rice and beans", "Cook each item individually and mix together with any spices you desire. Good for bulking.", "25 minutes", ingredients3, "src/Recipe/Photos/leaf.png");

    ingredients4.add(new FoodItem("tomatoes", 1));
    ingredients4.add(new FoodItem("rice", 2));
    ingredients4.add(new FoodItem("beans", 1));
    ingredients4.add(new FoodItem("chicken", 1));
    ingredients4.add(new FoodItem("pepper", 1));
    ingredients4.add(new FoodItem("salt", 1));
    ingredients4.add(new FoodItem("tortilla", 2));
    Recipe recipe4 = new Recipe("Enchilada", "Cook each item and place in tortilla, wrap and cook in oven.", "25 minutes", ingredients4, "src/Recipe/Photos/leaf.png");

    ingredients5.add(new FoodItem("flour", 1));
    ingredients5.add(new FoodItem("sugar", 1));
    ingredients5.add(new FoodItem("chocolate chips", 1));
    ingredients5.add(new FoodItem("butter", 1));
    ingredients5.add(new FoodItem("water", 1));
    ingredients5.add(new FoodItem("salt", 1));
    Recipe recipe5 = new Recipe("Chocolate Chip Cookies", "Mix all ingredients together and bake at 350 degrees for 10 minutes.", "25 minutes", ingredients5, "src/Recipe/Photos/leaf.png");


    RecipeBook recipeBook = new RecipeBook();
    recipeBook.addRecipe(recipe);
    recipeBook.addRecipe(recipe2);
    recipeBook.addRecipe(recipe3);
    recipeBook.addRecipe(recipe4);
    recipeBook.addRecipe(recipe5);

    user.setRecipeBook(recipeBook);
    MainView mainView = new MainView(user);
  }

  public static void testUser(){
    LoginView loginView = new LoginView();
  }
}
