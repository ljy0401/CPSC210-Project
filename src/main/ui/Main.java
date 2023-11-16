package ui;

import java.io.FileNotFoundException;

// The main class the console-based user interface of the restaurant review application.
public class Main {
    public static void main(String[] args) {
        try {
            new RestaurantReviewApp();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
