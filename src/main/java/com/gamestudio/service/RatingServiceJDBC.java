package com.gamestudio.service;

import com.gamestudio.entity.Rating;

public class RatingServiceJDBC implements RatingService {

    public static final String URL = "jdbc:mysql://localhost/gamestudio";
    public static final String USER = "root";
    public static final String PASSWORD = "Hackathon16";

    @Override
    public void setRating(Rating rating) throws RatingException {
        
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        return 0;
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        return 0;
    }
}
