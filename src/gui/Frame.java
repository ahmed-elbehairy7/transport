package gui;

import java.awt.Color;
import javax.swing.JFrame;

public class Frame extends JFrame {
    
    public Frame(String title) {
        setTitle(title);
        start();
    }
    public Frame() {
        setTitle("Transport Company App");
        start();
    }

    private void start() { 
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 720);
        getContentPane().setBackground(Color.WHITE);
    }
}