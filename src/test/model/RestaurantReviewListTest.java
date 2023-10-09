package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for restaurant review list class
public class RestaurantReviewListTest {
    private RestaurantReviewList testRestaurantReviewList;
    private RestaurantReview testRestaurantA;
    private RestaurantReview testRestaurantB;
    private RestaurantReview testRestaurantC;
    private RestaurantReview testRestaurantD;
    private RestaurantReview testRestaurantE;
    private RestaurantReview testRestaurantF;

    @BeforeEach
    void runBefore() {
        testRestaurantReviewList = new RestaurantReviewList();
        testRestaurantA = new RestaurantReview("restaurant A", 0,
                1, "dessert", false);
        testRestaurantB = new RestaurantReview("restaurant B", 3.5, 20,
                "breakfast", true);
        testRestaurantC = new RestaurantReview("restaurant C", 5, 50,
                "steak", true);
        testRestaurantD = new RestaurantReview("restaurant D", 2,
                100, "sushi", false);
        testRestaurantE = new RestaurantReview("restaurant E", 1.5, 30,
                "hot pot", false);
        testRestaurantF = new RestaurantReview("restaurant F", 4.8, 25,
                "ramen", true);
    }

    @Test
    public void constructorTest() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        assertEquals(0, reviewList.size());
        assertTrue(reviewList.isEmpty());
    }

    @Test
    public void addOneRestaurantReviewTest() {
        testRestaurantReviewList.addRestaurantReview("restaurant A", 0,
                1, "dessert", false);
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        assertEquals(1, reviewList.size());
        assertFalse(reviewList.isEmpty());
        RestaurantReview firstRestaurant = reviewList.get(0);
        assertFalse(firstRestaurant.getGoAgain());
        assertEquals(0, firstRestaurant.getRating());
        assertEquals("restaurant A", firstRestaurant.getName());
    }

    @Test
    public void addMultipleRestaurantsReviewTest() {
        testRestaurantReviewList.addRestaurantReview("restaurant A", 0,
                1, "dessert", false);
        testRestaurantReviewList.addRestaurantReview("restaurant B", 3.5, 20,
                "breakfast", true);
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        assertEquals(2, reviewList.size());
        assertFalse(reviewList.isEmpty());
        RestaurantReview firstRestaurant = reviewList.get(0);
        RestaurantReview secondRestaurant = reviewList.get(1);
        assertFalse(firstRestaurant.getGoAgain());
        assertEquals(0, firstRestaurant.getRating());
        assertEquals("restaurant A", firstRestaurant.getName());
        assertTrue(secondRestaurant.getGoAgain());
        assertEquals(20, secondRestaurant.getAverageCost());
        assertEquals("breakfast", secondRestaurant.getTitle());
    }

    @Test
    public void removeOneRestaurantReviewTest() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        reviewList.add(testRestaurantA);
        reviewList.add(testRestaurantB);
        reviewList.add(testRestaurantC);
        reviewList.add(testRestaurantD);
        reviewList.add(testRestaurantE);
        reviewList.add(testRestaurantF);
        testRestaurantReviewList.removeRestaurantReview("restaurant B");
        assertFalse(reviewList.contains(testRestaurantB));
        assertTrue(reviewList.contains(testRestaurantA));
        assertTrue(reviewList.contains(testRestaurantC));
        assertTrue(reviewList.contains(testRestaurantD));
        assertTrue(reviewList.contains(testRestaurantE));
        assertTrue(reviewList.contains(testRestaurantF));
        assertEquals(5, reviewList.size());
        assertFalse(reviewList.isEmpty());
        RestaurantReview secondRestaurant = reviewList.get(1);
        assertEquals("restaurant C", secondRestaurant.getName());
    }

    @Test
    public void removeMultipleRestaurantsReviewTest() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        reviewList.add(testRestaurantA);
        reviewList.add(testRestaurantB);
        reviewList.add(testRestaurantC);
        reviewList.add(testRestaurantD);
        reviewList.add(testRestaurantE);
        reviewList.add(testRestaurantF);
        testRestaurantReviewList.removeRestaurantReview("restaurant A");
        testRestaurantReviewList.removeRestaurantReview("restaurant F");
        assertFalse(reviewList.contains(testRestaurantA));
        assertFalse(reviewList.contains(testRestaurantF));
        assertTrue(reviewList.contains(testRestaurantB));
        assertTrue(reviewList.contains(testRestaurantD));
        assertTrue(reviewList.contains(testRestaurantC));
        assertTrue(reviewList.contains(testRestaurantE));
        assertEquals(4, reviewList.size());
        assertFalse(reviewList.isEmpty());
        RestaurantReview firstRestaurant = reviewList.get(0);
        assertEquals("restaurant B", firstRestaurant.getName());
    }

    @Test
    public void updateRestaurantReviewOnceTest() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        reviewList.add(testRestaurantA);
        reviewList.add(testRestaurantB);
        reviewList.add(testRestaurantC);
        testRestaurantReviewList.updateRestaurantReview("restaurant A",
        4, 15, "bubble tea", true);
        assertEquals(3, reviewList.size());
        assertFalse(reviewList.isEmpty());
        RestaurantReview updatedRestaurant = reviewList.get(0);
        assertEquals("restaurant A", updatedRestaurant.getName());
        assertEquals(4, updatedRestaurant.getRating());
        assertTrue(updatedRestaurant.getGoAgain());
        assertEquals("bubble tea", updatedRestaurant.getTitle());
        assertEquals(15, updatedRestaurant.getAverageCost());
        assertTrue(reviewList.contains(testRestaurantB));
        assertTrue(reviewList.contains(testRestaurantC));
    }

    @Test
    public void updateRestaurantReviewMultipleTimesTest() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        reviewList.add(testRestaurantA);
        reviewList.add(testRestaurantB);
        reviewList.add(testRestaurantC);
        testRestaurantReviewList.updateRestaurantReview("restaurant B",
                4, 15, "bubble tea", true);
        testRestaurantReviewList.updateRestaurantReview("restaurant B",
                0.5, 25, "sandwich", false);
        assertEquals(3, reviewList.size());
        assertFalse(reviewList.isEmpty());
        RestaurantReview updatedRestaurant = reviewList.get(1);
        assertEquals("restaurant B", updatedRestaurant.getName());
        assertEquals(0.5, updatedRestaurant.getRating());
        assertFalse(updatedRestaurant.getGoAgain());
        assertEquals("sandwich", updatedRestaurant.getTitle());
        assertEquals(25, updatedRestaurant.getAverageCost());
        assertTrue(reviewList.contains(testRestaurantA));
        assertTrue(reviewList.contains(testRestaurantC));
    }

    @Test
    public void getAllRestaurantsWantToGoAgainWithNoRestaurantsWantToGo() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        reviewList.add(testRestaurantA);
        reviewList.add(testRestaurantD);
        reviewList.add(testRestaurantE);
        List<RestaurantReview> wantToGoRestaurants =
        testRestaurantReviewList.getAllRestaurantsWantToGoAgain();
        assertEquals(0, wantToGoRestaurants.size());
        assertTrue(wantToGoRestaurants.isEmpty());
    }

    @Test
    public void getAllRestaurantsWantToGoAgainWithAllRestaurantsWantToGo() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        reviewList.add(testRestaurantB);
        reviewList.add(testRestaurantC);
        reviewList.add(testRestaurantF);
        List<RestaurantReview> wantToGoRestaurants =
                testRestaurantReviewList.getAllRestaurantsWantToGoAgain();
        assertEquals(3, wantToGoRestaurants.size());
        assertFalse(wantToGoRestaurants.isEmpty());
        assertTrue(wantToGoRestaurants.contains(testRestaurantB));
        assertTrue(wantToGoRestaurants.contains(testRestaurantC));
        assertTrue(wantToGoRestaurants.contains(testRestaurantF));
        assertEquals(testRestaurantB, wantToGoRestaurants.get(0));
        assertEquals(testRestaurantC, wantToGoRestaurants.get(1));
        assertEquals(testRestaurantF, wantToGoRestaurants.get(2));
    }

    @Test
    public void getAllRestaurantsWantToGoAgainWithSomeRestaurantsWantToGo() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        reviewList.add(testRestaurantA);
        reviewList.add(testRestaurantB);
        reviewList.add(testRestaurantC);
        reviewList.add(testRestaurantD);
        reviewList.add(testRestaurantE);
        reviewList.add(testRestaurantF);
        List<RestaurantReview> wantToGoRestaurants =
                testRestaurantReviewList.getAllRestaurantsWantToGoAgain();
        assertEquals(3, wantToGoRestaurants.size());
        assertFalse(wantToGoRestaurants.isEmpty());
        assertTrue(wantToGoRestaurants.contains(testRestaurantB));
        assertTrue(wantToGoRestaurants.contains(testRestaurantC));
        assertTrue(wantToGoRestaurants.contains(testRestaurantF));
        assertFalse(wantToGoRestaurants.contains(testRestaurantA));
        assertFalse(wantToGoRestaurants.contains(testRestaurantD));
        assertFalse(wantToGoRestaurants.contains(testRestaurantE));
        assertEquals(testRestaurantB, wantToGoRestaurants.get(0));
        assertEquals(testRestaurantC, wantToGoRestaurants.get(1));
        assertEquals(testRestaurantF, wantToGoRestaurants.get(2));
    }

    @Test
    public void restaurantsWithAtLeastRatingLowRatingTest() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        reviewList.add(testRestaurantA);
        reviewList.add(testRestaurantB);
        reviewList.add(testRestaurantC);
        reviewList.add(testRestaurantD);
        reviewList.add(testRestaurantE);
        reviewList.add(testRestaurantF);
        List<RestaurantReview> witMinimumRatingRestaurants =
                testRestaurantReviewList.getAllRestaurantsWithMinimumRating(0);
        assertEquals(6, witMinimumRatingRestaurants.size());
        assertFalse(witMinimumRatingRestaurants.isEmpty());
        assertTrue(witMinimumRatingRestaurants.contains(testRestaurantB));
        assertTrue(witMinimumRatingRestaurants.contains(testRestaurantC));
        assertTrue(witMinimumRatingRestaurants.contains(testRestaurantF));
        assertTrue(witMinimumRatingRestaurants.contains(testRestaurantA));
        assertTrue(witMinimumRatingRestaurants.contains(testRestaurantD));
        assertTrue(witMinimumRatingRestaurants.contains(testRestaurantE));
        assertEquals(testRestaurantA, witMinimumRatingRestaurants.get(0));
        assertEquals(testRestaurantB, witMinimumRatingRestaurants.get(1));
        assertEquals(testRestaurantC, witMinimumRatingRestaurants.get(2));
    }

    @Test
    public void restaurantsWithAtLeastRatingMediumRatingTest() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        reviewList.add(testRestaurantA);
        reviewList.add(testRestaurantB);
        reviewList.add(testRestaurantC);
        reviewList.add(testRestaurantD);
        reviewList.add(testRestaurantE);
        reviewList.add(testRestaurantF);
        List<RestaurantReview> witMinimumRatingRestaurants =
                testRestaurantReviewList.getAllRestaurantsWithMinimumRating(3.5);
        assertEquals(3, witMinimumRatingRestaurants.size());
        assertFalse(witMinimumRatingRestaurants.isEmpty());
        assertTrue(witMinimumRatingRestaurants.contains(testRestaurantB));
        assertTrue(witMinimumRatingRestaurants.contains(testRestaurantC));
        assertTrue(witMinimumRatingRestaurants.contains(testRestaurantF));
        assertFalse(witMinimumRatingRestaurants.contains(testRestaurantA));
        assertFalse(witMinimumRatingRestaurants.contains(testRestaurantD));
        assertFalse(witMinimumRatingRestaurants.contains(testRestaurantE));
        assertEquals(testRestaurantB, witMinimumRatingRestaurants.get(0));
        assertEquals(testRestaurantC, witMinimumRatingRestaurants.get(1));
        assertEquals(testRestaurantF, witMinimumRatingRestaurants.get(2));
    }

    @Test
    public void restaurantsWithAtLeastRatingHighRatingTest() {
        List<RestaurantReview> reviewList = testRestaurantReviewList.getReviewList();
        reviewList.add(testRestaurantA);
        reviewList.add(testRestaurantB);
        reviewList.add(testRestaurantD);
        reviewList.add(testRestaurantE);
        reviewList.add(testRestaurantF);
        List<RestaurantReview> witMinimumRatingRestaurants =
                testRestaurantReviewList.getAllRestaurantsWithMinimumRating(5);
        assertEquals(0, witMinimumRatingRestaurants.size());
        assertTrue(witMinimumRatingRestaurants.isEmpty());
    }
}
