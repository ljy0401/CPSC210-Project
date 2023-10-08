package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for restaurant review class
class RestaurantReviewTest {
    private RestaurantReview restaurantA;
    private RestaurantReview restaurantB;
    private RestaurantReview restaurantC;

    @BeforeEach
    void runBefore() {
        restaurantA = new RestaurantReview("restaurant A", 0,
                1, "dessert", false);
        restaurantB = new RestaurantReview("restaurant B", 3.5, 20,
                "breakfast", true);
        restaurantC = new RestaurantReview("restaurant C", 5, 50,
                "steak", true);
    }

    @Test
    public void constructorTestA() {
        assertEquals("restaurant A", restaurantA.getName());
        assertEquals(0, restaurantA.getRating());
        assertEquals(1, restaurantA.getAverageCost());
        assertEquals("dessert", restaurantA.getTitle());
        assertFalse(restaurantA.getGoAgain());
    }

    @Test
    public void constructorTestB() {
        assertEquals("restaurant B", restaurantB.getName());
        assertEquals(3.5, restaurantB.getRating());
        assertEquals(20, restaurantB.getAverageCost());
        assertEquals("breakfast", restaurantB.getTitle());
        assertTrue(restaurantB.getGoAgain());
    }

    @Test
    public void constructorTestC() {
        assertEquals("restaurant C", restaurantC.getName());
        assertEquals(5, restaurantC.getRating());
        assertEquals(50, restaurantC.getAverageCost());
        assertEquals("steak", restaurantC.getTitle());
        assertTrue(restaurantC.getGoAgain());
    }
}