package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class UserRegistrationGUI extends JFrame {
    private JButton signInButton, signUpButton;

    public UserRegistrationGUI() {
        setTitle("User Authentication");
        setSize(400, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        signInButton = new JButton("Sign In");
        signInButton.setBackground(new Color(70, 130, 180));
        signInButton.setForeground(Color.WHITE);
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSignInDialog();
            }
        });
        add(signInButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(70, 130, 180));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSignUpDialog();
            }
        });
        add(signUpButton);

        setVisible(true);
    }

    private void openSignInDialog() {
        SignInDialog signInDialog = new SignInDialog(this);
        signInDialog.setVisible(true);
    }

    private void openSignUpDialog() {
        SignUpDialog signUpDialog = new SignUpDialog(this);
        signUpDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserRegistrationGUI();
            }
        });
    }
}

class SignInDialog extends JDialog {
    private JTextField usernameField, passwordField;

    public SignInDialog(JFrame parent) {
        super(parent, "Sign In", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signIn();
            }
        });
        add(signInButton);
    }

    private void signIn() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (authenticateUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Sign in successful!");
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        String fileName = "users.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 4 && userData[2].equals(username) && userData[3].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

class SignUpDialog extends JDialog {
    private JTextField nameField, emailField, usernameField, passwordField;

    public SignUpDialog(JFrame parent) {
        super(parent, "Sign Up", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });
        add(signUpButton);
    }

    private void signUp() {
        String name = nameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        String fileName = "users.txt";
        String userData = name + "," + email + "," + username + "," + password;
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(userData);
            bufferedWriter.newLine();
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            setVisible(false);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while writing to file.");
            e.printStackTrace();
        }
    }
}