package gui.frames;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import behindTheScenes.Driver;
import behindTheScenes.Manager;
import behindTheScenes.Passenger;
import behindTheScenes.User;
import data.Validations;
import gui.UserRegisteration;
import gui.components.Button;
import gui.components.Frame;
import gui.components.Label;
import gui.components.P1;

public class Register extends UserRegisteration {
    public JTextField emailField = new JTextField();
    public JTextField nameField = new JTextField();

    public Register(String text) {
        super("Sign Up");
        className = text;

        String[] labels = {"name", "email"};
        JTextField[] textFields = {nameField, emailField};
        String[] buttonsText = { "Sign in", "New? Sign Up?" };
        ActionListener[] actionListeners = { e -> signUp(), e -> {
            dispose();
            new Login(className);
        } };

        generateUi(labels, textFields, buttonsText, actionListeners);
    }

    private void signUp() {
        
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String email = emailField.getText().trim();
        String name = nameField.getText().trim();

        if (!(Validations.validPass(password)) && Validations.validEmail(email) && Validations.validName(name)) {
            return;
        }

        User user;
        switch (className) {
            case Passenger.className:
                if (!Validations.validUsername(username, Passenger.instances)) {
                    return;
                }
                user = new Passenger(Passenger.generateId(), name, email, username, password, Passenger.instances.size());
                user.writeInstance(Passenger.savedPath);
                break;
            case Manager.className:
            if (!Validations.validUsername(username, Manager.instances)) {
                    return;
                }
                user = new Manager(Manager.generateId(), name, email, username, password, Manager.instances.size());
                user.writeInstance(Manager.savedPath);
                break;
            case Driver.className:
            if (!Validations.validUsername(username, Driver.instances)) {
                    return;
                }
                user = new Driver(Driver.generateId(), name, email, username, password, Driver.instances.size());
                user.writeInstance(Driver.savedPath);
                break;
            default:
                user = new User();
                break;
        }

        if (!user.success) {
            return;
        }

        switch (className) {
            case Passenger.className:
                dispose();
                new PassengerGui(user);
                break;
            case Manager.className:
                dispose();
                new ManagerGui(user);
                break;
            case Driver.className:
                dispose();
                new DriverGui(user);
                break;
        }
        

        
        
    }
}