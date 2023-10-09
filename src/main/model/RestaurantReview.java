package model;

// This class represents the review information of a restaurant
// written by the user of this application. The review information
// contains the name, the rating of the restaurant on a scale of 5,
// the average cost in that restaurant, the relevant title or
// keywords for that restaurant, and whether wanting to go again.
public class RestaurantReview {
    private String name;  // the name of the restaurant
    private double rating;  // the rating of the restaurant on a scale of 5
    private int averageCost; // the average cost in that restaurant (in whole CAD)
    private String title; // the relevant title or keywords for that restaurant
    private boolean goAgain; // whether the user wants to go again

    // REQUIRES: the name is not an empty string; 0 <= rating <= 5;
    // averageCost > 0 in whole CAD; the title is not an empty string
    // EFFECTS: construct a new restaurant review information for a given
    // name, rating, average, title of a restaurant, and whether you
    // want to go again.
    public RestaurantReview(String name, double rating, int averageCost,
                                 String title, boolean goAgain) {
        this.name = name;
        this.rating = rating;
        this.averageCost = averageCost;
        this.title = title;
        this.goAgain = goAgain;
    }

    // EFFECTS: returns the name of the restaurant
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the rating of the restaurant
    public double getRating() {
        return this.rating;
    }

    // EFFECTS: returns the average cost of the restaurant
    public int getAverageCost() {
        return this.averageCost;
    }

    // EFFECTS: returns the title of the restaurant
    public String getTitle() {
        return this.title;
    }

    // EFFECTS: returns whether the user wants to go to that restaurant again
    public boolean getGoAgain() {
        return this.goAgain;
    }

//    // MODIFIES: this
//    // EFFECTS: set the name of the restaurant
//    public void setName(String name) {
//        this.name = name;
//    }

    // MODIFIES: this
    // EFFECTS: set the rating of the restaurant
    public void setRating(double rating) {
        this.rating = rating;
    }

    // MODIFIES: this
    // EFFECTS: set the average cost of the restaurant
    public void setAverageCost(int averageCost) {
        this.averageCost = averageCost;
    }

    // MODIFIES: this
    // EFFECTS: set the title of the restaurant
    public void setTitle(String title) {
        this.title = title;
    }

    // MODIFIES: this
    // EFFECTS: set whether the user wants to go to that restaurant again
    public void setGoAgain(boolean goAgain) {
        this.goAgain = goAgain;
    }
}
