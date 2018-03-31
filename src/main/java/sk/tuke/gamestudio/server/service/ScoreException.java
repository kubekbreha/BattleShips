package sk.tuke.gamestudio.server.service;

/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */
public class ScoreException extends RuntimeException {
    public ScoreException(String message) {
        super(message);
    }

    public ScoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
