package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static sk.tuke.gamestudio.game.battleships.core.board.Board.GAME_NAME;

/**
 * Created by Kubo Brehuv with <3 (23.3.2018)
 */
public class RatingServiceFile implements RatingService {

    /**
     * Get file name.
     *
     * @param game name.
     * @return filename.
     */
    private String getFileName(String game) {
        return System.getProperty("user.home") + "/" + game+"-rating" + ".rating";
    }

    /**
     * Add rating object to file.
     *
     * @param rating which will be added.
     * @throws RatingException
     */
    @Override
    public void setRating(Rating rating) throws RatingException {
        List<Rating> ratings = getRatings(GAME_NAME);
        ratings.add(rating);
        Collections.sort(ratings);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFileName(GAME_NAME)))) {
            oos.writeObject(ratings);
        } catch (Exception e) {
            throw new RatingException("Error saving rating", e);
        }
    }

    //TODO
    @Override
    public int getAverageRating(String game) throws RatingException {
        return 0;
    }

    //TODO
    @Override
    public int getRating(String game, String player) throws RatingException {
        return 0;
    }

    /**
     * Get list of ratings objects from file.
     *
     * @param game name
     * @return list of ratings.
     * @throws RatingException
     */
    @Override
    public List<Rating> getRatings(String game) throws RatingException {
        List<Rating> ratings = new ArrayList<>();
        File file = new File(getFileName(game));
        if (file.exists()) {
            try (ObjectInputStream ois
                         = new ObjectInputStream(
                    new FileInputStream(file))) {
                ratings = (List<Rating>) ois.readObject();
            } catch (Exception e) {
                throw new RatingException("Error loading ratings", e);
            }
        }
        return ratings;
    }

    public static void main(String[] args) throws Exception {
        Rating rating = new Rating("jaro", GAME_NAME,  2, new Date());
        RatingService ratingService = new RatingServiceFile();
        ratingService.setRating(rating);
        System.out.println(ratingService.getRatings(GAME_NAME));
    }
}
