package View;

import javax.swing.*;

public class MainView extends JFrame{
  private JLabel ProjectName;
  private JList RecipeList;
  private JScrollPane PantryPane;
  private JPanel MainViewPanel;

  //Load main view
  public MainView() {
    setContentPane(MainViewPanel);
    setTitle("Pantry Manager");
    setSize(1400, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

}
