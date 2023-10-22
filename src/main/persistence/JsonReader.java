package persistence;

import model.RestaurantReview;
import model.RestaurantReviewList;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads the restaurant review list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RestaurantReviewList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRestaurantReviewList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the restaurant review list from JSON object and returns it
    private RestaurantReviewList parseRestaurantReviewList(JSONObject jsonObject) {
        RestaurantReviewList rrl = new RestaurantReviewList();
        addRestaurantReviews(rrl, jsonObject);
        return rrl;
    }

    // MODIFIES: restaurant review list (rrl)
    // EFFECTS: parses restaurant reviews from JSON object and adds them to the review list
    private void addRestaurantReviews(RestaurantReviewList rrl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("restaurant reviews");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addRestaurantReview(rrl, nextThingy);
        }
    }

    // MODIFIES: restaurant review list (rrl)
    // EFFECTS: parses a restaurant review from JSON object and adds it to the review list
    private void addRestaurantReview(RestaurantReviewList rrl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double rating = Double.parseDouble(jsonObject.getString("rating"));
        int averageCost = Integer.parseInt(jsonObject.getString("average cost"));
        String title = jsonObject.getString("title");
        boolean goAgain = Boolean.parseBoolean(jsonObject.getString("go again"));
        rrl.addRestaurantReview(name, rating, averageCost, title, goAgain);
    }
}

