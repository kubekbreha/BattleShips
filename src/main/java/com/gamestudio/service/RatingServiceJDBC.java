package com.gamestudio.service;

import com.gamestudio.entity.Rating;

import java.sql.*;

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

    public static final String INSERT_RATING =
            "INSERT INTO rating (player, game, rating, ratedon) VALUES (?, ?, ?, ?)";

    public static final String SELECT_RATING =
            "SELECT player, game, rating, ratedon FROM rating WHERE game = ? DESC LIMIT 10;";

    @Override
    public void setRating(Rating rating) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_RATING)) {
                ps.setString(1, rating.getGame());
                ps.setString(2, rating.getPlayer());
                ps.setInt(3, rating.getRating());
                ps.setDate(4, new Date(rating.getRatedon().getTime()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RatingException("Error saving rating", e);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        int ratingCount = 0;
        int ratingSum = 0;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_RATING)) {
                ps.setString(1, game);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Rating rating = new Rating(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getTimestamp(4)
                        );
                        ratingSum += rating.getRating();
                        ratingCount++;
                    }
                }
            }
        } catch (SQLException e) {
            throw new ScoreException("Error loading player rating", e);
        }
        return ratingSum/ratingCount;
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        int ratingVal = 0;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_RATING)) {
                ps.setString(1, game);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Rating rating = new Rating(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getTimestamp(4)
                        );
                        if (rating.getPlayer().equals("player")) {
                            ratingVal = rating.getRating();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new ScoreException("Error loading player rating", e);
        }
        return ratingVal;
    }
}
