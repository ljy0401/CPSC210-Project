package ui;

import model.RestaurantReview;
import model.RestaurantReviewList;

import java.util.Scanner;

public class RestaurantReviewApp {

    private Scanner scanner;
    private RestaurantReviewList restaurantReviewList;

    // EFFECTS: runs the restaurant review application
    public RestaurantReviewApp() {
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
                + "Please select from the following options:");
        System.out.println("\tadd -> add a new restaurant to the review information list");
        System.out.println("\tremove -> remove an existing restaurant from the review "
                + "information list");
        System.out.println("\tupdate -> update the review information of "
                + "an existing restaurant in the review list");
        System.out.println("\tgo again -> filter and get all restaurants that"
                + " you want to go again from the review list");
        System.out.println("\thigher rating -> filter and get all restaurants that"
                + " have a rating score of at least some number of your choice from the review list");
        System.out.println("\tquit -> quit the APP, thanks for using");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            processAddMethod();
//        } else if (command.equals("w")) {
//            doWithdrawal();
//        } else if (command.equals("t")) {
//            doTransfer();
        }  else {
            System.out.println("This is not an valid option, please retry with an valid option "
                    + "from the menu.");
        }
    }

    private void processAddMethod() {
        System.out.print("Enter the name of the restaurant.");
        String name = scanner.next();
        System.out.print("Enter the rating score of the restaurant on a scale of 5.");
        double rating = scanner.nextDouble();
        System.out.print("Enter the average cost of the restaurant (in whole CAD).");
        int averageCost = scanner.nextInt();
        System.out.print("Enter the title or keywords of the restaurant.");
        String title = scanner.next();
        System.out.print("Enter whether you want to go to the restaurant again, true for yes and false for no.");
        boolean goAgain = scanner.nextBoolean();

        if (rating < 0 || rating > 5) {
            System.out.print("the rating score must be in-between 0 and 5 points (including), please re-enter \n");
            System.out.print("Enter the rating score of the restaurant on a scale of 5.");
            rating = scanner.nextDouble();
        }
        if (averageCost < 0) {
            System.out.print("the average cannot be negative, please re-enter \n");
            System.out.print("Enter the average cost of the restaurant (in whole CAD).");
            averageCost = scanner.nextInt();
        }

        restaurantReviewList.addRestaurantReview(name, rating, averageCost, title, goAgain);
        System.out.printf("The review information of " + name + " has been successfully added to the review list.");
    }

}
