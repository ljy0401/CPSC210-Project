package persistence;

import model.RestaurantReview;

import static org.junit.jupiter.api.Assertions.assertEquals;

// The most parts of this class is designed based on the
// given example JsonSerializationDemo. You can find this reference on
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
public class JsonTest {
    protected void checkRestaurantReview(String name, double ranking, int averageCost,
                                         String title, boolean goAgain, RestaurantReview rr) {
        assertEquals(name, rr.getName());
        assertEquals(ranking, rr.getRating());
        assertEquals(averageCost, rr.getAverageCost());
        assertEquals(title, rr.getTitle());
        assertEquals(goAgain, rr.getGoAgain());
    }
}
