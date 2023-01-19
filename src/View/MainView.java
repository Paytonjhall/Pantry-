package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame{
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JPanel loginPanel;
  private JButton exitButton;
  private JButton loginButton;
  private JButton registerButton;
  private JLabel welcomeText;
  private JLabel leafLabel;
  private JLabel userNameText;
  private JLabel passwordText;

  public MainView() throws HeadlessException {
    setContentPane(loginPanel);
    setTitle("Pantry Manager");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //leafLabel.setIcon(new ImageIcon("src/Assets/leaf.png", "Leaf",100, 100));
    Image dimg = getImage("src/Assets/leaf.png").getScaledInstance(75, 75,
            Image.SCALE_SMOOTH);
    ImageIcon imageIcon = new ImageIcon(dimg);
    leafLabel.setIcon(imageIcon);
    setVisible(true);
    exitButton.addActionListener(e -> System.exit(0));
  }

    public static void main(String[] args) {
        MainView view = new MainView();
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

}
