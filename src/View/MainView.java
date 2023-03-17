package View;
import Pantry.Ingredient;
import Recipe.Recipe;
import User.*;
import View.ActionListeners.FoodItemAction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame{
  private JsonConverter jsonConverter = new JsonConverter();
  User user;
  private JList RecipeList;
  private JPanel MainViewPanel;
  private JButton addRecipeButton;
  private JButton logoutButton;
  private JButton saveButton;
  private JPanel RecipePanel;
  private JLabel RecipeLabel;
  private JTextArea RecipeLabelInfo;
  private JList RecipeIngredientList;
  private JButton deleteRecipeButton;
  private JButton editRecipeButton;
  private JPanel FoodPanel;
  private JLabel RecipePhoto;
  private JLabel RecipeCookTime;
  private JList PantryList;
  private JLabel RecipeListText;
  private JLabel PantryListText;
  private JButton makeRecipeButton;
  private JPanel RecipesListPanel;
  private JPanel SaveLogoutPanel;
  private JButton deleteStockButton;
  private JButton editStockButton;
  private JButton addToPantryButton;
  private JTabbedPane recipesTabPanel;
  private JPanel makeableRecipes;
  private JLabel makeableRecipesText;
  private JList makeableRecipesList;
  private JButton addRecipeButtonMakeable;
  private JLabel ShoppingListText;
  private JList ShoppingListList;
  private JButton editShoppingListButton;
  private JButton addShoppingListItemButton;
  private JButton clearShoppingListButton;
  private JTabbedPane pantryTabPanel;
  private JTextField SearchBox;

  int ALL_RECIPES = 0;
  int MAKEABLE_RECIPES = 1;
  //User user;

  //Load main view
  public MainView( User user ) {
    this.user = user;
    //Create frame
    setContentPane(MainViewPanel);
    setTitle("Pantry++");
    setSize(1300, 550);
    RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
    PantryList.setListData(user.getStock().getFoodNamesWithQuantity().toArray());
    ShoppingListList.setListData(user.getStock().getShoppingListNamesWithQuantity().toArray());
    System.out.println(user.getStock().getFoodNamesWithQuantity());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setIconImage(Toolkit.getDefaultToolkit().getImage("images/pantryLogoBackground.png"));
    //Set buttons to invisible, only visible when a recipe is selected
    RecipePhoto.setIcon(new ImageIcon("src/Assets/no-images.png"));
    RecipePhoto.setVisible(false);
    editShoppingListButton.setVisible(false);
    deleteRecipeButton.setVisible(false);
    editRecipeButton.setVisible(false);
    makeRecipeButton.setVisible(false);
    editStockButton.setVisible(false);
    setVisible(true);
    RecipeLabelInfo.setLineWrap(true);

    // When you click on an item in your pantry, the edit stock button should show up
    PantryList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        editStockButton.setVisible(true);
      }
    });

    // When you click on an item in your shopping list, the edit shopping list button should show up
    ShoppingListList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        editShoppingListButton.setVisible(true);
      }
    });

    // Clear shopping list button
    clearShoppingListButton.addActionListener(e -> {
      user.getStock().clearShoppingList();
      ShoppingListList.setListData(user.getStock().getShoppingListNamesWithQuantity().toArray());
    });


    // Edit button for pantry
    editStockButton.addActionListener(new EditStockAction());


    // edit button for shopping list
    editShoppingListButton.addActionListener(new EditShoppingListAction());


    // TODO: ADD A DELETE PANTRY ITEM BUTTON

    // add button for pantry
    addToPantryButton.addActionListener(new AddToPantryAction());


    class AddShoppingListItemAction implements ActionListener {

      void createBox() {
        JTextField foodNameField = new JTextField();
        foodNameField.setSize(150, 20); //Increasing one of the field sizes makes the dialog box bigger
        foodNameField.setPreferredSize(new Dimension(150, 20));
        JTextField foodQuantityField = new JTextField();

        JSpinner numUnits = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        JSpinner sizeOneUnit = new JSpinner(new SpinnerNumberModel(1, 0, 2000, 0.25));
        String possibleUnits[] = {
                "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER", "FLUID OUNCE"
        };
        JComboBox<String> unit = new JComboBox<>(possibleUnits);
        unit.setSelectedIndex(0);

        Object[] message = {
                "Food Name:", foodNameField,
                "Number of units:", numUnits,
                "Size of 1 unit:", sizeOneUnit,
                unit
        };

        handleOutput(message, foodNameField, numUnits, sizeOneUnit, unit);
      }

      void handleOutput(Object[] message, JTextField foodNameField, JSpinner numUnits, JSpinner sizeOneUnit,
                        JComboBox<String> unit) {
        int output = JOptionPane.showConfirmDialog(null, message, "Add Food Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, getLogo());
        if (output == JOptionPane.OK_OPTION) {
          Ingredient foodItem = new Ingredient(foodNameField.getText(), (Integer) numUnits.getValue(),
                  (double) sizeOneUnit.getValue(), unit.getItemAt(unit.getSelectedIndex()));
          user.getStock().addShoppingListItem(foodItem);
          ShoppingListList.setListData(user.getStock().getShoppingListNamesWithQuantity().toArray());
        }
      }

      @Override
      public void actionPerformed(ActionEvent e) {
        createBox();
      }
    }

    // add button for shopping list
    addShoppingListItemButton.addActionListener(new AddShoppingListItemAction());

    recipesTabPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println(recipesTabPanel.getSelectedIndex());
        if (ALL_RECIPES == recipesTabPanel.getSelectedIndex()) {
          RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
        } else if (MAKEABLE_RECIPES == recipesTabPanel.getSelectedIndex()) {
          makeableRecipesText.setText("Makeable Recipes");
          makeableRecipesList.setVisible(true);
          makeableRecipesList.setListData(user.getStringsUserCanMake().toArray());
          makeRecipeButton.setVisible(true);
        }
      }
    });

    makeableRecipes.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        makeableRecipesText.setText("Makeable Recipes");
        makeableRecipesList.setVisible(true);
        makeableRecipesList.setListData(user.getStringsUserCanMake().toArray());
        makeRecipeButton.setVisible(true);
      }
    });

    makeableRecipesList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        JLabel recipe = new JLabel();
        Recipe recipeSelected = user.getRecipesUserCanMake().get(makeableRecipesList.getSelectedIndex());
        recipe.setText(recipeSelected.getName());
        RecipeLabel.setText("Recipe: " + recipeSelected.getName());
        RecipeLabelInfo.setText("Instructions: \n" + recipeSelected.getInstructions());
        RecipeCookTime.setText("Cook Time: " + recipeSelected.getTime());
        RecipeIngredientList.removeAll();
        RecipeIngredientList.setListData(user.getIngredientStringList(recipeSelected).toArray()); //Replaced for loop with this, should work
        if (recipeSelected.getImage() != null) {
          Image dimg = getImage(recipeSelected.getImage()).getScaledInstance(150, 150, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          RecipePhoto.setIcon(imageIcon);
          //RecipePhoto.setIcon(new ImageIcon(recipeSelected.getImage()));
        } else {
          RecipePhoto.setIcon(new ImageIcon("src/Assets/no-images.png"));
          RecipePhoto.setToolTipText("No image found");
        }
        RecipePhoto.setVisible(true);
        deleteRecipeButton.setVisible(true);
        editRecipeButton.setVisible(true);
        makeRecipeButton.setVisible(true);
        RecipePanel.repaint();
      }
    });

    //Select Recipe action listener
    RecipeList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        JLabel recipe = new JLabel();
        Recipe recipeSelected = user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex());
        recipe.setText(recipeSelected.getName());
        RecipeLabel.setText("Recipe: " + recipeSelected.getName());
        RecipeLabelInfo.setText("Instructions: \n" + recipeSelected.getInstructions());
        RecipeCookTime.setText("Cook Time: " + recipeSelected.getTime());
        RecipeIngredientList.removeAll();
        RecipeIngredientList.setListData(user.getIngredientStringList(recipeSelected).toArray()); //Replaced for loop with this, should work
        if (recipeSelected.getImage() != null) {
          Image dimg = getImage(recipeSelected.getImage()).getScaledInstance(150, 150, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          RecipePhoto.setIcon(imageIcon);
          //RecipePhoto.setIcon(new ImageIcon(recipeSelected.getImage()));
        } else {
          RecipePhoto.setIcon(new ImageIcon("src/Assets/no-images.png"));
          RecipePhoto.setToolTipText("No image found");
        }
        RecipePhoto.setVisible(true);
        deleteRecipeButton.setVisible(true);
        editRecipeButton.setVisible(true);
        makeRecipeButton.setVisible(true);
        RecipePanel.repaint();
      }
    });

    //Save Button --> Complete: Done
    saveButton.addActionListener(e -> {
      try {
        jsonConverter.addUserToFile(user);
      } catch (Exception ex) {
        System.out.println("Error saving user: " + ex);
      }
    });

    //Logout Button --> Complete: Done
    logoutButton.addActionListener(e -> {
      LoginView loginView = new LoginView();
      dispose();
    });

    //add recipe button --> Complete: Done
    addRecipeButton.addActionListener(e -> {
      JTextField recipeName = new JTextField();
      JTextField recipeInstructions = new JTextField();
      JTextField recipeIngredients = new JTextField();
      JTextField cookTime = new JTextField();
      String possibleUnits[] = {
              "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER",
              "TABLESPOON", "TEASPOON", "FLUID OUNCE"
      }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST

      JComboBox<String> unit = new JComboBox<>(possibleUnits);
      unit.setSelectedIndex(0);
      SpinnerModel model = new SpinnerNumberModel(1, 0, 1000, 0.01);
      JSpinner ingredientSize = new JSpinner(model);
      Recipe recipe = new Recipe();
      JButton uploadPhoto = new JButton("Add Photo");
      JButton addIngredientButton = new JButton("Add Ingredient");
      Object[] message = {
              "Recipe Name:", recipeName,
              "Recipe Instructions:", recipeInstructions,
              "Recipe Ingredient:", recipeIngredients,
              "Ingredient Quantity:", ingredientSize, unit,
              addIngredientButton,
              "Cook Time:", cookTime,
              "Upload Photo:", uploadPhoto
      };
      List<Ingredient> ingredients = new ArrayList<>();

      addIngredientButton.addActionListener(e1 -> {
        ingredients.add(new Ingredient(recipeIngredients.getText(), (double) ingredientSize.getValue(), unit.getItemAt(unit.getSelectedIndex())));
        recipeIngredients.setText("");
        ingredientSize.setValue(1);
        unit.setSelectedIndex(0);
      });

      Recipe finalRecipe = new Recipe();
      finalRecipe.setName(recipeName.getText());
      uploadPhoto.addActionListener(e1 -> {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(getParent());
        if (result == JFileChooser.APPROVE_OPTION) {
          try {
            File file = fileChooser.getSelectedFile();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            String stringFile = file.toString();
            Image image = toolkit.getImage(stringFile);
            Path path = Paths.get(stringFile);
            String imagePath = path.toAbsolutePath().toString();
            String newStr = imagePath.toString();
            BufferedImage picture = ImageIO.read(new File(newStr));
            String extension = newStr.substring(newStr.lastIndexOf(".") + 1);
            String newPath = "src/Recipe/Photos/" + user.getUsername() + "-" + recipeName.getText() + "." + extension;
            finalRecipe.setImage(newPath);
            jsonConverter.addPhotoToFile(imagePath, newPath);
          } catch (IOException ex) {
            System.out.println("Error uploading photo: " + ex);
          }
        }
      });

      int option = JOptionPane.showConfirmDialog(null, message, "Add Recipe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, getLogo());
      if (option == JOptionPane.OK_OPTION) {
        finalRecipe.setIngredients(ingredients);
        finalRecipe.setName(recipeName.getText());
        finalRecipe.setInstructions(recipeInstructions.getText());
        finalRecipe.setTime(cookTime.getText());
        finalRecipe.setIngredients(ingredients);
        user.getRecipeBook().addRecipe(finalRecipe);
        RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());

      }
    });

    //add recipe on make-able recipe tab
    addRecipeButtonMakeable.addActionListener(e -> {
      JTextField recipeName = new JTextField();
      JTextField recipeInstructions = new JTextField();
      JTextField recipeIngredients = new JTextField();
      JTextField cookTime = new JTextField();
      String possibleUnits[] = {
              "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER",
              "TABLESPOON", "TEASPOON", "FLUID OUNCE"
      }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST

      JComboBox<String> unit = new JComboBox<>(possibleUnits);
      unit.setSelectedIndex(0);
      SpinnerModel model = new SpinnerNumberModel(1, 0, 1000, 0.01);
      JSpinner ingredientSize = new JSpinner(model);
      Recipe recipe = new Recipe();
      JButton uploadPhoto = new JButton("Add Photo");
      JButton addIngredientButton = new JButton("Add Ingredient");
      Object[] message = {
              "Recipe Name:", recipeName,
              "Recipe Instructions:", recipeInstructions,
              "Recipe Ingredient:", recipeIngredients,
              "Ingredient Quantity:", ingredientSize, unit,
              addIngredientButton,
              "Cook Time:", cookTime,
              "Upload Photo:", uploadPhoto
      };
      List<Ingredient> ingredients = new ArrayList<>();

      addIngredientButton.addActionListener(e1 -> {
        ingredients.add(new Ingredient(recipeIngredients.getText(), (Double) ingredientSize.getValue(), unit.getItemAt(unit.getSelectedIndex())));
        recipeIngredients.setText("");
        ingredientSize.setValue(1);
        unit.setSelectedIndex(0);
      });

      Recipe finalRecipe = new Recipe();
      finalRecipe.setName(recipeName.getText());
      uploadPhoto.addActionListener(e1 -> {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(getParent());
        if (result == JFileChooser.APPROVE_OPTION) {
          try {
            File file = fileChooser.getSelectedFile();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            String stringFile = file.toString();
            Image image = toolkit.getImage(stringFile);
            Path path = Paths.get(stringFile);
            String imagePath = path.toAbsolutePath().toString();
            String newStr = imagePath.toString();
            BufferedImage picture = ImageIO.read(new File(newStr));
            String extension = newStr.substring(newStr.lastIndexOf(".") + 1);
            String newPath = "src/Recipe/Photos/" + user.getUsername() + "-" + recipeName.getText() + "." + extension;
            finalRecipe.setImage(newPath);
            jsonConverter.addPhotoToFile(imagePath, newPath);
          } catch (IOException ex) {
            System.out.println("Error uploading photo: " + ex);
          }
        }
      });

      int option = JOptionPane.showConfirmDialog(null, message, "Add Recipe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, getLogo());
      if (option == JOptionPane.OK_OPTION) {
        finalRecipe.setIngredients(ingredients);
        finalRecipe.setName(recipeName.getText());
        finalRecipe.setInstructions(recipeInstructions.getText());
        finalRecipe.setTime(cookTime.getText());
        finalRecipe.setIngredients(ingredients);
        user.getRecipeBook().addRecipe(finalRecipe);
        RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());

      }
    });

    //Delete Recipe Button --> Complete: Done
    // TODO: Make sure this handles deleting recipes when the user is on the makeable recipes tab
    deleteRecipeButton.addActionListener(e -> {
      if (user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()).getImage() != null) {
        File file = new File(user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()).getImage());
        file.delete();
      }
      user.getRecipeBook().removeRecipe(user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()));
      RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
      RecipeLabel.setText("Recipe: ");
      RecipeLabelInfo.setText("Instructions: ");
      RecipeCookTime.setText("Cook Time: ");
      RecipeIngredientList.setListData(new String[0]);
      deleteRecipeButton.setVisible(false);
      editRecipeButton.setVisible(false);
      RecipePhoto.setVisible(false);
      makeRecipeButton.setVisible(false);

    });

    //Edit recipe Button --> Complete: Done
    editRecipeButton.addActionListener(e -> {
      Recipe recipe;
      if (MAKEABLE_RECIPES == recipesTabPanel.getSelectedIndex()) {
        recipe = user.getRecipesUserCanMake().get(makeableRecipesList.getSelectedIndex());
      } else {
        recipe = user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex());
      }
      JTextField recipeName = new JTextField(recipe.getName());
      JTextField recipeInstructions = new JTextField(recipe.getInstructions());
      JTextField recipeIngredients = new JTextField();
      JTextField cookTime = new JTextField(recipe.getTime());
      String possibleUnits[] = {
              "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER",
              "TABLESPOON", "TEASPOON", "FLUID OUNCE"
      };
      JComboBox<String> unit = new JComboBox<>(possibleUnits);
      unit.setSelectedIndex(0);
      SpinnerModel model = new SpinnerNumberModel(1, 0, 1000, 0.01);
      JSpinner ingredientSize = new JSpinner(model);


      JButton addIngredientButton = new JButton("Add Ingredient");
      Object[] message = {
              "Recipe Name:", recipeName,
              "Recipe Instructions:", recipeInstructions,
              "Recipe Ingredients: (please re-enter all ingredients) ", recipeIngredients,
              "Ingredient Quantity:", ingredientSize, unit,
              addIngredientButton,
              "Cook Time:", cookTime

      };

      List<Ingredient> ingredients = new ArrayList<>();

      addIngredientButton.addActionListener(e1 -> {
        ingredients.add(new Ingredient(recipeIngredients.getText(), (Double) ingredientSize.getValue(), unit.getItemAt(unit.getSelectedIndex())));
        recipeIngredients.setText("");
        ingredientSize.setValue(1);
        unit.setSelectedIndex(0);
      });

      int option = JOptionPane.showConfirmDialog(null, message, "Edit Recipe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, getLogo());

      if (option == JOptionPane.OK_OPTION) {
        Recipe newRecipe = new Recipe(recipeName.getText(), recipeInstructions.getText(), cookTime.getText(), ingredients);
        user.getRecipeBook().editRecipe(recipe, newRecipe);
        RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
        RecipeLabel.setText("Recipe: ");
        RecipeLabelInfo.setText("Instructions: ");
        RecipeCookTime.setText("Cook Time: ");
        RecipeIngredientList.setListData(new String[0]);
        deleteRecipeButton.setVisible(false);
        editRecipeButton.setVisible(false);
      }
    });

    makeRecipeButton.addActionListener(e1 -> {
      boolean type = true;
      if (recipesTabPanel.getSelectedIndex() == 1) {
        type = false;
      }
      Recipe recipeBeingMade;
      if (type) recipeBeingMade = user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex());
      else recipeBeingMade = user.getRecipeBook().getRecipeList().get(makeableRecipesList.getSelectedIndex());
      System.out.println(recipeBeingMade.toString());
      if (user.isMakeable(recipeBeingMade)) {
        int option = JOptionPane.showConfirmDialog(null, "Make recipe?: " + recipeBeingMade.getName(), recipeBeingMade.getName(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, getLogo());
        if (option == JOptionPane.OK_OPTION) {
          user.makeRecipe(recipeBeingMade);
          if (type) {
            RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
            RecipeLabel.setText("Recipe: ");
            RecipeLabelInfo.setText("Instructions: ");
            RecipeCookTime.setText("Cook Time: ");
            RecipeIngredientList.setListData(new String[0]);
            deleteRecipeButton.setVisible(false);
            editRecipeButton.setVisible(false);
            RecipePhoto.setVisible(false);
            makeRecipeButton.setVisible(false);
          } else {
            makeableRecipesList.setListData(user.getStringsUserCanMake().toArray());
            RecipeLabel.setText("Recipe: ");
            RecipeLabelInfo.setText("Instructions: ");
            RecipeCookTime.setText("Cook Time: ");
            RecipeIngredientList.setListData(new String[0]);
            deleteRecipeButton.setVisible(false);
            editRecipeButton.setVisible(false);
            RecipePhoto.setVisible(false);
            makeRecipeButton.setVisible(false);
          }
        }
      } else {
        JOptionPane.showMessageDialog(null, "You do not have enough ingredients to make this recipe.", "Error", JOptionPane.ERROR_MESSAGE, getLogo());
      }
      PantryList.setListData(user.getStock().getFoodNamesWithQuantity().toArray());
      //makeableRecipesList.setListData(user.getStringsUserCanMake().toArray());
    });

  }


  private static Image getImage(String iconPath) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(iconPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return img;
  }

  public static ImageIcon getLogo() {
    Image dimg = getImage("images/pantryLogoNoBackground.png").getScaledInstance(135, 135, Image.SCALE_SMOOTH);
    return new ImageIcon(dimg);
  }


  private class EditShoppingListAction extends FoodItemAction {

    JSpinner sizeOneUnitField;
    Ingredient item;

    @Override
    protected void createAdditionalFields() {
      item = user.getStock().getShoppingList().getItem(ShoppingListList.getSelectedIndex());
      sizeOneUnitField = new JSpinner(new SpinnerNumberModel(1, 0, 2000, 0.25));
    }

    @Override
    protected void initializeFields() {
      unitField.setSelectedIndex(0);
      foodNameField.setText(item.getName());
      numUnitsField.setValue(item.getQuantity());
      double unitSize = 1; // Because we are converting all to one size, we forget what the unit size is, so set to 1
      sizeOneUnitField.setValue(unitSize);

      String unitType = item.getUnitType();
      int indexOfUnit = -1;
      for (int i = 0; i < possibleUnits.length; i++) {
        if (unitType.toUpperCase().equals(possibleUnits[i])) {
          indexOfUnit = i;
        }
      }
      if (indexOfUnit > -1) {
        unitField.setSelectedIndex(indexOfUnit);
      } else {
        unitField.setSelectedIndex(0);
      }
    }

    @Override
    protected Object[] getMessage() {
      Object[] message = {
              "Food Name:", foodNameField,
              "Number of units:", numUnitsField,
              "Size of 1 unit:", sizeOneUnitField,
              unitField
      };
      return message;
    }

    @Override
    protected String getTitle() {
      return "Add Food Item";
    }

    @Override
    protected void processOutput() {
      String foodName = foodNameField.getText();
      Object numUnits = numUnitsField.getValue();
      Object sizeOneUnit = sizeOneUnitField.getValue();
      double foodQuantity = Double.parseDouble(numUnits.toString());
      double unitSize = Double.parseDouble(sizeOneUnit.toString());
      Ingredient newItem = new Ingredient(foodName, foodQuantity, unitSize, unitField.getItemAt(unitField.getSelectedIndex()));
      user.getStock().editShoppingListItem(item, newItem);
      ShoppingListList.setListData(user.getStock().getShoppingListNamesWithQuantity().toArray());
      editShoppingListButton.setVisible(false);
    }

  }

  private class AddToPantryAction extends FoodItemAction {

    JSpinner servingSize;
    JSpinner servingsPerContainer;

    @Override
    protected void createAdditionalFields() {
      servingSize = new JSpinner(new SpinnerNumberModel(1, 0, 2000, 0.01));
      servingsPerContainer = new JSpinner(new SpinnerNumberModel(1, 0, 2000, 0.01));
    }

    @Override
    protected void initializeFields() {
      unitField.setSelectedIndex(0);
    }

    @Override
    protected Object[] getMessage() {
      Object[] message = {
              "Food Name:", foodNameField,
              "Number of units:", numUnitsField,
              "Servings Per Container:", servingsPerContainer,
              "Serving Size:", servingSize,
              unitField
      };
      return message;
    }

    @Override
    protected String getTitle() {
      return "Add Food Item";
    }

    @Override
    protected void processOutput() {
      Double numUnitsValue = Double.parseDouble(numUnitsField.getValue().toString());
      Double servingSizeVal = Double.parseDouble(servingSize.getValue().toString());
      Double spcVal = Double.parseDouble(servingsPerContainer.getValue().toString());

      Ingredient foodItem = new Ingredient(foodNameField.getText(), numUnitsValue,
              servingSizeVal, spcVal, unitField.getItemAt(unitField.getSelectedIndex()));
      user.addToStock(foodItem);
      PantryList.setListData(user.getStock().getFoodNamesWithQuantity().toArray());
    }
  }

  private class EditStockAction extends FoodItemAction {

    Ingredient item;
    JSpinner servingSize;
    JSpinner servingsPerContainer;

    @Override
    protected void createAdditionalFields() {
      item = user.getStock().getStock().get(PantryList.getSelectedIndex());
      servingSize = new JSpinner(new SpinnerNumberModel(1, 0, 2000, 0.01));
      servingsPerContainer = new JSpinner(new SpinnerNumberModel(1, 0, 2000, 0.01));

    }

    @Override
    protected void initializeFields() {
      unitField.setSelectedIndex(0);
      foodNameField.setText(item.getName());
      numUnitsField.setValue(item.getQuantity());

      double unitSize = 1; // Because we are converting all to one size, we forget what the unit size is, so set to 1
      servingSize.setValue(unitSize);

      String unitType = item.getUnitType();
      int indexOfUnit = -1;
      for (int i = 0; i < possibleUnits.length; i++) {
        if (unitType.toUpperCase().equals(possibleUnits[i])) {
          indexOfUnit = i;
        }
      }
      if (indexOfUnit > -1) {
        unitField.setSelectedIndex(indexOfUnit);
      } else {
        unitField.setSelectedIndex(0);
      }
    }

    @Override
    protected Object[] getMessage() {
      Object[] message = {
              "Food Name:", foodNameField,
              "Number of units:", numUnitsField,
              "Servings per container:", servingsPerContainer,
              "Serving size:", servingSize,
              unitField
      };
      return message;
    }

    @Override
    protected String getTitle() {
      return "Add Food Item";
    }

    @Override
    protected void processOutput() {
      String foodName = foodNameField.getText();
      double foodQuantity = Double.parseDouble(numUnitsField.getValue().toString());
      double itemServingSize = Double.parseDouble(servingSize.getValue().toString());
      double servingsPC = Double.parseDouble(servingsPerContainer.getValue().toString());

      Ingredient newItem = new Ingredient(foodName, foodQuantity, itemServingSize, servingsPC,
              unitField.getItemAt(unitField.getSelectedIndex()));
      user.getStock().editFoodItem(item, newItem);
      PantryList.setListData(user.getStock().getFoodNamesWithQuantity().toArray());
      editStockButton.setVisible(false);
    }
  }
}
