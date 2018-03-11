package com.gamestudio.service;

import com.gamestudio.entity.Comment;

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

    @Override
    public void addComment(Comment comment) throws CommentException {

    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        return null;
    }
}
