package ui;

import javax.swing.*;
import java.awt.*;

public class RestaurantReviewAppGUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // MODIFIES: this
    // EFFECTS: constructs the frame for the Restaurant Review APP graphical user interface
    public RestaurantReviewAppGUI() {
        super();
        this.setTitle("Restaurant Review Application");
        this.setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        ImageIcon icon = new ImageIcon("images/icon.jpg");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(202,219,206));
    }
}
