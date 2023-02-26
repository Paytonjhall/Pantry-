package View;
import Pantry.FoodItem;
import Recipe.Recipe;
import User.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame{
  private JsonConverter jsonConverter = new JsonConverter();
  private JLabel ProjectName;
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
  private JTextField SearchBox;
  //User user;

  //Load main view
  public MainView( User user )  {
    //this.user = user;
    //Create frame
    setContentPane(MainViewPanel);
    setTitle("Pantry++");
    setSize(1200, 550);
    RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
    PantryList.setListData(user.getStock().getFoodNamesWithQuantity().toArray());
    System.out.println(user.getStock().getFoodNamesWithQuantity());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setIconImage(Toolkit.getDefaultToolkit().getImage("images/pantryLogoBackground.png"));
    //Set buttons to invisible, only visible when a recipe is selected
    RecipePhoto.setIcon(new ImageIcon("src/Assets/no-images.png"));
    RecipePhoto.setVisible(false);
    deleteRecipeButton.setVisible(false);
    editRecipeButton.setVisible(false);
    makeRecipeButton.setVisible(false);
    editStockButton.setVisible(false);
//    deleteStockButton.setVisible(false);
    setVisible(true);
    RecipeLabelInfo.setLineWrap(true);

    // When you click on an item in your pantry, the edit stock button should show up
    PantryList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        editStockButton.setVisible(true);
      }
    });

    // edit button for pantry
    editStockButton.addActionListener(e -> {
      // add dialogue box for editing food items
      FoodItem item = user.getStock().getStock().get(PantryList.getSelectedIndex());
      JTextField foodNameField = new JTextField();
      foodNameField.setSize(150, 20); //Increasing one of the field sizes makes the dialog box bigger
      foodNameField.setPreferredSize(new Dimension(150, 20));
      JTextField foodQuantityField = new JTextField();
      SpinnerModel model = new SpinnerNumberModel(1, 1, 1000, 1);
      JSpinner numUnitsField = new JSpinner(model);
      SpinnerModel sizeModel = new SpinnerNumberModel(1, 0, 2000, 0.01);
      JSpinner sizeOneUnitField = new JSpinner(sizeModel);
      String possibleUnits[] = {
              "", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER", "FLUID OUNCE"
      }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST

      JComboBox<String> unitField = new JComboBox<>(possibleUnits);
      unitField.setSelectedIndex(0);
      String foodName = item.getName();
      foodNameField.setText(foodName);
      double foodQuantity = item.getQuantity();
      numUnitsField.setValue(foodQuantity);
      double unitSize = 1; // Because we are converting all to one size, we forget what the unit size is, so set to 1
      sizeOneUnitField.setValue(unitSize);

      String unitType = item.getUnitType();
      int indexOfUnit = -1;
      for (int i = 0; i < possibleUnits.length; i++) {
        if (unitType.toUpperCase() == possibleUnits[i]) {
          indexOfUnit = i;
        }
      }
      if (indexOfUnit > -1) {
        unitField.setSelectedIndex(indexOfUnit);
      } else {
        unitField.setSelectedIndex(0);
      }

      // Add metric for food measurement here;
      Object[] message = {
              "Food Name:", foodNameField,
              "Number of units:", numUnitsField,
              "Size of 1 unit:", sizeOneUnitField,
              unitField
      };
      int output = JOptionPane.showConfirmDialog(null, message, "Add Food Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, getLogo());
      if (output == JOptionPane.OK_OPTION) {
        foodName = foodNameField.getText();
        foodQuantity = (Integer) numUnitsField.getValue();
        unitSize = (Double) sizeOneUnitField.getValue();
        FoodItem newItem = new FoodItem(foodName, foodQuantity, unitSize, unitField.getItemAt(unitField.getSelectedIndex()));
        user.getStock().editFoodItem(item, newItem);
        PantryList.setListData(user.getStock().getFoodNamesWithQuantity().toArray());
        editStockButton.setVisible(false);

      }
    });

    // add button for pantry
    addToPantryButton.addActionListener(e -> {
      // add dialogue box for adding food items
      JTextField foodNameField = new JTextField();
      foodNameField.setSize(150, 20); //Increasing one of the field sizes makes the dialog box bigger
      foodNameField.setPreferredSize(new Dimension(150, 20));
      JTextField foodQuantityField = new JTextField();
      SpinnerModel model = new SpinnerNumberModel(1, 1, 1000, 0.25);
      JSpinner numUnits = new JSpinner(model);

      SpinnerModel sizeModel = new SpinnerNumberModel(1, 0, 2000, 0.01);
      JSpinner sizeOneUnit = new JSpinner(sizeModel);
      String possibleUnits[] = {
              "", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER", "FLUID OUNCE"
      }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST

      JComboBox<String> unit = new JComboBox<>(possibleUnits);
      unit.setSelectedIndex(0);

      // Add metric for food measurement here;
      Object[] message = {
              "Food Name:", foodNameField,
              "Number of units:", numUnits,
              "Size of 1 unit:", sizeOneUnit,
              unit
      };
      int output = JOptionPane.showConfirmDialog(null, message, "Add Food Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, getLogo());
      if (output == JOptionPane.OK_OPTION) {
            FoodItem foodItem = new FoodItem(foodNameField.getText(), (Double) numUnits.getValue(),
                    (Double) sizeOneUnit.getValue(), unit.getItemAt(unit.getSelectedIndex()));
            user.addToStock(foodItem);
            PantryList.setListData(user.getStock().getFoodNamesWithQuantity().toArray());

        }
    });

    recipesTabPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int allRecipes = 0;
        int makeableRecipes = 1;
        System.out.println(recipesTabPanel.getSelectedIndex());
        if(allRecipes == recipesTabPanel.getSelectedIndex()){
          RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
        } else if(makeableRecipes == recipesTabPanel.getSelectedIndex()){
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
        if(recipeSelected.getImage() != null){
          Image dimg = getImage(recipeSelected.getImage()).getScaledInstance(150, 150, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          RecipePhoto.setIcon(imageIcon);
          //RecipePhoto.setIcon(new ImageIcon(recipeSelected.getImage()));
        }
        else{
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
        if(recipeSelected.getImage() != null){
          Image dimg = getImage(recipeSelected.getImage()).getScaledInstance(150, 150, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          RecipePhoto.setIcon(imageIcon);
          //RecipePhoto.setIcon(new ImageIcon(recipeSelected.getImage()));
        }
        else{
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
      try{
        jsonConverter.addUserToFile(user);
      } catch (Exception ex){
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
                "", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER",
                "TABLESPOON", "TEASPOON", "FLUID OUNCE"
        }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST

        JComboBox<String> unit = new JComboBox<>(possibleUnits);
        unit.setSelectedIndex(3);
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
        List<FoodItem> ingredients = new ArrayList<>();

        addIngredientButton.addActionListener(e1 -> {
          ingredients.add(new FoodItem(recipeIngredients.getText(), (Double) ingredientSize.getValue(), unit.getItemAt(unit.getSelectedIndex())));
          recipeIngredients.setText("");
          ingredientSize.setValue(1);
          unit.setSelectedIndex(3);
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
            } catch(IOException ex){
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
              "", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER",
              "TABLESPOON", "TEASPOON", "FLUID OUNCE"
      }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST

      JComboBox<String> unit = new JComboBox<>(possibleUnits);
      unit.setSelectedIndex(3);
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
      List<FoodItem> ingredients = new ArrayList<>();

      addIngredientButton.addActionListener(e1 -> {
        ingredients.add(new FoodItem(recipeIngredients.getText(), (Double) ingredientSize.getValue(), unit.getItemAt(unit.getSelectedIndex())));
        recipeIngredients.setText("");
        ingredientSize.setValue(1);
        unit.setSelectedIndex(3);
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
          } catch(IOException ex){
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
    deleteRecipeButton.addActionListener(e -> {
      if (user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()).getImage() != null) {
        File file=new File(user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()).getImage());
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
      Recipe recipe = user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex());
      JTextField recipeName = new JTextField(recipe.getName());
      JTextField recipeInstructions = new JTextField(recipe.getInstructions());
      JTextField recipeIngredients = new JTextField();
      JTextField cookTime = new JTextField(recipe.getTime());
      String possibleUnits[] = {
              "", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER",
              "TABLESPOON", "TEASPOON", "FLUID OUNCE"
      };
      JComboBox<String> unit = new JComboBox<>(possibleUnits);
      unit.setSelectedIndex(3);
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

      List<FoodItem> ingredients = new ArrayList<>();

      addIngredientButton.addActionListener(e1 -> {
        ingredients.add(new FoodItem(recipeIngredients.getText(), (Double) ingredientSize.getValue(), unit.getItemAt(unit.getSelectedIndex())));
        recipeIngredients.setText("");
        ingredientSize.setValue(1);
        unit.setSelectedIndex(3);
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
    //Create logo
  }

  private Image getImage(String iconPath) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(iconPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return img;
  }

  private ImageIcon getLogo() {
    Image dimg = getImage("images/pantryLogoNoBackground.png").getScaledInstance(135, 135, Image.SCALE_SMOOTH);
    return new ImageIcon(dimg);
  }
}
