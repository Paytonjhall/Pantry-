package Test;

import Pantry.Ingredient;
import Recipe.Recipe;
import Recipe.RecipeBook;
import User.User;
import View.LoginView;
import View.MainView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TestUser {

public static void main(String[] args) {

    testUserRecipe();

  }

  public static void testUserRecipe(){

    File testFile = new File("../User/Users/test.json");
    try {
      Files.deleteIfExists(testFile.toPath());
    } catch (IOException ex) {
      System.out.println("Could not delete existing test user");
    }

    User user = new User("test", "test");


    // Add food to users stock
    Ingredient apple = new Ingredient("apple", 5,1,"cups");
    user.addToStock(apple);
    Ingredient banana = new Ingredient("banana", 15, 1, "cups");
    user.addToStock(banana);
    Ingredient orange = new Ingredient("orange", 25, 1, "cups");
    user.addToStock(orange);
    Ingredient egg = new Ingredient("egg", 36, 1, "cups");
    user.addToStock(egg);
    Ingredient milk = new Ingredient("milk", 4, 1, "cups");
    user.addToStock(milk);
    Ingredient bread = new Ingredient("bread", 2, 1, "cups");
    user.addToStock(bread);
    Ingredient cheese = new Ingredient("cheese", 4, 1, "cups");
    user.addToStock(cheese);
    Ingredient butter = new Ingredient("butter", 4, 1, "cups");
    user.addToStock(butter);
    Ingredient chicken = new Ingredient("chicken", 2, 1, "cups");
    user.addToStock(chicken);
    Ingredient rice = new Ingredient("rice", 16, 1, "cups");
    user.addToStock(rice);
    Ingredient beans = new Ingredient("beans", 12, 1, "cups");
    user.addToStock(beans);
    Ingredient tomatoes = new Ingredient("tomatoes", 4, 1, "cups");
    user.addToStock(tomatoes);

    // some food user doesn't have in stock used in recipes.
    Ingredient flour = new Ingredient("flour", 4);
    Ingredient sugar = new Ingredient("sugar", 2);
    Ingredient salt = new Ingredient("salt", 1);
    Ingredient pepper = new Ingredient("pepper", 2);
    Ingredient water = new Ingredient("water", 4);
    Ingredient tortilla = new Ingredient("tortilla", 4);
    Ingredient salsa = new Ingredient("chocolate chips", 2);


    // ingredients for shopping list
    Ingredient chocolateChips = new Ingredient("chocolate chips", 3, 1, "cups");
    Ingredient vanillaExtract = new Ingredient("vanilla extract", 1, 1, "teaspoon");
    Ingredient bakingSoda = new Ingredient("baking soda", 1, 1, "teaspoon");
    Ingredient bakingPowder = new Ingredient("baking powder", 1, 1, "teaspoon");
    Ingredient cinnamon = new Ingredient("cinnamon", 1, 1, "teaspoon");

    user.getStock().addShoppingListItem(chocolateChips);
    user.getStock().addShoppingListItem(vanillaExtract);
    user.getStock().addShoppingListItem(bakingSoda);
    user.getStock().addShoppingListItem(bakingPowder);
    user.getStock().addShoppingListItem(cinnamon);


    List<Ingredient> ingredients1 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients2 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients3 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients4 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients5 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients6 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients7 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients8 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients9 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients10 = new ArrayList<Ingredient>();

    ingredients1.add(new Ingredient("apple", 1, 1, "cups"));

    Recipe recipe = new Recipe("Apple cider", "I don't actually know how to make apple cider", "15 minutes", ingredients1);

    ingredients2.add(new Ingredient("bread", 1, 1, "unit"));
    ingredients2.add(new Ingredient("cheese", 1, 1, "unit"));
    ingredients2.add(new Ingredient("butter", 1, 1, "tablespoon"));
    Recipe recipe2 = new Recipe("Grilled Cheese", "Grill two slices of bread with cheese in between on griddle or pan. Apply butter generously.", "5 minutes", ingredients2);

    ingredients3.add(new Ingredient("chicken", 1, 1, "pound"));
    ingredients3.add(new Ingredient("rice", 2, 1, "cups"));
    ingredients3.add(new Ingredient("beans", 1, 1, "cups"));
    Recipe recipe3 = new Recipe("Chicken with rice and beans", "Cook each item individually and mix together with any spices you desire. Good for bulking. I'm making this instruction super long to test it's length.", "25 minutes", ingredients3, "src/Assets/leaf.png");

    ingredients4.add(new Ingredient("tomatoes", 2, 1, "cups"));
    ingredients4.add(new Ingredient("rice", 2, 1, "cups"));
    ingredients4.add(new Ingredient("beans", 1, 1, "cups"));
    ingredients4.add(new Ingredient("chicken", 1, 1, "cups"));
    ingredients4.add(new Ingredient("pepper", 1, 1, "tsp"));
    ingredients4.add(new Ingredient("salt", 1, 1, "tsp"));
    ingredients4.add(new Ingredient("tortilla", 2, 1, "tsp"));
    Recipe recipe4 = new Recipe("Enchilada", "Cook each item and place in tortilla, wrap and cook in oven.", "25 minutes", ingredients4, "src/Assets/leaf.png");

    ingredients5.add(new Ingredient("flour", 1, 1, "cups"));
    ingredients5.add(new Ingredient("sugar", 1, 1, "cups"));
    ingredients5.add(new Ingredient("chocolate chips", 1, 1, "cups"));
    ingredients5.add(new Ingredient("butter", 1, 1, "cups"));
    ingredients5.add(new Ingredient("water", 1, 1, "cups"));
    ingredients5.add(new Ingredient("salt", 1, 1, "tsp"));
    Recipe recipe5 = new Recipe("Chocolate Chip Cookies", "Mix all ingredients together and bake at 350 degrees for 10 minutes.", "25 minutes", ingredients5);

    ingredients6.add(new Ingredient("flour", 1, 1, "cups"));
    ingredients6.add(new Ingredient("sugar", 1, 1, "cups"));
    ingredients6.add(new Ingredient("butter", 1, 1, "cups"));
    ingredients6.add(new Ingredient("water", 1, 1, "cups"));
    ingredients6.add(new Ingredient("salt", 1, 1, "tsp"));
    Recipe recipe6 = new Recipe("Sugar Cookies", "Mix all ingredients together and bake at 350 degrees for 10 minutes.", "25 minutes", ingredients6);

    ingredients7.add(new Ingredient("flour", 1, 1, "cups"));
    ingredients7.add(new Ingredient("sugar", 1, 1, "cups"));
    ingredients7.add(new Ingredient("butter", 1, 1, "cups"));
    ingredients7.add(new Ingredient("water", 1, 1, "cups"));
    ingredients7.add(new Ingredient("cinnamon", 1, 1, "tsp"));
    Recipe recipe7 = new Recipe("Cinnamon Rolls", "Mix all ingredients together and bake at 350 degrees for 10 minutes.", "25 minutes", ingredients7, "src/Recipe/Photos/leaf.png");
    recipe7.setBreakfastTag(true);

    ingredients8.add(new Ingredient("beef", 1, 1, "pound"));
    ingredients8.add(new Ingredient("noodles", 2, 1, "cups"));
    ingredients8.add(new Ingredient("tomato sauce", 1, 1, "cups"));
    Recipe recipe8 = new Recipe("Spaghetti", "Shape beef into balls. Cook noodles and add sauce, add meatballs after cooking.", "25 minutes", ingredients8, "src/Assets/leaf.png");

    RecipeBook recipeBook = new RecipeBook();
    recipeBook.addRecipe(recipe);
    recipeBook.addRecipe(recipe2);
    recipeBook.addRecipe(recipe3);
    recipeBook.addRecipe(recipe4);
    recipeBook.addRecipe(recipe5);
    recipeBook.addRecipe(recipe6);
    recipeBook.addRecipe(recipe7);
    recipeBook.addRecipe(recipe8);

    user.setRecipeBook(recipeBook);
    MainView mainView = new MainView(user);
  }

  public static void testUser(){
    LoginView loginView = new LoginView();
  }
}
