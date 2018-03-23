package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;

import java.io.*;
import java.util.*;

import static sk.tuke.gamestudio.game.battleships.core.board.Board.GAME_NAME;

/**
 * Created by Kubo Brehuv with <3 (23.3.2018)
 */
public class CommentServiceFile implements CommentService {

    /**
     * Get file name.
     *
     * @param game name.
     * @return filename.
     */
    private String getFileName(String game) {
        return System.getProperty("user.home") + "/" + game+"-comment" + ".comment";
    }

    /**
     * Add comment object to file.
     *
     * @param comment which will be added.
     * @throws CommentException
     */
    @Override
    public void addComment(Comment comment) throws CommentException {
        List<Comment> comments = getComments(GAME_NAME);
        comments.add(comment);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFileName(GAME_NAME)))) {
            oos.writeObject(comments);
        } catch (Exception e) {
            throw new CommentException("Error saving comment", e);
        }
    }

    /**
     * Get list of comment objects from file.
     *
     * @param game name
     * @return list of comments.
     * @throws CommentException
     */
    @Override
    public List<Comment> getComments(String game) throws CommentException {
        List<Comment> comments = new ArrayList<>();
        File file = new File(getFileName(game));
        if (file.exists()) {
            try (ObjectInputStream ois
                         = new ObjectInputStream(
                    new FileInputStream(file))) {
                comments = (List<Comment>) ois.readObject();
            } catch (Exception e) {
                throw new CommentException("Error loading comments", e);
            }
        }
        return comments;
    }


    public static void main(String[] args) throws Exception {
        Comment comment = new Comment("jaro", GAME_NAME,  "Really great game", new Date());
        CommentService commentService = new CommentServiceFile();
        commentService.addComment(comment);
        System.out.println(commentService.getComments(GAME_NAME));
    }
}
