package sk.tuke.gamestudio.service;


import sk.tuke.gamestudio.entity.Rating;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RatingServiceRestClient implements RatingService {
    private static final String URL = "http://localhost:8080/rest/rating";


    @Override
    public void setRating(Rating rating) throws RatingException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(rating, MediaType.APPLICATION_JSON), Response.class);
        } catch (Exception e) {
            throw new RatingException("Error saving score", e);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(URL)
                    .path("/" + game)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<Integer>() {
                    });
        } catch (Exception e) {
            throw new RatingException("Error loading score", e);
        }

    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(URL)
                    .path("/" + game + "/" + player)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<Integer>() {
                    });
        } catch (Exception e) {
            throw new RatingException("Error loading score", e);
        }

    }


}