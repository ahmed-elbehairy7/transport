package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.components.Button;
import gui.components.Frame;
import gui.components.Label;
import gui.components.P1;

public class UserRegisteration extends Frame {

    public JTextField usernameField = new JTextField();
    public JTextField passwordField = new JPasswordField();
    public String className;

    public static String[] mustLabels = { "username", "password" };
    public JTextField[] mustFields = { usernameField, passwordField };

    public UserRegisteration(String title) {
        super(title);
    }

    private void generateField(String label, JTextField field) {
        Label newLabel = new Label(label + ":");
        add(newLabel);

        field.setFont(new P1());
        add(field);   
    }
    
    public void generateUi(String[] labels, JTextField[] textFields, String[] buttonsText,
            ActionListener[] actionListeners) {

        setLayout(new GridLayout(labels.length + 3, 2));

        for (byte i = 0; i < labels.length; i++) {
            generateField(labels[i], (textFields[i]));
        }

        for (byte i = 0; i < 2; i++) {
            generateField(mustLabels[i], mustFields[i]);
        }

        for (byte i = 0; i < 2; i++) {
            Button newButton = new Button(buttonsText[i]);
            newButton.addActionListener(actionListeners[i]);
            add(newButton);
        }

    }    
}
