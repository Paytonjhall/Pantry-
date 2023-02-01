package View;

import User.*;
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
  JsonConverter jsonConverter = new JsonConverter();

  public LoginView() throws HeadlessException {
    //Make Panel:
    setContentPane(loginPanel);
    setTitle("Pantry ++");
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
        User user = jsonConverter.checkUserFile(usernameField.getText(), passwordField.getText());
        if(user != null) {
          System.out.println("Login Successful");
          MainView view = new MainView(user);
          dispose();
        }
        else {
          System.out.println("Login Failed");
        }}});

    registerButton.addActionListener(e -> {
      //add Register code
      if(!usernameField.getText().equals("") && !passwordField.getText().equals("")){
        System.out.println("Register Successful");
        User user = new User(usernameField.getText(), passwordField.getText());
        MainView view;
        if(jsonConverter.addUserToFile(user)){
          view = new MainView(user);
          dispose();
        } else {
          //display information saying that the username is already taken
        }
      }
      else{
        System.out.println("Register Failed");
      }
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