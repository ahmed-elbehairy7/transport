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

    public boolean login(String username, String password) {
            return false;
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
            String userType = getUserType(username);
            switch (userType) {
                case "passenger":
                    PassengerDialog passengerDialog = new PassengerDialog((JFrame) getParent());
                    passengerDialog.setVisible(true);
                    break;
                case "driver":
                    DriverDialog driverDialog = new DriverDialog((JFrame) getParent());
                    driverDialog.setVisible(true);
                    break;
                case "manager":
                    ManagerDialog managerDialog = new ManagerDialog((JFrame) getParent());
                    managerDialog.setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid user type.");
                    break;
            }
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

    private String getUserType(String username) {
        String fileName = "users.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 4 && userData[2].equals(username)) {
                    return userData[4];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class SignUpDialog extends JDialog {
    private JTextField nameField, emailField, usernameField, passwordField;
    private JRadioButton passengerButton, driverButton, managerButton;

    public SignUpDialog(JFrame parent) {
        super(parent, "Sign Up", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2));

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

        add(new JLabel("Select User Type:"));
        ButtonGroup group = new ButtonGroup();
        passengerButton = new JRadioButton("Passenger");
        driverButton = new JRadioButton("Driver");
        managerButton = new JRadioButton("Manager");
        group.add(passengerButton);
        group.add(driverButton);
        group.add(managerButton);
        JPanel userTypePanel = new JPanel();
        userTypePanel.add(passengerButton);
        userTypePanel.add(driverButton);
        userTypePanel.add(managerButton);
        add(userTypePanel);

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
        String userType = "";
        if (passengerButton.isSelected()) {
            userType = "passenger";
        } else if (driverButton.isSelected()) {
            userType = "driver";
        } else if (managerButton.isSelected()) {
            userType = "manager";
        }

        if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || userType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        String userData = name + "," + email + "," + username + "," + password + "," + userType;
        String fileName = "users.txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userData);
            bufferedWriter.newLine();
            bufferedWriter.close();
            JOptionPane.showMessageDialog(this, "Registration successful.");
            setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while writing to file.");
        }
    }
}

class PassengerDialog extends JDialog {
    public PassengerDialog(JFrame parent) {
        super(parent, "Passenger Dialog", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome, Passenger!");
        new PassengersGUI().setVisible(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(closeButton, BorderLayout.SOUTH);
    }
}

class DriverDialog extends JDialog {
    public DriverDialog(JFrame parent) {
        super(parent, "Driver Dialog", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome, Driver!");
        new DriverGUI().setVisible(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(closeButton, BorderLayout.SOUTH);
    }
}

class ManagerDialog extends JDialog {
    public ManagerDialog(JFrame parent) {
        super(parent, "Manager Dialog", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome, Manager!");
        new ManagerGUI().setVisible(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(closeButton, BorderLayout.SOUTH);
    }
}