package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DriverGUI extends JFrame {
    private JButton viewInfoButton, viewAssignedTripsButton;
    private JTextArea outputTextArea;

    public DriverGUI() {
        setTitle("Driver Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));

        viewInfoButton = new JButton("View Info");
        viewInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewInfo();
            }
        });
        buttonPanel.add(viewInfoButton);

        viewAssignedTripsButton = new JButton("View Assigned Trips");
        viewAssignedTripsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAssignedTrips();
            }
        });
        buttonPanel.add(viewAssignedTripsButton);

        add(buttonPanel, BorderLayout.WEST);

        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void viewInfo() {
    }

    private void viewAssignedTrips() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DriverGUI().setVisible(true);
            }
        });
    }
}