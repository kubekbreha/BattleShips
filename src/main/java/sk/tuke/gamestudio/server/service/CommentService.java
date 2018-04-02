package sk.tuke.gamestudio.server.service;


import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.exception.CommentException;

import java.util.List;

/**
 * Created by Kubo Brehuv with <3 (11.3.2018)
 */
public interface CommentService {
    void addComment(Comment comment) throws CommentException;
    List<Comment> getComments(String game) throws CommentException;
}