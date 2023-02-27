package User;

import Pantry.Ingredient;
import Recipe.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;


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

    public String convertFoodItemToJson(Ingredient foodItem) {
        return gson.toJson(foodItem);
    }

    public Ingredient convertJsonToFoodItem(String json) {
        return gson.fromJson(json, Ingredient.class);
    }

    public User checkUserFile(String username, String password) {
      Gson gson = new Gson();
      try {
        Reader reader = new FileReader("src/User/Users/" + username + ".json");
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
      File file = new File("src/User/Users/" + user.getUsername() + ".json");
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

    public boolean addPhotoToFile(String oldFilePath, String newFilePath) {
      try {
        Files.copy(new File(oldFilePath).toPath(), new File(newFilePath).toPath());
        return true;
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }

    }
}
