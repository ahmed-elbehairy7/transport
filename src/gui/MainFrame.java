package gui;

import java.awt.Color;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    
    public MainFrame() {

        this.setTitle("Transport Company App");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setSize(720, 720);

        this.getContentPane().setBackground(Color.BLACK);
        
        
    }

}
