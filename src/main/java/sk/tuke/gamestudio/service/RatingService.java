package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.util.List;

/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */
public interface RatingService {
    void setRating(Rating rating) throws RatingException;
    int getAverageRating(String game) throws RatingException;
    int getRating(String game, String player) throws RatingException;
    List<Rating> getRatings(String game) throws RatingException;
}
