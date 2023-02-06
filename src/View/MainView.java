package View;
import Pantry.Ingredient;
import Recipe.Recipe;
import User.*;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
  private JPanel FoodPanel;
  private JLabel RecipePhoto;
  private JLabel RecipeCookTime;
  private JTextField SearchBox;

  //Load main view
  public MainView( User user ) {
    //Create frame
    setContentPane(MainViewPanel);
    setTitle("Pantry ++");
    setSize(1200, 550);
    RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setIconImage(Toolkit.getDefaultToolkit().getImage("src/Assets/leaf.png"));
    //Set buttons to invisible, only visible when a recipe is selected
    RecipePhoto.setIcon(new ImageIcon("src/Assets/no-images.png"));
    RecipePhoto.setVisible(false);
    deleteRecipeButton.setVisible(false);
    editRecipeButton.setVisible(false);
    setVisible(true);

    //Select Recipe action listener
    RecipeList.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        JLabel recipe = new JLabel();
        Recipe recipeSelected = user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex());
        recipe.setText(recipeSelected.getName());
        RecipeLabel.setText("Recipe: " + recipeSelected.getName());
        RecipeLabelInfo.setText("Instructions: \n" + recipeSelected.getInstructions());
        RecipeCookTime.setText("Cook Time: " + recipeSelected.getTime());
        for(int i = 0; i < recipeSelected.getIngredients().size(); i++){
          List<String> ingredientList = user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()).getIngredientsNames();
          RecipeIngredientList.setListData(ingredientList.toArray());
        }
        if(recipeSelected.getImage() != null){
          RecipePhoto.setIcon(new ImageIcon(recipeSelected.getImage()));
        }
        else{
          RecipePhoto.setIcon(new ImageIcon("src/Assets/no-images.png"));
          RecipePhoto.setToolTipText("No image found");
        }
        RecipePhoto.setVisible(true);
        deleteRecipeButton.setVisible(true);
        editRecipeButton.setVisible(true);
        RecipePanel.repaint();
      }
    });

//    SearchBox.addActionListener(new java.awt.event.ActionListener() {
//      public void actionPerformed(java.awt.event.ActionEvent evt) {
//        if (SearchBox.getText() != null) {
//          String search=SearchBox.getText();
//          List<Recipe> searchList=new ArrayList<Recipe>();
//          for (int i=0; i < user.getRecipeBook().getRecipeList().size(); i++) {
//            if (user.getRecipeBook().getRecipeList().get(i).getName().contains(search)) {
//              searchList.add(user.getRecipeBook().getRecipeList().get(i));
//            }
//          }
//          RecipeList.setListData(searchList.toArray());
//        }
//      }
//    });

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
        Recipe recipe = new Recipe();
        JButton uploadPhoto = new JButton("Add Photo");
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
            "Cook Time:", cookTime,
            "Upload Photo:", uploadPhoto
        };
        List<Ingredient> ingredients = new ArrayList<>();

        addIngredientButton.addActionListener(e1 -> {
          ingredients.add(new Ingredient(recipeIngredients.getText(), ingredientCount.getValue()));
          recipeIngredients.setText("");
          ingredientCount.setValue(1);
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

      Image dimg = getImage("src/Assets/leaf.png").getScaledInstance(75, 75, Image.SCALE_SMOOTH);
      ImageIcon imageIcon = new ImageIcon(dimg);

        int option = JOptionPane.showConfirmDialog(null, message, "Add Recipe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, imageIcon);
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
      File file = new File(user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()).getImage());
      file.delete();
      user.getRecipeBook().removeRecipe(user.getRecipeBook().getRecipeList().get(RecipeList.getSelectedIndex()));
      RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());
      RecipeLabel.setText("Recipe: ");
      RecipeLabelInfo.setText("Instructions: ");
      RecipeCookTime.setText("Cook Time: ");
      RecipeIngredientList.setListData(new String[0]);
      deleteRecipeButton.setVisible(false);
      editRecipeButton.setVisible(false);
      RecipePhoto.setVisible(false);

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
        RecipeCookTime.setText("Cook Time: ");
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
