package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// This class represents a collection (list) of review information of restaurants.

// The last part of this class is designed based on the
// given example JsonSerializationDemo. You can find this reference on
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.

public class RestaurantReviewList implements Writable {
    private List<RestaurantReview> reviewList;
    private EventLog eventLog = EventLog.getInstance();

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

    // REQUIRES: the name is not an empty string; 0 <= rating <= 5;
    // averageCost > 0 in whole CAD; the title is not an empty string
    // MODIFIES: this
    // EFFECTS: add a new review information of a restaurant with the given information
    // to the restaurant review list, this method is specific for the GUI action listener
    public void addRestaurantReviewForGUI(String name, double rating, int averageCost,
                                    String title, boolean goAgain) {
        this.reviewList.add(new RestaurantReview(name, rating, averageCost, title, goAgain));
        eventLog.logEvent(new Event("A new restaurant was added to the "
                + "restaurant review list!"));
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
        eventLog.logEvent(new Event("A restaurant was removed from the "
                + "restaurant review list!"));
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
                eventLog.logEvent(new Event("The review information of a "
                        + "restaurant was updated in the restaurant review list!"));
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
        eventLog.logEvent(new Event("You viewed all the restaurants "
                + "that you want to revisit from the restaurant review list!"));
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
        eventLog.logEvent(new Event("You viewed all the restaurants that "
                + "have at least a specified minimum rating score from the restaurant review list!"));
        return atLeastRatingRestaurants;
    }

    // EFFECTS: returns the restaurants review list
    public List<RestaurantReview> getReviewList() {
        return this.reviewList;
    }

    // EFFECTS: return the view message for the GUI when you want to view all the
    // restaurant review information from the review list
    @SuppressWarnings("methodlength")
    public String getViewMessageForGUI() {
        List<RestaurantReview> restaurantReviews = this.getReviewList();
        String viewMessage = "";
        if (restaurantReviews.isEmpty()) {
            viewMessage = "You don't have any restaurants in your restaurant review "
                    + "application right now! Please add some first!";
        } else {
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
        }
        eventLog.logEvent(new Event("All the restaurant review information "
                + "from the restaurant review list was displayed (or automatically updated and "
                + "displayed after adding or removing) in the APP!"));
        return viewMessage;
    }

    // EFFECTS: get the event log
    public EventLog getEventLog() {
        return this.eventLog;
    }

    @Override
    // EFFECTS: returns the restaurant review list (a JSON array) as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("restaurant reviews", restaurantReviewsToJson());
        return json;
    }

    // EFFECTS: returns restaurant reviews in this review list as a JSON array
    private JSONArray restaurantReviewsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (RestaurantReview rr : reviewList) {
            jsonArray.put(rr.toJson());
        }
        return jsonArray;
    }
}
