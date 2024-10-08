package ui;

import model.RestaurantReview;
import model.RestaurantReviewList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// This class represents a restaurant review application (console-based application) user interface.
// I use the workflow in the given example "Teller Application" from edX as a reference to design
// my user interface class. However, the codes are all written myself and I just use that project
// as a reference to get started.
// The project could be found on gitHub at: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Some necessary parts of this class is designed based on the
// given example JsonSerializationDemo. You can find this reference on
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.

public class RestaurantReviewApp {

    private Scanner scanner;
    private RestaurantReviewList restaurantReviewList;
    private static final String JSON_STORE = "./data/restaurantreviewlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: the constructor, runs the restaurant review application
    public RestaurantReviewApp() throws FileNotFoundException {
        scanner = new Scanner(System.in);
        restaurantReviewList = new RestaurantReviewList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean continueOrNot = true;
        String command = null;
        initialize();

        while (continueOrNot) {
            showMenuOptions();
            command = scanner.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                continueOrNot = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThanks for using the App, see you next time!");
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner
    private void initialize() {
        restaurantReviewList = new RestaurantReviewList();
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
    }

    // EFFECTS: displays all options for a user to use this application from the menu
    private void showMenuOptions() {
        System.out.println("\nHow would you like to use the restaurant review APP?"
                + " Please select from the following options:");
        System.out.println("\tadd -> add a new restaurant to the review information list");
        System.out.println("\tremove -> remove an existing restaurant from the review "
                + "information list");
        System.out.println("\tupdate -> update the review information of "
                + "an existing restaurant in the review list");
        System.out.println("\tview -> view the review information of all restaurants in the list");
        System.out.println("\tgo again -> filter and get all restaurants that"
                + " you want to go again from the review list");
        System.out.println("\thigher rating -> filter and get all restaurants that"
                + " have a rating score of at least some number of your choice from the review list");
        System.out.println("\tsave -> save your restaurant review list to file");
        System.out.println("\tload -> load your saved restaurant review list from file");
        System.out.println("\tquit -> quit the APP, thanks for using");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            processAddMethod();
        } else if (command.equals("remove")) {
            processRemoveMethod();
        } else if (command.equals("update")) {
            processUpdateMethod();
        } else if (command.equals("view")) {
            processViewMethod();
        } else if (command.equals("go again")) {
            processGoAgainMethod();
        } else if (command.equals("higher rating")) {
            processHigherRatingMethod();
        } else if (command.equals("save")) {
            saveRestaurantReviewList();
        } else if (command.equals("load")) {
            loadRestaurantReviewList();
        } else {
            System.out.println("This is not an valid option, please retry with an valid option "
                    + "from the menu.");
        }
    }

    // MODIFIES: this
    // EFFECTS: process the "add" method of the restaurant review application
    // that adds a new restaurant review to the application
    private void processAddMethod() {
        System.out.println("Enter the name of the restaurant.");
        String name = scanner.next();
        System.out.println("Enter the rating score of the restaurant on a scale of 5.");
        double rating = scanner.nextDouble();
        System.out.println("Enter the average cost of the restaurant (in whole CAD).");
        int averageCost = scanner.nextInt();
        System.out.println("Enter the title or keywords of the restaurant.");
        String title = scanner.next();
        System.out.println("Enter whether you want to go to the restaurant again, true for yes and false for no.");
        boolean goAgain = scanner.nextBoolean();

        if (rating < 0 || rating > 5) {
            System.out.print("the rating score must be in-between 0 and 5 points (including), please re-enter \n");
            System.out.println("Enter the rating score of the restaurant on a scale of 5.");
            rating = scanner.nextDouble();
        }

        restaurantReviewList.addRestaurantReview(name, rating, averageCost, title, goAgain);
        System.out.println("The review information of restaurant " + name
                + " has been successfully added to the review list.");
        System.out.println("You now have the following restaurants in your review list:");

        for (RestaurantReview rr : restaurantReviewList.getReviewList()) {
            System.out.print(rr.getName() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: process the "remove" method of the restaurant review application
    // that removes a restaurant review from the application
    private void processRemoveMethod() {
        System.out.println("Enter the name of the restaurant that you want to remove from the review list.");
        String name = scanner.next();
        restaurantReviewList.removeRestaurantReview(name);
        System.out.println("The review information of restaurant " + name
                + " has been successfully removed to the review list.");
        System.out.println("You now have the following restaurants in your review list:");

        for (RestaurantReview rr : restaurantReviewList.getReviewList()) {
            System.out.print(rr.getName() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: process the "update" method of the restaurant review application
    // that updates the review information of an existing restaurant
    private void processUpdateMethod() {
        System.out.println("Enter the name of the restaurant that you want to update the review information of.");
        String name = scanner.next();
        System.out.println("Enter the NEW rating score of the restaurant on a scale of 5.");
        double newRating = scanner.nextDouble();
        System.out.println("Enter the NEW average cost of the restaurant (in whole CAD).");
        int newAverageCost = scanner.nextInt();
        System.out.println("Enter the NEW title or keywords of the restaurant.");
        String newTitle = scanner.next();
        System.out.println("Update whether you want to go to the restaurant again, true for yes and false for no.");
        boolean newGoAgain = scanner.nextBoolean();

        if (newRating < 0 || newRating > 5) {
            System.out.print("the rating score must be in-between 0 and 5 points (including), please re-enter \n");
            System.out.println("Enter the NEW rating score of the restaurant on a scale of 5.");
            newRating = scanner.nextDouble();
        }

        restaurantReviewList.updateRestaurantReview(name, newRating, newAverageCost, newTitle, newGoAgain);
        System.out.println("The updated review information of restaurant" + name
                + " has been successfully added to the review list.");
        System.out.println("You now have the following restaurants in your review list:");

        for (RestaurantReview rr : restaurantReviewList.getReviewList()) {
            System.out.print(rr.getName() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: process the "view" method of the restaurant review application
    // that views all the restaurant review information stored in this APP
    private void processViewMethod() {
        if (restaurantReviewList.getReviewList().isEmpty()) {
            System.out.print("You do not have any restaurants reviewed in the list yet. Please add some.\n");
        } else {
            for (RestaurantReview rr : restaurantReviewList.getReviewList()) {
                if (rr.getGoAgain()) {
                    System.out.print("The restaurant " + rr.getName() + " has a rating score "
                            + rr.getRating() + " out of 5, and the average cost of CAD $"
                            + rr.getAverageCost() + ". The title of the restaurant is "
                            + rr.getTitle() + " and you want to go there again. \n");
                } else {
                    System.out.print("The restaurant " + rr.getName() + " has a rating score "
                            + rr.getRating() + " out of 5, and the average cost of CAD $"
                            + rr.getAverageCost() + ". The title of the restaurant is "
                            + rr.getTitle() + " and you don't want to go there again. \n");
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: process the "go again" method of the restaurant review application
    // that filters out all restaurants that you want to revisit in this application
    private void processGoAgainMethod() {
        List<RestaurantReview> goAgainRestaurants = restaurantReviewList.getAllRestaurantsWantToGoAgain();
        System.out.print("The following restaurants are the ones you want to go again: \n");
        if (goAgainRestaurants.isEmpty()) {
            System.out.print("You do not have any restaurants that you want to go again.\n");
        } else {
            for (RestaurantReview rr : goAgainRestaurants) {
                System.out.print("The restaurant " + rr.getName() + " has a rating score "
                        + rr.getRating() + " out of 5, and the average cost of CAD $"
                        + rr.getAverageCost() + ". The title of the restaurant is "
                        + rr.getTitle() + ".\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: process the "higher rating" method of the restaurant review application
    // that returns only those restaurants with a specified minimum rating score
    @SuppressWarnings("methodlength")
    private void processHigherRatingMethod() {
        System.out.println("Enter the minimum rating score of a restaurant on a scale of 5"
                + " that you want to filter from the list.");
        double minimumRating = scanner.nextDouble();

        if (minimumRating < 0 || minimumRating > 5) {
            System.out.print("the rating score must be in-between 0 and 5 points (including), please re-enter \n");
            System.out.println("Enter the minimum rating score of a restaurant on a scale of 5"
                    + " that you want to filter from the list.");
            minimumRating = scanner.nextDouble();
        }

        List<RestaurantReview> minRatingRestaurants =
                restaurantReviewList.getAllRestaurantsWithMinimumRating(minimumRating);
        System.out.print("The following restaurants are the ones that have a rating score of at least"
                + " as you specified: \n");

        if (minRatingRestaurants.isEmpty()) {
            System.out.print("You do not have any restaurants that fulfill your requirement.\n");
        } else {
            for (RestaurantReview rr : minRatingRestaurants) {
                if (rr.getGoAgain()) {
                    System.out.print("The restaurant " + rr.getName() + " has a rating score "
                            + rr.getRating() + " out of 5, and the average cost of CAD $"
                            + rr.getAverageCost() + ". The title of the restaurant is "
                            + rr.getTitle() + " and you want to go there again. \n");
                } else {
                    System.out.print("The restaurant " + rr.getName() + " has a rating score "
                            + rr.getRating() + " out of 5, and the average cost of CAD $"
                            + rr.getAverageCost() + ". The title of the restaurant is "
                            + rr.getTitle() + " and you don't want to go there again. \n");
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the restaurant review list to file
    private void saveRestaurantReviewList() {
        try {
            jsonWriter.open();
            jsonWriter.write(restaurantReviewList);
            jsonWriter.close();
            System.out.println("Successfully saved the restaurant review list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the restaurant review list from file
    private void loadRestaurantReviewList() {
        try {
            restaurantReviewList = jsonReader.read();
            System.out.println("Successfully loaded the restaurant review list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
