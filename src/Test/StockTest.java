package Test;

import Pantry.Ingredient;
import Pantry.Stock;
import Recipe.Recipe;
import User.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class StockTest {

    Ingredient mockItem1 = new Ingredient("Eggs", 12);
    Ingredient mockItem2 = new Ingredient("Bread", 1);
    Ingredient mockItem3 = new Ingredient("Cheese", 2);
    Ingredient mockItem4 = new Ingredient("Milk", 1);
    Ingredient mockItem5 = new Ingredient("Butter", 1);
    Ingredient mockItem6 = new Ingredient("Chicken", 1);
    Ingredient mockItem7 = new Ingredient("Rice", 1);
    Ingredient mockItem8 = new Ingredient("Beans", 1);
    Ingredient mockItem9 = new Ingredient("Tomatoes", 1);


     Recipe mockRecipe1 = new Recipe("Egg in a hole", "Mix eggs and bread", "10 minutes", new ArrayList<Ingredient>());
     Recipe mockRecipe2 = new Recipe("Egg Sandwich", "Mix eggs, bread, and cheese", "15 minutes", new ArrayList<Ingredient>());
     Recipe mockRecipe3 = new Recipe("Eggnog", "Mix eggs, and milk", "20 minutes", new ArrayList<Ingredient>());

    Stock mockStock;
    User mockUser;

    @BeforeEach
    void setUp() {

        List<Ingredient> items = new ArrayList<>();
        items.add(mockItem1);
        items.add(mockItem2);
        mockStock = new Stock(items);
        mockUser = new User("mockUser", "mockPassword");

        mockUser.addToStock(mockItem1);
        mockUser.addToStock(mockItem2);
        mockUser.addToStock(mockItem3);

        //User can make this recipe
        mockRecipe1.getIngredients().add(mockItem1);
        mockRecipe1.getIngredients().add(mockItem2);

        //User can make this recipe
        mockRecipe2.getIngredients().add(mockItem1);
        mockRecipe2.getIngredients().add(mockItem2);
        mockRecipe2.getIngredients().add(mockItem3);

        //User can't make this recipe: missing milk
        mockRecipe3.getIngredients().add(mockItem1);
        mockRecipe3.getIngredients().add(mockItem4);

        //Add the recipes.
        mockUser.addRecipe(mockRecipe1);
        mockUser.addRecipe(mockRecipe2);
        mockUser.addRecipe(mockRecipe3);
    }

    @Test
    void addItem() {
        Assertions.assertEquals(2, mockStock.getStock().size());
        mockStock.addItem(mockItem3);
        Assertions.assertEquals(3, mockStock.getStock().size());
        Assertions.assertTrue(mockStock.inStock(mockItem3));
    }

    @Test
    void removeItemThatExists() {
        Assertions.assertEquals(2, mockStock.getStock().size());
        mockStock.removeItem(mockItem1);
        Assertions.assertEquals(1, mockStock.getStock().size());
        Assertions.assertFalse(mockStock.inStock(mockItem1));
    }

    @Test
    void removeItemThatNotExists() {
        Assertions.assertEquals(2, mockStock.getStock().size());
        mockStock.removeItem(mockItem3);
        Assertions.assertEquals(2, mockStock.getStock().size());
    }

    @Test
    void inStock() {
        Assertions.assertTrue(mockStock.inStock(mockItem1));
        Assertions.assertTrue(mockStock.inStock(mockItem2));
        Assertions.assertFalse(mockStock.inStock(mockItem3));
    }

    @Test
    void testMakeableRecipes() {
        // User can make 2 recipes, can't make the 3rd recipe.
        Assertions.assertEquals(2, mockUser.getRecipesUserCanMake().size());
    }

}