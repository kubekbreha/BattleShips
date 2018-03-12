package com.gamestudio.service;

import com.gamestudio.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    CREATE TABLE comment (
        game VARCHAR(64) NOT NULL,
        player VARCHAR(64) NOT NULL,
        comment VARCHAR(64) NOT NULL,
        commentedon TIMESTAMP NOT NULL
    );
     */


/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */
public class CommentServiceJDBC implements CommentService {

    public static final String URL = "jdbc:mysql://localhost/gamestudio";
    public static final String USER = "root";
    public static final String PASSWORD = "Hackathon16";

    /**
     * Insert comment to database command.
     */
    public static final String INSERT_COMMENT =
            "INSERT INTO comment ( game, player, comment, commentedon) VALUES (?, ?, ?, ?)";


    /**
     * Get comment from database command.
     */
    public static final String SELECT_COMMENT =
            "SELECT game, player, comment, commentedon FROM comment WHERE game = ?;";

    /**
     * Add comment to database.
     * @param comment which will be added.
     * @throws CommentException
     */
    @Override
    public void addComment(Comment comment) throws CommentException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT)){
                ps.setString(1, comment.getGame());
                ps.setString(2, comment.getPlayer());
                ps.setString(3, comment.getComment());
                ps.setDate(4, new Date(comment.getCommentedOn().getTime()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new CommentException("Error saving comment", e);
        }
    }

    /**
     * Get comment from database.
     * @param game to which was comment added.
     * @return list od comment objects.
     * @throws CommentException
     */
    @Override
    public List<Comment> getComments(String game) throws CommentException {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try(PreparedStatement ps = connection.prepareStatement(SELECT_COMMENT)){
                ps.setString(1, game);
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        Comment comment = new Comment(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getTimestamp(4)
                        );
                        comments.add(comment);
                    }
                }
            }
        } catch (SQLException e) {
            throw new CommentException("Error loading comment", e);
        }
        return comments;
    }


}
