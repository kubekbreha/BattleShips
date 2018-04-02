package sk.tuke.gamestudio.server.service;


import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.entity.Rating;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RatingServiceRestClient implements RatingService {
    private static final String URL = "http://localhost:8080/rest/comment";


    @Override
    public void setRating(Rating rating) throws RatingException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(rating, MediaType.APPLICATION_JSON), Response.class);
        } catch (Exception e) {
            throw new RatingException("Error saving comment", e);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        List<Rating> ratings = null;
        try {
            Client client = ClientBuilder.newClient();
            ratings = client.target(URL)
                    .path("/" + game)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Rating>>() {
                    });
        } catch (Exception e) {
            throw new RatingException("Error loading ratings", e);
        }
        int ratingSum = 0;
        int ratingCount = 0;

        if (ratings != null) {
            for (Rating rating : ratings) {
                ratingSum += rating.getRating();
                ratingCount++;
            }
            return ratingSum / ratingCount;
        }else {
            return 0;
        }
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        return 0;
    }

    @Override
    public List<Rating> getRatings(String game) throws RatingException {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(URL)
                    .path("/" + game)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Rating>>() {
                    });
        } catch (Exception e) {
            throw new RatingException("Error loading ratings", e);
        }
    }
}