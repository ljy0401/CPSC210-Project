package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantReviewAppGUI extends JFrame {
    private static final int WIDTH = 814;
    private static final int HEIGHT = 638;
    private JButton loadButton;

    // MODIFIES: this
    // EFFECTS: constructs the frame for the Restaurant Review APP graphical user interface
    @SuppressWarnings("methodlength")
    public RestaurantReviewAppGUI() {
        displaySplashImage();
        JFrame frame = new JFrame("Restaurant Review Application");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("images/icon.jpg");
        frame.setIconImage(icon.getImage());
        frame.setLayout(null);
        JPanel viewPanel = addPanel(0,0,800,200);
        JPanel addMethodPanel = addPanel(0,200,300,400);
        JPanel removeMethodPanel = addPanel(300,200,250,350);
        JPanel goAgainMethodPanel = addPanel(550,200,250,350);
        JPanel loadPanel = addPanel(300,550,250,50);
        JPanel savePanel = addPanel(550,550,250,50);
        JLabel viewMethodTextLabel = generatePanelTextLabel("You can view all pieces of review information that "
                + "have been added to this restaurant review application by clicking the \"Display\" button.");
        JLabel addMethodTextLabel = generatePanelTextLabel("Add a new restaurant review:");
        JLabel removeMethodTextLabel = generatePanelTextLabel("Remove an existing restaurant review:");
        JLabel goAgainMethodTextLabel = generatePanelTextLabel("Restaurants you want to go again:");
        viewPanel.add(viewMethodTextLabel);
        addMethodPanel.add(addMethodTextLabel);
        removeMethodPanel.add(removeMethodTextLabel);
        goAgainMethodPanel.add(goAgainMethodTextLabel);
        loadButton = new JButton("Load Review From File!");
        loadButton.addActionListener(new LoadButtonListener());
        loadButton.setBounds(300,550,250,50);
        loadButton.setFocusable(false);
        loadPanel.add(loadButton);
        frame.add(viewPanel);
        frame.add(addMethodPanel);
        frame.add(removeMethodPanel);
        frame.add(goAgainMethodPanel);
        frame.add(loadPanel);
        frame.add(savePanel);
        frame.setVisible(true);
    }

    // MODIFIES: splashImage
    // EFFECTS: display the splash image for 5 seconds when the application starts
    public void displaySplashImage() {
        JWindow splashImage = new JWindow();
        JLabel label = new JLabel(new ImageIcon("images/splash_image.jpg"));
        splashImage.getContentPane().add(label, BorderLayout.CENTER);
        splashImage.setSize(900,600);
        splashImage.setLocationRelativeTo(this);
        splashImage.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        splashImage.setVisible(false);
    }

    // MODIFIES: newPanel
    // EFFECTS: constructs a panel on the frame based on the given x and y positions and width and height
    public JPanel addPanel(int x, int y, int width, int height) {
        JPanel newPanel = new JPanel();
        newPanel.setBounds(x,y,width,height);
        Border panelBorder = BorderFactory.createLineBorder(Color.BLACK);
        newPanel.setBorder(panelBorder);
        newPanel.setBackground(new Color(202,219,206));
        return newPanel;
    }

    // MODIFIES: panelTextLabel
    // EFFECTS: generate the text label for a panel
    public JLabel generatePanelTextLabel(String text) {
        JLabel panelTextLabel = new JLabel();
        panelTextLabel.setText(text);
        panelTextLabel.setForeground(new Color(17,5,115));
        return panelTextLabel;
    }

    // the ActionListener class for the load button
    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("loaded!");
        }
    }

    // EFFECTS: the main method that executes the graphical user interface
    public static void main(String[] args) {
        new RestaurantReviewAppGUI();
    }
}
