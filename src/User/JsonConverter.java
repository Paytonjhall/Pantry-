package User;

import Pantry.FoodItem;
import Recipe.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;


public class JsonConverter {
  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public String convertRecipeToJson(Recipe recipe) {
    return gson.toJson(recipe);
  }

  public Recipe convertJsonToRecipe(String json) {
    return gson.fromJson(json, Recipe.class);
  }

  public String convertUserToJson(User user) {
    return gson.toJson(user);
  }

    public User convertJsonToUser(String json) {
        return gson.fromJson(json, User.class);
    }

    public String convertFoodItemToJson(FoodItem foodItem) {
        return gson.toJson(foodItem);
    }

    public FoodItem convertJsonToFoodItem(String json) {
        return gson.fromJson(json, FoodItem.class);
    }

    public User checkUserFile(String username, String password) {
      Gson gson = new Gson();
      try {
        Reader reader = new FileReader("src/Files/Users/" + username + ".json");
        User user = gson.fromJson(reader, User.class);
        if (user.getPassword().equals(password)) {
          return user;
        } else return null; // wrong password
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null; // no file found
    }

    public boolean addUserToFile(User user) {
      File file = new File("src/Files/Users/" + user.getUsername() + ".json");
      try (FileWriter writer = new FileWriter(file);) {
        gson.toJson(user, writer);

        return true;
      } catch (IOException e) {
        System.out.println("Error saving game");
        e.printStackTrace();
        //File might exist already.
        return false;
      }
    }
}