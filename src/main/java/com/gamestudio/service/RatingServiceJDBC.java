package com.gamestudio.service;

import com.gamestudio.entity.Rating;

/*
    CREATE TABLE rating (
        player VARCHAR(64) NOT NULL,
        game VARCHAR(64) NOT NULL,
        rating INTEGER NOT NULL,
        ratedon TIMESTAMP NOT NULL
    );
     */

//INSERT INTO rating (player, game, rating, ratedon) VALUES ('kubo' ,'battleships', 5, '2017-03-02 14:30')
//SELECT player, game, rating, ratedon FROM rating ORDER BY ratedon DESC LIMIT 10;

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
