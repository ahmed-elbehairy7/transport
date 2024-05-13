package gui.frames;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

import behindTheScenes.Driver;
import behindTheScenes.Manager;
import behindTheScenes.Passenger;
import behindTheScenes.User;
import gui.UserRegisteration;

public class Login extends UserRegisteration {

    public Login(String text) {
        super("Login");
        className = text;

        String[] labels = {};
        JTextField[] textFields = {};
        String[] buttonsText = { "Sign in", "New? Sign Up?" };
        ActionListener[] actionListeners = { e -> signIn(), e -> {
            dispose();
            new Register(className);
        } };

        generateUi(labels, textFields, buttonsText, actionListeners);
    }

    private void signIn() {
        
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        User user;
        switch (className) {
            case Passenger.className:
                user = Passenger.login(username, password, Passenger.instances);
                break;
            case Manager.className:
                user = Manager.login(username, password, Manager.instances);
                break;
            case Driver.className:
                user = Driver.login(username, password, Driver.instances);
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