package com.gamestudio.service;

import com.gamestudio.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    CREATE TABLE comment (
        player VARCHAR(64) NOT NULL,
        game VARCHAR(64) NOT NULL,
        comment VARCHAR(64) NOT NULL,
        commentedon TIMESTAMP NOT NULL
    );
     */

//INSERT INTO coment (player, game, comment, commentedon) VALUES ('kubo', 'battleships', 'what a perfect game', '2017-03-02 14:30')
//SELECT player, game, comment, commentedon FROM score ORDER BY commentedon DESC LIMIT 10;

public class CommentServiceJDBC implements CommentService {

    public static final String URL = "jdbc:mysql://localhost/gamestudio";
    public static final String USER = "root";
    public static final String PASSWORD = "Hackathon16";

    public static final String INSERT_COMMENT =
            "INSERT INTO comment (player, game, comment, commentedon) VALUES (?, ?, ?, ?)";

    public static final String SELECT_COMMENT =
            "SELECT player, game, comment, commentedon FROM score ORDER BY points DESC LIMIT 10;";

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
            throw new ScoreException("Error saving  comment", e);
        }
    }

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
            throw new ScoreException("Error loading comment", e);
        }
        return comments;
    }
}
