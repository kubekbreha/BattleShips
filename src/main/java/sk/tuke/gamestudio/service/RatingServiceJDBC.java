package sk.tuke.gamestudio.service;

/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    CREATE TABLE rating (
        player VARCHAR(64) NOT NULL,
        game VARCHAR(64) NOT NULL,
        rating INTEGER NOT NULL,
        ratedon TIMESTAMP NOT NULL
    );
     */

public class RatingServiceJDBC implements RatingService {

    public static final String URL = "jdbc:mysql://localhost/gamestudio";
    public static final String USER = "root";
    public static final String PASSWORD = "Hackathon16";

    /**
     * Insert rating to database command.
     */
    public static final String INSERT_RATING =
            "INSERT INTO rating ( player, game, rating, ratedon) VALUES (?, ?, ?, ?)";

    /**
     * Get rating from database command.
     */
    public static final String SELECT_RATING =
            "SELECT player, game, rating, ratedon FROM rating WHERE game = ?;";

    /**
     * Add rating to database.
     * @param rating which will be added.
     * @throws RatingException
     */
    @Override
    public void setRating(Rating rating) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_RATING)) {
                ps.setString(1, rating.getPlayer());
                ps.setString(2, rating.getGame());
                ps.setInt(3, rating.getRating());
                ps.setDate(4, new Date(rating.getRatedon().getTime()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RatingException("Error saving rating", e);
        }
    }


    /**
     * Get average rating from database.
     * @param game to which were ratings added.
     * @return integer value.
     * @throws RatingException
     */
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


    /**
     * Get rating from database.
     * @param game to which was rating added.
     * @return rating integer value.
     * @throws RatingException
     */
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


    /**
     * Get ratings from database.
     * @param game to which was rating added.
     * @return list od rating objects.
     * @throws RatingException
     */
    @Override
    public List<Rating> getRatings(String game) throws RatingException {
        List<Rating> ratings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SELECT_RATING)){
                ps.setString(1, game);
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        Rating rating = new Rating(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getTimestamp(4)
                        );
                        ratings.add(rating);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RatingException("Error loading ratings", e);
        }
        return ratings;
    }
}
