package ui;

import model.RestaurantReview;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new RestaurantReviewApp();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
