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
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            RestaurantReviewList rrl = new RestaurantReviewList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRestaurantReviewList() {
        try {
            RestaurantReviewList rrl = new RestaurantReviewList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRestaurantReviewList.json");
            writer.open();
            writer.write(rrl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRestaurantReviewList.json");
            rrl = reader.read();
            assertEquals(0, rrl.getReviewList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRestaurantReviewList() {
        try {
            RestaurantReviewList rrl = new RestaurantReviewList();
            rrl.addRestaurantReview("Restaurant A", 4.8, 50,
                    "good steak", true);
            rrl.addRestaurantReview("Restaurant B", 2, 20,
                    "slow service", false);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRestaurantReviewList.json");
            writer.open();
            writer.write(rrl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRestaurantReviewList.json");
            rrl = reader.read();
            List<RestaurantReview> restaurantReviews = rrl.getReviewList();
            assertEquals(2, restaurantReviews.size());
            checkRestaurantReview("Restaurant A", 4.8, 50,
                    "good steak", true, restaurantReviews.get(0));
            checkRestaurantReview("Restaurant B", 2, 20,
                    "slow service", false, restaurantReviews.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
