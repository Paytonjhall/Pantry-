package View;

import Pantry.Ingredient;
import Recipe.Recipe;
import User.JsonConverter;
import User.User;
import Utils.Colors;
import View.ActionListeners.FoodItemAction;
import View.ActionListeners.RecipeAction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame{
  final private JsonConverter jsonConverter = new JsonConverter();
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
  private JPanel BreakfastRecipesPanel;
  private JList BreakfastList;
  private JTextField SearchBox;

  int ALL_RECIPES = 0;
  int MAKEABLE_RECIPES = 1;
  int BREAKFAST_TAB = 2;
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

    // Set button colors:
    addShoppingListItemButton.setBackground(Colors.gray);
    clearShoppingListButton.setBackground(Colors.gray);
    addRecipeButton.setBackground(Colors.gray);
    logoutButton.setBackground(Colors.gray);
    saveButton.setBackground(Colors.gray);
    editStockButton.setBackground(Colors.gray);
    addToPantryButton.setBackground(Colors.gray);
    deleteRecipeButton.setBackground(Colors.gray);
    editRecipeButton.setBackground(Colors.gray);
    makeRecipeButton.setBackground(Colors.gray);
    addRecipeButtonMakeable.setBackground(Colors.gray);
    editShoppingListButton.setBackground(Colors.gray);
    addToPantryButton.setBackground(Colors.gray);
    addRecipeButtonMakeable.setBackground(Colors.gray);


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

    // edit button for shopping list
    editShoppingListButton.addMouseListener(new EditShoppingListAction());

    // add button for pantry
    addToPantryButton.addMouseListener(new AddToPantryAction());

    // Edit button for pantry
    editStockButton.addMouseListener(new EditStockAction());

    // add button for shopping list
    addShoppingListItemButton.addMouseListener(new AddShoppingListItemAction());


    // Clear shopping list button
    clearShoppingListButton.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseClicked(java.awt.event.MouseEvent evt) {
        user.getStock().clearShoppingList();
        ShoppingListList.setListData(user.getStock().getShoppingListNamesWithQuantity().toArray());

        saveFile();
      }

      public void mouseEntered(java.awt.event.MouseEvent evt) {
        clearShoppingListButton.setBackground(Colors.red);
      }
      public void mouseExited(java.awt.event.MouseEvent evt) {
        clearShoppingListButton.setBackground(Colors.gray);
      }
    });


    // TODO: ADD A DELETE PANTRY ITEM BUTTON
    // TODO: WHEN ADDING A SHOPPING LIST ITEM, IF IT'S THE SAME MAKE SURE IT ADDS IT AS SUCH

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
    saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
              try{
                jsonConverter.addUserToFile(user);
              } catch (Exception ex){
                System.out.println("Error saving user: " + ex);
              }
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            saveButton.setBackground(Colors.green);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            saveButton.setBackground(Colors.gray);
            }
        });

    //Logout Button --> Complete: Done
    logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
              LoginView loginView = new LoginView();
              dispose();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            logoutButton.setBackground(Colors.green);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            logoutButton.setBackground(Colors.gray);
            }
        });

    //add recipe button --> Complete: Done
    addRecipeButton.addMouseListener(new AddRecipeAction(addRecipeButton));


    //add recipe on make-able recipe tab
    addRecipeButtonMakeable.addMouseListener(new AddRecipeAction(addRecipeButtonMakeable));


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
            deleteRecipeButton.setBackground(Colors.red);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            deleteRecipeButton.setBackground(Colors.gray);
            }
        });

    //Edit recipe Button --> Complete: Done
    editRecipeButton.addMouseListener(new EditRecipeAction(editRecipeButton));

   
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
              makeableRecipesList.setListData(user.getStringsUserCanMake().toArray());
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
            makeRecipeButton.setBackground(Colors.green);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            makeRecipeButton.setBackground(Colors.gray);
            }
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

  public void saveFile() {
    try {
      jsonConverter.addUserToFile(user);
    } catch (Exception ex) {
      System.out.println("Error saving user: " + ex);
    }
  }


  /*
  Inner classes that inherit from FoodItemAction to implement the mouseListener events
   */
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

      saveFile();
    }

    @Override
    protected void setButtonColor(Color color) {
      editShoppingListButton.setBackground(color);
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

      saveFile();
    }

    @Override
    protected void setButtonColor(Color color) {
      addToPantryButton.setBackground(color);
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

      saveFile();
    }

    @Override
    protected void setButtonColor(Color color) {
      editStockButton.setBackground(color);
    }


  }

  private class AddShoppingListItemAction extends FoodItemAction {

    JSpinner sizeOneUnit;

    @Override
    protected void createAdditionalFields() {
      sizeOneUnit = new JSpinner(new SpinnerNumberModel(1, 0, 2000, 0.25));

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
              "Size of 1 unit:", sizeOneUnit,
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
      Ingredient foodItem = new Ingredient(foodNameField.getText(), (Integer) numUnitsField.getValue(),
              Double.parseDouble(sizeOneUnit.getValue().toString()), unitField.getItemAt(unitField.getSelectedIndex()));
      user.getStock().addShoppingListItem(foodItem);
      ShoppingListList.setListData(user.getStock().getShoppingListNamesWithQuantity().toArray());

      saveFile();
    }

    @Override
    protected void setButtonColor(Color color) {
      addShoppingListItemButton.setBackground(color);
    }


  }

  private class AddRecipeAction extends RecipeAction {

    JButton button;

    public AddRecipeAction(JButton button) {
      super(user);
      this.button = button;
    }

    @Override
    protected void updateInterfaceList() {
      RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());

      saveFile();
    }

    @Override
    protected void setButtonColor(Color color) {
      button.setBackground(color);
    }

  }

  private class EditRecipeAction extends RecipeAction {

    JButton button;
    Recipe recipe;

    public EditRecipeAction(JButton button) {
      super(user);
      this.button = button;
    }

    @Override
    protected void initializeFields() {
      if(MAKEABLE_RECIPES == recipesTabPanel.getSelectedIndex()) {
        recipe = user.getRecipesUserCanMake().get(makeableRecipesList.getSelectedIndex());
      } else {
        recipe = user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex());
      }
      unitField.setSelectedIndex(0);
      recipeName.setText(recipe.getName());
      recipeInstructions.setText(recipe.getInstructions());
      cookTime.setText(recipe.getTime());

      uploadPhoto.setVisible(false);
    }

    @Override
    protected Object[] getMessage() {
      Object[] message = {
              "Recipe Name:", recipeName,
              "Recipe Instructions:", recipeInstructions,
              "Recipe Ingredients: (please re-enter all ingredients) ", recipeIngredients,
              "Ingredient Quantity:", ingredientSize, unitField,
              addIngredientButton,
              "Cook Time:", cookTime

      };
      return message;
    }

    @Override
    protected void handleOutput() {
      int option = JOptionPane.showConfirmDialog(null, getMessage(), "Edit Recipe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, getLogo());

      if (option == JOptionPane.OK_OPTION) {
        Recipe newRecipe = new Recipe(recipeName.getText(), recipeInstructions.getText(), cookTime.getText(), ingredients);
        user.getRecipeBook().editRecipe(recipe, newRecipe);
        RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());

        updateInterfaceList();

        saveFile();
      }
    }

    @Override
    protected void updateInterfaceList() {
      RecipeLabel.setText("Recipe: ");
      RecipeLabelInfo.setText("Instructions: ");
      RecipeCookTime.setText("Cook Time: ");
      RecipeIngredientList.setListData(new String[0]);
      deleteRecipeButton.setVisible(false);
      editRecipeButton.setVisible(false);
    }

    @Override
    protected void setButtonColor(Color color) {
      button.setBackground(color);
    }
  }
}
