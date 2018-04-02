package sk.tuke.gamestudio.server.service;

import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.exception.RatingException;

/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */
public interface RatingService {
    void setRating(Rating rating) throws RatingException;
    int getAverageRating(String game) throws RatingException;
    int getRating(String game, String player) throws RatingException;
}
