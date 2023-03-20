package View;
import Pantry.Ingredient;
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
  private JPanel BreakfastRecipesPanel;
  private JList BreakfastList;
  private JTextField SearchBox;

  int ALL_RECIPES = 0;
  int MAKEABLE_RECIPES = 1;
  int BREAKFAST_TAB = 2;
  //User user;

  Color green = new Color(133, 204, 160);
  Color red = new Color(195, 53, 76);
  Color yellow = new Color(209, 195, 126);
  Color gray = new Color(200, 200, 200);

  //Load main view
  public MainView( User user )  {
    //this.user = user;
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

    // Set button colors:
    addShoppingListItemButton.setBackground(gray);
    clearShoppingListButton.setBackground(gray);
    addRecipeButton.setBackground(gray);
    logoutButton.setBackground(gray);
    saveButton.setBackground(gray);
    editStockButton.setBackground(gray);
    addToPantryButton.setBackground(gray);
    deleteRecipeButton.setBackground(gray);
    editRecipeButton.setBackground(gray);
    makeRecipeButton.setBackground(gray);
    addRecipeButtonMakeable.setBackground(gray);
    editShoppingListButton.setBackground(gray);
    addToPantryButton.setBackground(gray);
    addRecipeButtonMakeable.setBackground(gray);


    // When you click on an item in your pantry, the edit stock button should show up
    PantryList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        editStockButton.setVisible(true);
      }
    });

    // When you click on an item in your shopping list, the edit shopping list button should show up
    ShoppingListList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {editShoppingListButton.setVisible(true);
      }
    });

    editShoppingListButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        // add dialogue box for editing food items
        Ingredient item = user.getStock().getShoppingList().getItem(ShoppingListList.getSelectedIndex());
        JTextField foodNameField = new JTextField();
        foodNameField.setSize(150, 20); //Increasing one of the field sizes makes the dialog box bigger
        foodNameField.setPreferredSize(new Dimension(150, 20));
        JTextField foodQuantityField = new JTextField();
        SpinnerModel model = new SpinnerNumberModel(1, 1, 1000, 1);
        JSpinner numUnitsField = new JSpinner(model);
        SpinnerModel sizeModel = new SpinnerNumberModel(1, 0, 2000, 0.25);
        JSpinner sizeOneUnitField = new JSpinner(sizeModel);
        String possibleUnits[] = {
                "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER", "FLUID OUNCE"
        };
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
          if (unitType.toUpperCase().equals(possibleUnits[i])) {
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
          Object numUnits = numUnitsField.getValue();
          Object sizeOneUnit = sizeOneUnitField.getValue();
          foodQuantity = Double.parseDouble(numUnits.toString());
          unitSize = Double.parseDouble(sizeOneUnit.toString());
          Ingredient newItem = new Ingredient(foodName, foodQuantity, unitSize, unitField.getItemAt(unitField.getSelectedIndex()));
          user.getStock().editShoppingListItem(item, newItem);
          ShoppingListList.setListData(user.getStock().getShoppingListNamesWithQuantity().toArray());
          editShoppingListButton.setVisible(false);
        }
      }

       public void mouseEntered(java.awt.event.MouseEvent evt) {
         editShoppingListButton.setBackground(yellow);
       }

       public void mouseExited(java.awt.event.MouseEvent evt) {
        editShoppingListButton.setBackground(gray);
      }

    });

    addToPantryButton.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseClicked(java.awt.event.MouseEvent evt) {
        // add dialogue box for adding food items
        JTextField foodNameField = new JTextField();
        foodNameField.setSize(150, 20); //Increasing one of the field sizes makes the dialog box bigger
        foodNameField.setPreferredSize(new Dimension(150, 20));
        JTextField foodQuantityField = new JTextField();
        SpinnerModel model = new SpinnerNumberModel(1, 1, 1000, 1);
        JSpinner numUnits = new JSpinner(model);

        SpinnerModel sizeModel = new SpinnerNumberModel(1, 0, 2000, 0.01);
        JSpinner servingSize = new JSpinner(sizeModel);
        SpinnerModel spcModel = new SpinnerNumberModel(1, 0, 2000, 0.01);
        JSpinner servingsPerContainer = new JSpinner(spcModel);
        String possibleUnits[] = {
                "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER", "FLUID OUNCE"
        }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST

        JComboBox<String> unit = new JComboBox<>(possibleUnits);
        unit.setSelectedIndex(0);

        // Add metric for food measurement here;
        Object[] message = {
                "Food Name:", foodNameField,
                "Number of units:", numUnits,
                "Servings Per Container:", servingsPerContainer,
                "Serving Size:", servingSize,
                unit
        };
        int output = JOptionPane.showConfirmDialog(null, message, "Add Food Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, getLogo());
        if (output == JOptionPane.OK_OPTION) {
          Double numUnitsValue = Double.parseDouble(numUnits.getValue().toString());
          Double servingSizeVal = Double.parseDouble(servingSize.getValue().toString());
          Double spcVal = Double.parseDouble(servingsPerContainer.getValue().toString());

          Ingredient foodItem = new Ingredient(foodNameField.getText(), numUnitsValue,
                  servingSizeVal, spcVal, unit.getItemAt(unit.getSelectedIndex()));
          user.addToStock(foodItem);
          PantryList.setListData(user.getStock().getFoodNamesWithQuantity().toArray());
        }
      }

      public void mouseEntered(java.awt.event.MouseEvent evt) {
        addToPantryButton.setBackground(green);
      }

      public void mouseExited(java.awt.event.MouseEvent evt) {
        addToPantryButton.setBackground(gray);
      }
    });

    // edit button for pantry
    editStockButton.addMouseListener(new java.awt.event.MouseAdapter(){

        public void mouseClicked(java.awt.event.MouseEvent evt) {
          // add dialogue box for editing food items
          Ingredient item = user.getStock().getStock().get(PantryList.getSelectedIndex());
          JTextField foodNameField = new JTextField();
          foodNameField.setSize(150, 20); //Increasing one of the field sizes makes the dialog box bigger
          foodNameField.setPreferredSize(new Dimension(150, 20));
          JTextField foodQuantityField = new JTextField();
          SpinnerModel model = new SpinnerNumberModel(1, 1, 1000, 1);
          JSpinner numUnitsField = new JSpinner(model);
          SpinnerModel sizeModel = new SpinnerNumberModel(1, 0, 2000, 0.01);
          JSpinner servingSize = new JSpinner(sizeModel);
          SpinnerModel spcModel = new SpinnerNumberModel(1, 0, 2000, 0.01);
          JSpinner servingsPerContainer = new JSpinner(spcModel);
          String possibleUnits[] = {
                  "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER", "FLUID OUNCE"
          }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST

          JComboBox<String> unitField = new JComboBox<>(possibleUnits);
          unitField.setSelectedIndex(0);
          String foodName = item.getName();
          foodNameField.setText(foodName);
          double foodQuantity = item.getQuantity();
          numUnitsField.setValue(foodQuantity);
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

          // Add metric for food measurement here;
          Object[] message = {
                  "Food Name:", foodNameField,
                  "Number of units:", numUnitsField,
                  "Servings per container:", servingsPerContainer,
                  "Serving size:", servingSize,
                  unitField
          };
          int output = JOptionPane.showConfirmDialog(null, message, "Add Food Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, getLogo());
          if (output == JOptionPane.OK_OPTION) {
            foodName = foodNameField.getText();
            foodQuantity = Double.parseDouble(numUnitsField.getValue().toString());
            double itemServingSize = Double.parseDouble(servingSize.getValue().toString());
            double servingsPC = Double.parseDouble(servingsPerContainer.getValue().toString());

            Ingredient newItem = new Ingredient(foodName, foodQuantity, itemServingSize, servingsPC,
                    unitField.getItemAt(unitField.getSelectedIndex()));
            user.getStock().editFoodItem(item, newItem);
            PantryList.setListData(user.getStock().getFoodNamesWithQuantity().toArray());
            editStockButton.setVisible(false);

          }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
          editStockButton.setBackground(yellow);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
          editStockButton.setBackground(gray);
        }
    });

    // add button for shopping list
    addShoppingListItemButton.addMouseListener(new java.awt.event.MouseAdapter() {
                                                 public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                   // add dialogue box for adding food items
                                                   JTextField foodNameField = new JTextField();
                                                   foodNameField.setSize(150, 20); //Increasing one of the field sizes makes the dialog box bigger
                                                   foodNameField.setPreferredSize(new Dimension(150, 20));
                                                   JTextField foodQuantityField = new JTextField();
                                                   SpinnerModel model = new SpinnerNumberModel(1, 1, 1000, 1);
                                                   JSpinner numUnits = new JSpinner(model);

                                                   SpinnerModel sizeModel = new SpinnerNumberModel(1, 0, 2000, 0.25);
                                                   JSpinner sizeOneUnit = new JSpinner(sizeModel);
                                                   String possibleUnits[] = {
                                                           "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER", "FLUID OUNCE"
                                                   };
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
                                                     Ingredient foodItem = new Ingredient(foodNameField.getText(), (Integer) numUnits.getValue(),
                                                             (double) sizeOneUnit.getValue(), unit.getItemAt(unit.getSelectedIndex()));
                                                     user.getStock().addShoppingListItem(foodItem);
                                                     ShoppingListList.setListData(user.getStock().getShoppingListNamesWithQuantity().toArray());
                                                   }
                                                 }

                                                 public void mouseEntered(java.awt.event.MouseEvent evt) {
                                                   addShoppingListItemButton.setBackground(green);
                                                 }

                                                 public void mouseExited(java.awt.event.MouseEvent evt) {
                                                   addShoppingListItemButton.setBackground(gray);
                                                 }
                                               });

    clearShoppingListButton.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseClicked(java.awt.event.MouseEvent evt) {
        user.getStock().clearShoppingList();
        ShoppingListList.setListData(user.getStock().getShoppingListNamesWithQuantity().toArray());
      }

      public void mouseEntered(java.awt.event.MouseEvent evt) {
        clearShoppingListButton.setBackground(red);
      }
      public void mouseExited(java.awt.event.MouseEvent evt) {
        clearShoppingListButton.setBackground(gray);
      }
    });

    recipesTabPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println(recipesTabPanel.getSelectedIndex());
        if(ALL_RECIPES == recipesTabPanel.getSelectedIndex()){
          RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
        } else if(MAKEABLE_RECIPES == recipesTabPanel.getSelectedIndex()){
          makeableRecipesText.setText("Makeable Recipes");
          makeableRecipesList.setVisible(true);
          makeableRecipesList.setListData(user.getStringsUserCanMake().toArray());
          makeRecipeButton.setVisible(true);
        } else if (BREAKFAST_TAB == recipesTabPanel.getSelectedIndex()) {
          BreakfastList.setListData(user.getRecipeBook().getBreakfastRecipes().toArray());
          //TODO: add logic to make recipes clickable
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
    saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
              try{
                jsonConverter.addUserToFile(user);
              } catch (Exception ex){
                System.out.println("Error saving user: " + ex);
              }
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            saveButton.setBackground(green);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            saveButton.setBackground(gray);
            }
        });

    //Logout Button --> Complete: Done
    logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
              LoginView loginView = new LoginView();
              dispose();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            logoutButton.setBackground(green);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            logoutButton.setBackground(gray);
            }
        });

    //Add Ingredient Button --> Complete: Done
    addRecipeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
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
              JCheckBox breakfastTag = new JCheckBox("Breakfast");
              JCheckBox lunchTag = new JCheckBox("Lunch");
              JCheckBox dinnerTag = new JCheckBox("Dinner");
              JCheckBox mainCourseTag = new JCheckBox("Main Course");
              JCheckBox sideDishTag = new JCheckBox("SIde Dish");
              JCheckBox snackTag = new JCheckBox("Snack");
              JCheckBox dessertTag = new JCheckBox("Dessert");
              JCheckBox vegetarianTag = new JCheckBox("Vegetarian");
              JCheckBox glutenFreeTag = new JCheckBox("Gluten Free");
              JCheckBox meatTag = new JCheckBox("Meat");
              
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
                finalRecipe.setBreakfastTag(breakfastTag.isSelected());
                finalRecipe.setLunchTag(lunchTag.isSelected());
                finalRecipe.setDinnerTag(dinnerTag.isSelected());
                finalRecipe.setMainCourseTag(mainCourseTag.isSelected());
                finalRecipe.setSideDishTag(sideDishTag.isSelected());
                finalRecipe.setSnackTag(snackTag.isSelected());
                finalRecipe.setDessertTag(dessertTag.isSelected());
                finalRecipe.setVegetarianTag(vegetarianTag.isSelected());
                finalRecipe.setGlutenFreeTag(glutenFreeTag.isSelected());
                finalRecipe.setMeatTag(meatTag.isSelected());

                user.getRecipeBook().addRecipe(finalRecipe);
                RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
              }
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            addRecipeButton.setBackground(green);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            addRecipeButton.setBackground(gray);
            }
        });

    //Add Ingredient Button --> Complete: Done
    addRecipeButtonMakeable.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
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
                "Upload Photo:", uploadPhoto,
                "Tags:", breakfastTag, lunchTag, dinnerTag, mainCourseTag, sideDishTag, snackTag, dessertTag, vegetarianTag,
                    glutenFreeTag, meatTag
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
      }
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        addRecipeButtonMakeable.setBackground(green);
      }
      public void mouseExited(java.awt.event.MouseEvent evt) {
        addRecipeButtonMakeable.setBackground(gray);
      }
    });

    //Delete Recipe Button --> Complete: Done
    // TODO: Make sure this handles deleting recipes when the user is on the makeable recipes tab
    deleteRecipeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
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
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            deleteRecipeButton.setBackground(red);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            deleteRecipeButton.setBackground(gray);
            }
        });

    //Edit recipe Button --> Complete: Done
    editRecipeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
      Recipe recipe;
      if(MAKEABLE_RECIPES == recipesTabPanel.getSelectedIndex()) {
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
    }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            editRecipeButton.setBackground(yellow);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            editRecipeButton.setBackground(gray);
            }

    });


    makeRecipeButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
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
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
            makeRecipeButton.setBackground(green);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            makeRecipeButton.setBackground(gray);
            }
        });

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
    Image dimg = getImage("Pantry-/images/pantryLogoNoBackground.png").getScaledInstance(135, 135, Image.SCALE_SMOOTH);
    return new ImageIcon(dimg);
  }
}
