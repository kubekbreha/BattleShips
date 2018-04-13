package sk.tuke.gamestudio.service;

/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */
public class CommentException extends Exception {
    public CommentException(String message) {
        super(message);
    }

    public CommentException(String message, Throwable cause) {
        super(message, cause);
    }
}
