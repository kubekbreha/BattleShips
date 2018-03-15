package sk.tuke.gamestudio.service;

/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */
public class RatingException extends Exception {
    public RatingException(String message) {
        super(message);
    }

    public RatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
