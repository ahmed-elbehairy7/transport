package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ManagerGui extends JFrame {
    private JTextField driverEmailField, tripNumberField;
    private JButton assignDriverButton;
    private JTextArea outputTextArea;   

    public ManagerGui() {
        setTitle("Manager Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Driver Email:"));
        driverEmailField = new JTextField();
        inputPanel.add(driverEmailField);

        inputPanel.add(new JLabel("Trip Number:"));
        tripNumberField = new JTextField();
        inputPanel.add(tripNumberField);

        assignDriverButton = new JButton("Assign Driver");
        assignDriverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assignDriverToTrip();
            }
        });
        inputPanel.add(assignDriverButton);

        add(inputPanel, BorderLayout.NORTH);

        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void assignDriverToTrip() {
        String driverEmail = driverEmailField.getText();
        int tripNumber = Integer.parseInt(tripNumberField.getText());

        ManagerGui manager = new ManagerGui();
        manager.assignDriverToTrip();

        // Update output text area
        outputTextArea.append("Driver assigned to trip: " + driverEmail + ", " + tripNumber + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ManagerGui().setVisible(true);
            }
        });
    }
}