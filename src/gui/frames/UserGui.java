package gui.frames;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import behindTheScenes.Ticket;
import behindTheScenes.Trip;
import behindTheScenes.User;
import behindTheScenes.Vehicle;
import gui.components.Button;
import gui.components.Frame;
import gui.components.TextArea;

public class UserGui extends Frame {

    public User user;
    public JPanel buttonPanel;
    public TextArea outputArea;
    
    public UserGui(String title, User theUser) {
        super(title);
        user = theUser;

        Trip.initiateClass();
        Vehicle.initiateClass();
        Ticket.initiateClass();

        setLayout(new BorderLayout());
        buttonPanel = new JPanel();
    }

    public void generateUi(String[] buttonTexts, ActionListener[] functions) {
        int length = buttonTexts.length;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(length, 1));

        for (byte i = 0; i < length; i++) {
            //Book a ticket
            Button newButton = new Button(buttonTexts[i]);
            newButton.addActionListener(functions[i]);
            buttonPanel.add(newButton);
        }

        add(buttonPanel, BorderLayout.WEST);

        outputArea = new TextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);
    }

}