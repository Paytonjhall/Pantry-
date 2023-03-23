package View.ActionListeners;

import Pantry.Ingredient;
import Recipe.Recipe;
import User.User;
import User.JsonConverter;
import Utils.Colors;
import View.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class RecipeAction implements MouseListener {

    protected JTextField recipeName;
    protected JTextField recipeInstructions;
    protected JTextField recipeIngredients;
    protected JTextField cookTime;
    protected String[] possibleUnits = {
            "WHOLE ITEM", "GALLON", "LITER", "CUP", "QUART", "PINT", "MILLILITER",
            "TABLESPOON", "TEASPOON", "FLUID OUNCE"
    }; // TODO: ADD THE WEIGHT MEASUREMENTS TO THIS LIST

    protected JComboBox<String> unitField;
    protected JSpinner ingredientSize;
    protected Recipe finalRecipe;
    protected JButton uploadPhoto;
    protected JButton addIngredientButton;
    protected List<Ingredient> ingredients;
    JCheckBox breakfastTag;
    JCheckBox lunchTag;
    JCheckBox dinnerTag;
    JCheckBox mainCourseTag;
    JCheckBox sideDishTag;
    JCheckBox snackTag;
    JCheckBox dessertTag;
    JCheckBox vegetarianTag;
    JCheckBox glutenFreeTag;
    JCheckBox meatTag;
    User user;

    public RecipeAction(User user) {
        this.user = user;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        createEntryBoxFields();
        initializeFields();

        addIngredientButton.addActionListener(new AddIngredientButtonAction());
        uploadPhoto.addActionListener(new UploadPhotoAction());

        handleOutput();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setButtonColor(Colors.green);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setButtonColor(Colors.gray);
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

        createCheckBoxFields();
    }

    protected void createCheckBoxFields() {
        breakfastTag = new JCheckBox("Breakfast");
        lunchTag = new JCheckBox("Lunch");
        dinnerTag = new JCheckBox("Dinner");
        mainCourseTag = new JCheckBox("Main Course");
        sideDishTag = new JCheckBox("SIde Dish");
        snackTag = new JCheckBox("Snack");
        dessertTag = new JCheckBox("Dessert");
        vegetarianTag = new JCheckBox("Vegetarian");
        glutenFreeTag = new JCheckBox("Gluten Free");
        meatTag = new JCheckBox("Meat");
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
                "Upload Photo:", uploadPhoto,
                "Tags:", breakfastTag, lunchTag, dinnerTag, mainCourseTag, sideDishTag, snackTag, dessertTag, vegetarianTag,
                glutenFreeTag, meatTag
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

    protected void handleOutput() {
        int option = JOptionPane.showConfirmDialog(null, getMessage(), "Add Recipe", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, MainView.getLogo());
        if (option == JOptionPane.OK_OPTION) {
            finalRecipe.setIngredients(ingredients);
            finalRecipe.setName(recipeName.getText());
            finalRecipe.setInstructions(recipeInstructions.getText());
            finalRecipe.setTime(cookTime.getText());

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
            updateInterfaceList();
        }
    }

    protected abstract void updateInterfaceList();
    protected abstract void setButtonColor(Color color);

        /*
    Inner classes to handle photo upload and ingredient buttons
     */
    private class AddIngredientButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Ingredient newIngredient = new Ingredient(recipeIngredients.getText(),
                    Double.parseDouble(ingredientSize.getValue().toString()), unitField.getItemAt(unitField.getSelectedIndex()));

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
                try {
                    saveFile();
                } catch (IOException ex) {
                    System.out.println("Error uploading photo: " + ex.getMessage());
                }
            }
        }

        private void saveFile() throws IOException {
            File file = fileChooser.getSelectedFile();
//            Toolkit toolkit = Toolkit.getDefaultToolkit();
            String stringFile = file.toString();
//            Image image = toolkit.getImage(stringFile);
            Path path = Paths.get(stringFile);
            String imagePath = path.toAbsolutePath().toString();
//            String newStr = imagePath.toString();
//            BufferedImage picture = ImageIO.read(new File(newStr));
            String extension = imagePath.substring(imagePath.lastIndexOf(".") + 1);
            String newPath = "src/Recipe/Photos/" + user.getUsername() + "-" + recipeName.getText() + "." + extension;
            finalRecipe.setImage(newPath);
            (new JsonConverter()).addPhotoToFile(imagePath, newPath);
        }
    }

}