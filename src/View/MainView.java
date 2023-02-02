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
  private JLabel RecipeLabelInfo;
  private JList RecipeIngredientList;
  private JButton deleteRecipeButton;
  private JButton editRecipeButton;

  //Load main view
  public MainView( User user ) {
    //Create frame
    setContentPane(MainViewPanel);
    setTitle("Pantry ++");
    setSize(1200, 550);
    RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Set buttons to invisible, only visible when a recipe is selected
    deleteRecipeButton.setVisible(false);
    editRecipeButton.setVisible(false);
    setVisible(true);

    //Select Recipe action listener
    RecipeList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        JLabel recipe = new JLabel();
        recipe.setText(user.getRecipeBook().getRecipeStringList().get(RecipeList.getSelectedIndex()));
        RecipeLabel.setText("Recipe: " + recipe.getText());
        RecipeLabelInfo.setText("Instructions: \n" + user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()).getInstructions());
        for(int i = 0; i < user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()).getIngredients().size(); i++){
          List<String> ingredientList = user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()).getIngredientsNames();
          RecipeIngredientList.setListData(ingredientList.toArray());
        }
        //RecipePanel.add(recipe);
        //RecipePanel.revalidate();
        deleteRecipeButton.setVisible(true);
        editRecipeButton.setVisible(true);
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
        JSlider ingredientCount = new JSlider();
        ingredientCount.setMinimum(1);
        ingredientCount.setMaximum(10);
        ingredientCount.setMajorTickSpacing(1);
        ingredientCount.setPaintTicks(true);
        ingredientCount.setPaintLabels(true);
        JButton addIngredientButton = new JButton("Add Ingredient");
        Object[] message = {
            "Recipe Name:", recipeName,
            "Recipe Instructions:", recipeInstructions,
            "Recipe Ingredients:", recipeIngredients,
            "Number of Ingredients:", ingredientCount,
                addIngredientButton,
            "Cook Time:", cookTime

        };
        List<Ingredient> ingredients = new ArrayList<>();

        addIngredientButton.addActionListener(e1 -> {
          ingredients.add(new Ingredient(recipeIngredients.getText(), ingredientCount.getValue()));
          recipeIngredients.setText("");
          ingredientCount.setValue(1);
        });

      Image dimg = getImage("src/Assets/leaf.png").getScaledInstance(75, 75, Image.SCALE_SMOOTH);
      ImageIcon imageIcon = new ImageIcon(dimg);

        int option = JOptionPane.showConfirmDialog(null, message, "Add Recipe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon);
        if (option == JOptionPane.OK_OPTION) {
//          Recipe recipe = new Recipe(recipeName.getText(), recipeInstructions.getText(), recipeIngredients.getText());
//          user.getRecipeBook().addRecipe(recipe);
//          RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
            System.out.println("Recipe Added");
            System.out.println(recipeName.getText());
            System.out.println(recipeInstructions.getText());
            System.out.println(recipeIngredients.getText());
            Recipe recipe = new Recipe(recipeName.getText(), recipeInstructions.getText(), cookTime.getText(), ingredients);
            user.getRecipeBook().addRecipe(recipe);
            RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());

        }

    });

    //Delete Recipe Button --> Complete: Done
    deleteRecipeButton.addActionListener(e -> {
      user.getRecipeBook().removeRecipe(user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()));
      RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
      RecipeLabel.setText("Recipe: ");
      RecipeLabelInfo.setText("Instructions: ");
      RecipeIngredientList.setListData(new String[0]);
      deleteRecipeButton.setVisible(false);
      editRecipeButton.setVisible(false);
    });

    //Edit recipe Button --> Complete: Done
    editRecipeButton.addActionListener(e -> {
      Recipe recipe = user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex());
      JTextField recipeName = new JTextField(recipe.getName());
      JTextField recipeInstructions = new JTextField(recipe.getInstructions());
      JTextField recipeIngredients = new JTextField();
      JTextField cookTime = new JTextField(recipe.getTime());
      JSlider ingredientCount = new JSlider();
      ingredientCount.setMinimum(1);
      ingredientCount.setMaximum(10);
      ingredientCount.setMajorTickSpacing(1);
      ingredientCount.setPaintTicks(true);
      ingredientCount.setPaintLabels(true);


      JButton addIngredientButton = new JButton("Add Ingredient");
      Object[] message = {
            "Recipe Name:", recipeName,
            "Recipe Instructions:", recipeInstructions,
            "Recipe Ingredients: (please re-enter all ingredients) ", recipeIngredients,
            "Number of Ingredients:", ingredientCount,
              addIngredientButton,
              "Cook Time:", cookTime

      };
      List<Ingredient> ingredients = new ArrayList<>();

      addIngredientButton.addActionListener(e1 -> {
        ingredients.add(new Ingredient(recipeIngredients.getText(), ingredientCount.getValue()));
        recipeIngredients.setText("");
        ingredientCount.setValue(1);
      });

      Image dimg = getImage("src/Assets/leaf.png").getScaledInstance(75, 75, Image.SCALE_SMOOTH);
      ImageIcon imageIcon = new ImageIcon(dimg);

      int option = JOptionPane.showConfirmDialog(null, message, "Edit Recipe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon);

      if (option == JOptionPane.OK_OPTION) {
        Recipe newRecipe = new Recipe(recipeName.getText(), recipeInstructions.getText(), cookTime.getText(), ingredients);
        user.getRecipeBook().editRecipe(recipe, newRecipe);
        RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
        RecipeLabel.setText("Recipe: ");
        RecipeLabelInfo.setText("Instructions: ");
        RecipeIngredientList.setListData(new String[0]);
        deleteRecipeButton.setVisible(false);
        editRecipeButton.setVisible(false);
      }
    });
  }

  //Create logo
  private Image getImage(String iconPath) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(iconPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return img;
  }
}
