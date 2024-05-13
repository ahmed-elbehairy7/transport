package gui.frames;

import java.awt.GridLayout;

import behindTheScenes.Driver;
import behindTheScenes.Manager;
import behindTheScenes.Passenger;
import gui.components.Button;
import gui.components.Frame;

public class MainFrame extends Frame{
    
    public MainFrame() {
        super();

        setLayout(new GridLayout(3, 1));

        Passenger.initiateClass();
        Manager.initiateClass();
        Driver.initiateClass();

        //Passenger button
        Button PassengerButton = new Button(Passenger.className);
        PassengerButton.addActionListener(e -> {
            dispose();
            new Login(Passenger.className);
        });
        add(PassengerButton);

        //Manager button
        Button ManagerButton = new Button(Manager.className);
        ManagerButton.addActionListener(e -> {
            dispose();
            new Login(Manager.className);
        });
        add(ManagerButton);

        //Driver button
        Button DriverButton = new Button(Driver.className);
        DriverButton.addActionListener(e -> {
            dispose();
            new Login(Driver.className);
        });
        add(DriverButton);

        start();
    }
    }
