package View;
import User.*;
import javax.swing.*;
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

  //Load main view
  public MainView( User user ) {
    setContentPane(MainViewPanel);
    setTitle("Pantry ++");
    setSize(1400, 600);
    RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

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
        RecipePanel.repaint();
      }
    });

    // Not sure if this method of populating the data will work

    saveButton.addActionListener(e -> {
      try{
        jsonConverter.addUserToFile(user);
      } catch (Exception ex){
        System.out.println("Error saving user: " + ex);
      }
    });

    logoutButton.addActionListener(e -> {
      LoginView loginView = new LoginView();
      dispose();
    });

  }
}
