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


    // Add food to users stock
    Ingredient apple = new Ingredient("apple", 5,1,"cups");
    user.addToStock(apple);
    Ingredient banana = new Ingredient("banana", 15, 1, "cups");
    user.addToStock(banana);
    Ingredient orange = new Ingredient("orange", 25);
    user.addToStock(orange);
    Ingredient egg = new Ingredient("egg", 36);
    user.addToStock(egg);
    Ingredient milk = new Ingredient("milk", 4);
    user.addToStock(milk);
    Ingredient bread = new Ingredient("bread", 2);
    user.addToStock(bread);
    Ingredient cheese = new Ingredient("cheese", 4);
    user.addToStock(cheese);
    Ingredient butter = new Ingredient("butter", 4);
    user.addToStock(butter);
    Ingredient chicken = new Ingredient("chicken", 2);
    user.addToStock(chicken);
    Ingredient rice = new Ingredient("rice", 16);
    user.addToStock(rice);
    Ingredient beans = new Ingredient("beans", 12);
    user.addToStock(beans);
    Ingredient tomatoes = new Ingredient("tomatoes", 4);
    user.addToStock(tomatoes);

    // some food user doesn't have in stock used in recipes.
    Ingredient flour = new Ingredient("flour", 4);
    Ingredient sugar = new Ingredient("sugar", 2);
    Ingredient salt = new Ingredient("salt", 1);
    Ingredient pepper = new Ingredient("pepper", 2);
    Ingredient water = new Ingredient("water", 4);
    Ingredient tortilla = new Ingredient("tortilla", 4);
    Ingredient salsa = new Ingredient("chocolate chips", 2);




    List<Ingredient> ingredients1 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients2 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients3 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients4 = new ArrayList<Ingredient>();
    List<Ingredient> ingredients5 = new ArrayList<Ingredient>();

    ingredients1.add(new Ingredient("apple", 1));

    Recipe recipe = new Recipe("apple cider", "I don't actually know how to make apple cider", "15 minutes", ingredients1);

    ingredients2.add(new Ingredient("bread", 1));
    ingredients2.add(new Ingredient("cheese", 1));
    ingredients2.add(new Ingredient("butter", 1));
    Recipe recipe2 = new Recipe("Grilled Cheese", "Grill two slices of bread with cheese in between on griddle or pan. Apply butter generously.", "5 minutes", ingredients2);

    ingredients3.add(new Ingredient("chicken", 1));
    ingredients3.add(new Ingredient("rice", 2));
    ingredients3.add(new Ingredient("beans", 1));
    Recipe recipe3 = new Recipe("Chicken with rice and beans", "Cook each item individually and mix together with any spices you desire. Good for bulking. I'm making this instruction super long to test it's length.", "25 minutes", ingredients3, "src/Recipe/Photos/leaf.png");

    ingredients4.add(new Ingredient("tomatoes", 2, 1, "cups"));
    ingredients4.add(new Ingredient("rice", 2, 1, "cups"));
    ingredients4.add(new Ingredient("beans", 1, 1, "cups"));
    ingredients4.add(new Ingredient("chicken", 1, 1, "cups"));
    ingredients4.add(new Ingredient("pepper", 1, 1, "tsp"));
    ingredients4.add(new Ingredient("salt", 1, 1, "tsp"));
    ingredients4.add(new Ingredient("tortilla", 2, 1, "tsp"));
    Recipe recipe4 = new Recipe("Enchilada", "Cook each item and place in tortilla, wrap and cook in oven.", "25 minutes", ingredients4, "src/Recipe/Photos/leaf.png");

    ingredients5.add(new Ingredient("flour", 1));
    ingredients5.add(new Ingredient("sugar", 1));
    ingredients5.add(new Ingredient("chocolate chips", 1));
    ingredients5.add(new Ingredient("butter", 1));
    ingredients5.add(new Ingredient("water", 1));
    ingredients5.add(new Ingredient("salt", 1));
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
