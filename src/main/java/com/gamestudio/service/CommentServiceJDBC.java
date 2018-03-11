package com.gamestudio.service;

import com.gamestudio.entity.Comment;

import java.util.List;

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
