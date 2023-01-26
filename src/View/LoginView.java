package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginView extends JFrame{
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

  public LoginView() throws HeadlessException {
    //Make Panel:
    setContentPane(loginPanel);
    setTitle("Pantry Manager");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Make leaf icon
    Image dimg = getImage("src/Assets/leaf.png").getScaledInstance(75, 75,
            Image.SCALE_SMOOTH);
    ImageIcon imageIcon = new ImageIcon(dimg);
    leafLabel.setIcon(imageIcon);
    System.out.println("username: " + usernameField.getText());
    System.out.println("password: " + passwordField.getText());
    //Make panel visible
    setVisible(true);
    //Add buttons
    exitButton.addActionListener(e -> System.exit(0));

    loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        if(usernameField.getText().equals("admin") && passwordField.getText().equals("admin")){
          System.out.println("Login Successful");
          MainView view = new MainView();
          dispose();
        }
        else{
          System.out.println("Login Failed");
        }
      }
    });

    registerButton.addActionListener(e -> {
      //add Register code
    });

  }

    public static void main(String[] args) {
        LoginView view = new LoginView();
    }




  //Used to make images on the panel
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
