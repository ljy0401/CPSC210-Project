package ui;

import model.RestaurantReview;
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
import java.util.List;

// This class represents the main class of the graphical user interface (GUI)
// of the restaurant review application. In this application, you can load and save
// the restaurant review information, add new restaurant to the review list, remove
// existing restaurants from the review list, view review information of all restaurants
// you have added to this application, and filter out those you want to revisit.

// Some of the codes in this class were written based on the tutorial examples provided on edX
// such as components-ListDemoProject, components-ButtonDemoProject, components-ScrollDemoProject,
// etc. The link to those tutorial demo projects is
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html.
// More examples could be found on edX on
// https://learning.edge.edx.org/course/course-v1:UBC+CPSC210+2023W1.
// The codes for this graphical user interface (GUI) were written using the Java Swing library
// https://en.wikipedia.org/wiki/Swing_(Java).
// Another reference to some components of my graphical user interface (GUI) is a YouTube
// tutorial video "Java GUI: Full Course ☕ (FREE)" by Bro Code found on
// https://www.youtube.com/watch?v=Kmgo00avvEw. I followed the workflow of the construction of
// some components of a GUI　taught in that tutorial video, but all codes in this class are
// written by myself.
public class RestaurantReviewAppGUI extends JFrame {
    // constants
    private static final int WIDTH = 814;
    private static final int HEIGHT = 638;
    private static final String JSON_STORE = "./data/restaurantreviewlist.json";

    // the restaurant review list field and the JSON fields
    private RestaurantReviewList restaurantReviewList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // the overall frame field
    private JFrame frame;

    // all the text input fields
    private JTextField nameTextField;
    private JTextField ratingTextField;
    private JTextField aveCostTextField;
    private JTextField titleTextField;
    private JTextField goAgainTextField;
    private JTextField removeRestaurantNameTextField;

    // all fields that display texts
    private JTextArea goAgainTextArea;
    private JScrollPane goAgainScrollPane;
    private JTextArea viewTextArea;
    private JScrollPane viewScrollPane;

    // MODIFIES: this
    // EFFECTS: constructs the frame and components for the Restaurant Review APP graphical user interface
    public RestaurantReviewAppGUI() {
        // initialize the Restaurant Review List and the JSON objects
        restaurantReviewList = new RestaurantReviewList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        // Set up the frame, display the splash image, and set the icon of this application
        displaySplashImage();
        frame = new JFrame("Restaurant Review Application");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("images/icon.jpg");
        frame.setIconImage(icon.getImage());
        frame.setLayout(null);

        // Construct and set up all the panels in this application
        new AddPanelClass();
        new RemovePanelClass();
        new ViewPanelClass();
        new GoAgainPanel();
        new LoadPanel();
        new SavePanel();
        frame.setVisible(true);
    }

    // MODIFIES: splashImage
    // EFFECTS: display the splash image for 5 seconds when the application starts
    public void displaySplashImage() {
        JWindow splashImage = new JWindow();
        JLabel imageLabel = new JLabel(new ImageIcon("images/splash_image.jpg"));
        splashImage.getContentPane().add(imageLabel, BorderLayout.CENTER);
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

    // the private class for the add panel
    @SuppressWarnings("methodlength")
    private class AddPanelClass extends JFrame {
        private JButton addButton;

        public AddPanelClass() {
            JPanel addMethodPanel = addPanel(0,200,300,400);
            JLabel addMethodTextLabel = generatePanelTextLabel("Add a new restaurant review:");
            addMethodPanel.add(addMethodTextLabel);
            addButton = new JButton("Add This Restaurant Review!");
            addButton.setToolTipText("After typing in the review information of a restaurant"
                    + ", please click this button to add the restaurant to application.");
            addButton.addActionListener(new ViewButtonListener());
            addButton.addActionListener(new AddButtonListener());
            addButton.setFocusable(false);
            addButton.setBounds(0, 550, 300, 50);
            frame.add(addButton);
            JLabel nameLabel = new JLabel();
            nameLabel.setText("Name:");
            nameLabel.setBounds(0,230,50,50);
            nameTextField = new JTextField();
            nameTextField.setBounds(50,230,250,50);
            nameTextField.setToolTipText("the name of the restaurant you want to add");
            frame.add(nameLabel);
            frame.add(nameTextField);
            JLabel ratingLabel = new JLabel();
            ratingLabel.setText("Rating:");
            ratingLabel.setBounds(0,280,50,50);
            ratingTextField = new JTextField();
            ratingTextField.setBounds(50,280,250,50);
            ratingTextField.setToolTipText("rating score out of 5, your input could be any real numbers in [0,5]");
            frame.add(ratingLabel);
            frame.add(ratingTextField);
            JLabel aveCostLabel = new JLabel();
            aveCostLabel.setText("Ave Cost:");
            aveCostLabel.setFont(new Font(Font.DIALOG, Font.BOLD,10));
            aveCostLabel.setBounds(0,330,50,50);
            aveCostTextField = new JTextField();
            aveCostTextField.setBounds(50,330,250,50);
            aveCostTextField.setToolTipText("your input should be a non-negative whole number");
            frame.add(aveCostLabel);
            frame.add(aveCostTextField);
            JLabel titleLabel = new JLabel();
            titleLabel.setText("Title:");
            titleLabel.setBounds(0,380,50,50);
            titleTextField = new JTextField();
            titleTextField.setBounds(50,380,250,50);
            titleTextField.setToolTipText("any comments about this restaurant that your want to add");
            frame.add(titleLabel);
            frame.add(titleTextField);
            JLabel goAgainLabel = new JLabel();
            goAgainLabel.setText("Go Again?");
            goAgainLabel.setFont(new Font(Font.DIALOG, Font.BOLD,10));
            goAgainLabel.setBounds(0,430,50,50);
            goAgainTextField = new JTextField();
            goAgainTextField.setBounds(50,430,250,50);
            goAgainTextField.setToolTipText("your input should be either true or false");
            frame.add(goAgainLabel);
            frame.add(goAgainTextField);
            frame.add(addMethodPanel);
        }
    }

    // the private class for the remove panel
    private class RemovePanelClass extends JFrame {
        private JButton removeButton;

        public RemovePanelClass() {
            JPanel removeMethodPanel = addPanel(300,200,250,350);
            JLabel removeMethodTextLabel = generatePanelTextLabel("Remove an existing restaurant review:");
            removeMethodPanel.add(removeMethodTextLabel);
            removeButton = new JButton("Remove This Restaurant Review!");
            removeButton.setToolTipText("After typing in the name of the restaurant you want to remove"
                    + ", please click this button to remove it from application");
            removeButton.addActionListener(new ViewButtonListener());
            removeButton.addActionListener(new RemoveButtonListener());
            removeButton.setFocusable(false);
            removeButton.setBounds(300, 520, 250, 30);
            frame.add(removeButton);
            JLabel removeRestaurantNameLabel = new JLabel("Name of Restaurant You Want To Remove:");
            removeRestaurantNameLabel.setBounds(300,250,250,50);
            removeRestaurantNameTextField = new JTextField();
            removeRestaurantNameTextField.setBounds(300,302,250,100);
            removeRestaurantNameTextField.setToolTipText("name of the restaurant you want to remove");
            frame.add(removeRestaurantNameLabel);
            frame.add(removeRestaurantNameTextField);
            frame.add(removeMethodPanel);
        }
    }

    // the private class for the view panel
    private class ViewPanelClass extends JFrame {
        private JButton viewButton;

        public ViewPanelClass() {
            JPanel viewPanel = addPanel(0,0,800,200);
            JLabel viewMethodTextLabel = generatePanelTextLabel("You can view all pieces of review information that "
                    + "have been added to this restaurant review application by clicking the button below.");
            viewPanel.add(viewMethodTextLabel);
            viewButton = new JButton("View All The Restaurant Reviews!");
            viewButton.setToolTipText("View all restaurant review information you have written "
                     + "by clicking this button");
            viewButton.addActionListener(new ViewButtonListener());
            viewButton.setBounds(200,170,400,30);
            viewButton.setFocusable(false);
            frame.add(viewButton);
            viewTextArea = new JTextArea();
            viewTextArea.setEditable(false);
            viewScrollPane = new JScrollPane(viewTextArea);
            viewScrollPane.setBounds(0,25,800,145);
            viewScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            viewScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            frame.add(viewScrollPane);
            frame.add(viewPanel);
        }
    }

    // the private class for the goAgain panel
    private class GoAgainPanel extends JFrame {
        private JButton goAgainButton;

        public GoAgainPanel() {
            JPanel goAgainMethodPanel = addPanel(550,200,250,350);
            JLabel goAgainMethodTextLabel = generatePanelTextLabel("Restaurants you want to go again:");
            goAgainMethodPanel.add(goAgainMethodTextLabel);
            goAgainButton = new JButton("All Restaurants You Want To Revisit!");
            goAgainButton.setToolTipText("From all restaurants you have added to this application, "
                    + "you can find all restaurants you want to go again by clicking this button.");
            goAgainButton.addActionListener(new GoAgainButtonListener());
            goAgainButton.setFocusable(false);
            goAgainButton.setBounds(550, 520, 250, 30);
            frame.add(goAgainButton);
            goAgainTextArea = new JTextArea();
            goAgainTextArea.setEditable(false);
            goAgainScrollPane = new JScrollPane(goAgainTextArea);
            goAgainScrollPane.setBounds(550,250,250,275);
            goAgainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            goAgainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            frame.add(goAgainScrollPane);
            frame.add(goAgainMethodPanel);
        }
    }

    // the private class for the load panel
    private class LoadPanel extends JFrame {
        private JButton loadButton;

        public LoadPanel() {
            JPanel loadPanel = addPanel(300,550,250,50);
            loadButton = new JButton("Load Review From File!");
            loadButton.setToolTipText("You can load all the restaurant review you have saved by clicking this button.");
            loadButton.addActionListener(new LoadButtonListener());
            loadButton.setFocusable(false);
            loadButton.setBounds(300, 550, 250, 50);
            frame.add(loadButton);
            frame.add(loadPanel);
        }
    }

    // the private class for the save panel
    private class SavePanel extends JFrame {
        private JButton saveButton;

        public SavePanel() {
            JPanel savePanel = addPanel(550,550,250,50);
            saveButton = new JButton("Save Review To File!");
            saveButton.setToolTipText("You can save your restaurant review to file by clicking this button.");
            saveButton.addActionListener(new SaveButtonListener());
            saveButton.setFocusable(false);
            saveButton.setBounds(550, 550, 250, 50);
            frame.add(saveButton);
            frame.add(savePanel);
        }
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
            nameTextField.setText("");
            ratingTextField.setText("");
            aveCostTextField.setText("");
            titleTextField.setText("");
            goAgainTextField.setText("");
            System.out.println("Added!");
        }
    }

    // the ActionListener class for the remove button
    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nameRemoved = removeRestaurantNameTextField.getText();
            restaurantReviewList.removeRestaurantReview(nameRemoved);
            removeRestaurantNameTextField.setText("");
            System.out.println("Removed!");
        }
    }

    // the ActionListener class for the goAgain button
    private class GoAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<RestaurantReview> goAgainRestaurants = restaurantReviewList.getAllRestaurantsWantToGoAgain();
            if (goAgainRestaurants.isEmpty()) {
                goAgainTextArea.setText("You don't have any restaurants that you \nwant to revisit!");
            } else {
                String goAgainMessage = "";
                for (RestaurantReview rr : goAgainRestaurants) {
                    goAgainMessage = goAgainMessage + rr.getName() + "\n\n";
                }
                goAgainTextArea.setText(goAgainMessage);
            }
            System.out.println("Go Again!");
        }
    }

    // the ActionListener class for the view button
    private class ViewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<RestaurantReview> restaurantReviews = restaurantReviewList.getReviewList();
            if (restaurantReviews.isEmpty()) {
                viewTextArea.setText("You don't have any restaurants in your restaurant review "
                        + "application right now! Please add some first!");
            } else {
                String viewMessage = "";
                for (RestaurantReview rr : restaurantReviews) {
                    if (rr.getGoAgain()) {
                        viewMessage = viewMessage + "The restaurant " + rr.getName() + " has a rating score "
                                + rr.getRating() + " out of 5, and the average cost of CAD $"
                                + rr.getAverageCost() + ".\nThe title of the restaurant is "
                                + rr.getTitle() + " and you want to go there again. \n\n";
                    } else {
                        viewMessage = viewMessage + "The restaurant " + rr.getName() + " has a rating score "
                                + rr.getRating() + " out of 5, and the average cost of CAD $"
                                + rr.getAverageCost() + ".\nThe title of the restaurant is "
                                + rr.getTitle() + " and you don't want to go there again. \n\n";
                    }
                }
                viewTextArea.setText(viewMessage);
            }
            System.out.println("Viewed!");
        }
    }

    // EFFECTS: the main method that executes the graphical user interface window
    public static void main(String[] args) {
        new RestaurantReviewAppGUI();
    }
}
