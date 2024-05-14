package gui.frames;

import java.awt.GridLayout;

import behindTheScenes.Driver;
import behindTheScenes.Manager;
import behindTheScenes.Passenger;
import gui.components.Button;
import gui.components.Frame;

public class MainFrame extends Frame {
    
    public final String[] classNames = { Passenger.className, Manager.className, Driver.className };
    public String className;
    public MainFrame() {
        super();

        setLayout(new GridLayout(3, 1));

        Passenger.initiateClass();
        Manager.initiateClass();
        Driver.initiateClass();

        for (byte i = 0; i < 3; i++) {
            className = classNames[i];
            Button newButton = new Button(className);
            newButton.addActionListener(e -> {
                dispose();
                new Login(className);
            });
            add(newButton);
        }

        display();
    }
    }
