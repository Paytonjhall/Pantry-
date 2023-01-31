package View;
import User.*;
import javax.swing.*;

public class MainView extends JFrame{
  private JsonConverter jsonConverter = new JsonConverter();
  private JLabel ProjectName;
  private JList RecipeList;
  private JPanel MainViewPanel;
  private JButton addRecipeButton;
  private JButton logoutButton;
  private JButton saveButton;
  private JPanel RecipePanel;

  //Load main view
  public MainView( User user ) {
    setContentPane(MainViewPanel);
    setTitle("Pantry ++");
    setSize(1400, 600);
    RecipeList.setListData(user.getRecipeBook().getRecipeStringList().toArray());

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

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
