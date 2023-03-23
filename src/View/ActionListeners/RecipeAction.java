package View.ActionListeners;

import Pantry.Ingredient;
import Recipe.Recipe;
import User.User;
import User.JsonConverter;

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

public class RecipeAction implements ActionListener {

    JTextField recipeName;
    JTextField recipeInstructions;
    JTextField recipeIngredients;
    JTextField cookTime;
    String possibleUnits[] = {
            "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER",
            "TABLESPOON", "TEASPOON", "FLUID OUNCE"
    }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST

    JComboBox<String> unitField;
    JSpinner ingredientSize;
    Recipe finalRecipe;
    JButton uploadPhoto;
    JButton addIngredientButton;
    List<Ingredient> ingredients;
    User user;

    public RecipeAction(User user) {
        this.user = user;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        createEntryBoxFields();
        initializeFields();

        addIngredientButton.addActionListener(new AddIngredientButtonAction());
        uploadPhoto.addActionListener(new UploadPhotoAction());
    }

    protected void createEntryBoxFields() {
        recipeName = new JTextField();
        recipeInstructions = new JTextField();
        recipeIngredients = new JTextField();
        cookTime = new JTextField();
        unitField = new JComboBox<>(possibleUnits);
        ingredientSize = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 0.01));
        finalRecipe = new Recipe();
        uploadPhoto = new JButton("Add Photo");
        addIngredientButton = new JButton("Add Ingredient");
        ingredients = new ArrayList<>();


    }

    protected void initializeFields() {
        unitField.setSelectedIndex(0);
        finalRecipe.setName(recipeName.getText());
    }

    protected Object[] getMessage() {
        Object[] message = {
                "Recipe Name:", recipeName,
                "Recipe Instructions:", recipeInstructions,
                "Recipe Ingredient:", recipeIngredients,
                "Ingredient Quantity:", ingredientSize, unitField,
                addIngredientButton,
                "Cook Time:", cookTime,
                "Upload Photo:", uploadPhoto
        };
        return message;
    }

    protected void addIngredientItem(Ingredient item) {
        ingredients.add(item);
    }

    protected void resetIngredientFields() {
        recipeIngredients.setText("");
        ingredientSize.setValue(1);
        unitField.setSelectedIndex(0);
    }


    private class AddIngredientButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Ingredient newIngredient = new Ingredient(recipeIngredients.getText(),
                    Double.parseDouble(ingredientSize.toString()), unitField.getItemAt(unitField.getSelectedIndex()));

            addIngredientItem(newIngredient);
            resetIngredientFields();
        }
    }

    private class UploadPhotoAction implements ActionListener {

        JFileChooser fileChooser;
        final String FILE_DIR = "user.home";

        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty(FILE_DIR)));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
//                try {
//                }
            }
        }

        private void saveFile() throws IOException {
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
            (new JsonConverter()).addPhotoToFile(imagePath, newPath);
        }
    }

}


/*
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

 */