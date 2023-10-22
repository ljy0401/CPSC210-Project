package persistence;

import model.RestaurantReview;
import model.RestaurantReviewList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// The most parts of this class is designed based on the
// given example JsonSerializationDemo. You can find this reference on
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RestaurantReviewList rrl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRestaurantReviewList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRestaurantReviewList.json");
        try {
            RestaurantReviewList rrl = reader.read();
            assertEquals(0, rrl.getReviewList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRestaurantReviewList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRestaurantReviewList.json");
        try {
            RestaurantReviewList rrl = reader.read();
            List<RestaurantReview> restaurantReviews = rrl.getReviewList();
            assertEquals(2, restaurantReviews.size());
            checkRestaurantReview("Restaurant A", 4.8, 50,
                    "good steak", true, restaurantReviews.get(0));
            checkRestaurantReview("Restaurant B", 2, 20,
                    "slow service", false, restaurantReviews.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}