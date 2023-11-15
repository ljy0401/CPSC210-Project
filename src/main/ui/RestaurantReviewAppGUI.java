package ui;

import model.RestaurantReviewList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RestaurantReviewAppGUI extends JFrame {
    private static final int WIDTH = 814;
    private static final int HEIGHT = 638;
    private RestaurantReviewList restaurantReviewList;
    private static final String JSON_STORE = "./data/restaurantreviewlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JButton loadButton;
    private JButton saveButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton goAgainButton;
    private JTextField nameTextField;
    private JTextField ratingTextField;
    private JTextField aveCostTextField;
    private JTextField titleTextField;
    private JTextField goAgainTextField;
    private JTextField removeRestaurantNameTextField;

    // MODIFIES: this
    // EFFECTS: constructs the frame for the Restaurant Review APP graphical user interface
    @SuppressWarnings("methodlength")
    public RestaurantReviewAppGUI() {
        restaurantReviewList = new RestaurantReviewList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        loadButton.setFocusable(false);
        loadPanel.add(loadButton);
        saveButton = new JButton("Save Review To File!");
        saveButton.addActionListener(new SaveButtonListener());
        saveButton.setFocusable(false);
        savePanel.add(saveButton);
        addButton = new JButton("Add This Restaurant Review!");
        addButton.addActionListener(new AddButtonListener());
        addButton.setFocusable(false);
        addButton.setBounds(0, 570, 300, 30);
        frame.add(addButton);
        removeButton = new JButton("Remove This Restaurant Review!");
        removeButton.addActionListener(new RemoveButtonListener());
        removeButton.setFocusable(false);
        removeButton.setBounds(300, 520, 250, 30);
        frame.add(removeButton);
        goAgainButton = new JButton("All Restaurants You Want To Revisit!");
        goAgainButton.addActionListener(new GoAgainButtonListener());
        goAgainButton.setFocusable(false);
        goAgainButton.setBounds(550, 520, 250, 30);
        frame.add(goAgainButton);

        frame.add(viewPanel);

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name:");
        nameLabel.setBounds(0,230,50,50);
        nameTextField = new JTextField();
        nameTextField.setBounds(50,230,250,50);
        frame.add(nameLabel);
        frame.add(nameTextField);
        JLabel ratingLabel = new JLabel();
        ratingLabel.setText("Rating:");
        ratingLabel.setBounds(0,280,50,50);
        ratingTextField = new JTextField();
        ratingTextField.setBounds(50,280,250,50);
        frame.add(ratingLabel);
        frame.add(ratingTextField);
        JLabel aveCostLabel = new JLabel();
        aveCostLabel.setText("Ave Cost:");
        aveCostLabel.setFont(new Font(Font.DIALOG,0,10));
        aveCostLabel.setBounds(0,330,50,50);
        aveCostTextField = new JTextField();
        aveCostTextField.setBounds(50,330,250,50);
        frame.add(aveCostLabel);
        frame.add(aveCostTextField);
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Title:");
        titleLabel.setBounds(0,380,50,50);
        titleTextField = new JTextField();
        titleTextField.setBounds(50,380,250,50);
        frame.add(titleLabel);
        frame.add(titleTextField);
        JLabel goAgainLabel = new JLabel();
        goAgainLabel.setText("Go Again?");
        goAgainLabel.setFont(new Font(Font.DIALOG,0,10));
        goAgainLabel.setBounds(0,430,50,50);
        goAgainTextField = new JTextField();
        goAgainTextField.setBounds(50,430,250,50);
        frame.add(goAgainLabel);
        frame.add(goAgainTextField);
        frame.add(addMethodPanel);

        JLabel removeRestaurantNameLabel = new JLabel("Name of Restaurant You Want To Remove:");
        removeRestaurantNameLabel.setBounds(300,250,250,50);
        removeRestaurantNameTextField = new JTextField();
        removeRestaurantNameTextField.setBounds(300,302,250,100);
        frame.add(removeRestaurantNameLabel);
        frame.add(removeRestaurantNameTextField);
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
            try {
                restaurantReviewList = jsonReader.read();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("loaded!");
        }
    }

    // the ActionListener class for the save button
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            jsonWriter.write(restaurantReviewList);
            jsonWriter.close();
            System.out.println("Saved!");
        }
    }

    // the ActionListener class for the add button
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameTextField.getText();
            Double rating = Double.parseDouble(ratingTextField.getText());
            int averageCost = Integer.parseInt(aveCostTextField.getText());
            String title = titleTextField.getText();
            boolean goAgain = Boolean.parseBoolean(goAgainTextField.getText());
            restaurantReviewList.addRestaurantReview(name,rating,averageCost,title,goAgain);
            System.out.println("Added!");
        }
    }

    // the ActionListener class for the remove button
    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nameRemoved = removeRestaurantNameTextField.getText();
            restaurantReviewList.removeRestaurantReview(nameRemoved);
            System.out.println("Removed!!!!");
        }
    }

    // the ActionListener class for the goAgain button
    private class GoAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Go Again!");
        }
    }

    // EFFECTS: the main method that executes the graphical user interface
    public static void main(String[] args) {
        new RestaurantReviewAppGUI();
    }
}
