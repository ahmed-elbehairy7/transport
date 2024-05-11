import java.awt.Color;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    
    MainFrame() {

        this.setTitle("Transport Company App"); //Set the title of the app
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set it to close as expected
        this.setResizable(false);

        this.setSize(720, 720);

        this.getContentPane().setBackground(Color.BLACK);
        
        
    }

}
