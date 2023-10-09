package model;

import java.util.ArrayList;
import java.util.List;

// This class represents a collection (list) of review information of restaurants.
public class RestaurantReviewList {
    private List<RestaurantReview> reviewList;

    // EFFECTS: construct a new restaurant review list with no restaurants in it
    public RestaurantReviewList() {
        this.reviewList = new ArrayList<>();
    }

    // REQUIRES: the name is not an empty string; 0 <= rating <= 5;
    // averageCost > 0 in whole CAD; the title is not an empty string
    // MODIFIES: this
    // EFFECTS: add a new review information of a restaurant with the given information
    // to the restaurant review list
    public void addRestaurantReview(String name, double rating, int averageCost,
                                    String title, boolean goAgain) {
        this.reviewList.add(new RestaurantReview(name, rating, averageCost, title, goAgain));
    }

    // REQUIRES: the name is not an empty string, the review list is not empty,
    // and there must exist a review information of a restaurant that has the
    // same name as the given string, otherwise this method does nothing
    // MODIFIES: this
    // EFFECTS: remove the review information of a restaurant in the list that has
    // the same name as the specified name
    public void removeRestaurantReview(String name) {
        RestaurantReview movedRestaurant = null;
        for (RestaurantReview rr : this.reviewList) {
            if (name.equals(rr.getName())) {
                movedRestaurant = rr;
            }
        }
        this.reviewList.remove(movedRestaurant);
    }

    // REQUIRES: the name is not an empty string, the review list is not empty,
    // and there must exist a review information of a restaurant that has the
    // same name as the given string, otherwise this method does nothing
    // MODIFIES: this
    // EFFECTS: update the review information of an existing restaurant in the list that has
    // the same name as the specified name, update the review information with new
    // rating, average cost, title and whether you want to go again
    public void updateRestaurantReview(String name, double newRating, int newAverageCost,
                                       String newTitle, boolean newGoAgain) {
        for (RestaurantReview rr : this.reviewList) {
            if (name.equals(rr.getName())) {
                rr.setRating(newRating);
                rr.setAverageCost(newAverageCost);
                rr.setTitle(newTitle);
                rr.setGoAgain(newGoAgain);
            }
        }
    }

    // EFFECTS: returns all the restaurants that the user wants to go again in the original
    // restaurant review list as a new list called goAgainRestaurants
    public List<RestaurantReview> getAllRestaurantsWantToGoAgain() {
        List<RestaurantReview> goAgainRestaurants = new ArrayList<>();
        for (RestaurantReview rr : this.reviewList) {
            if (rr.getGoAgain()) {
                goAgainRestaurants.add(rr);
            }
        }
        return goAgainRestaurants;
    }

    // REQUIRES: 0 <= minimumRating <= 5
    // EFFECTS: returns all the restaurants that have a rating of at least the minimum rating
    // specified in the argument as a new list called atLeastRatingRestaurants
    public List<RestaurantReview> getAllRestaurantsWithMinimumRating(double minimumRating) {
        List<RestaurantReview> atLeastRatingRestaurants = new ArrayList<>();
        for (RestaurantReview rr : this.reviewList) {
            if (rr.getRating() >= minimumRating) {
                atLeastRatingRestaurants.add(rr);
            }
        }
        return atLeastRatingRestaurants;
    }

    // EFFECTS: returns the restaurants review list
    public List<RestaurantReview> getReviewList() {
        return this.reviewList;
    }
}
