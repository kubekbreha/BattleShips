package sk.tuke.gamestudio.service;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

public class CommentServiceRestClient implements CommentService {
    private static final String URL = "http://localhost:8080/rest/comment";

    @Override
    public void addComment(Comment comment) throws CommentException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(comment, MediaType.APPLICATION_JSON), Response.class);
        } catch (Exception e) {
            throw new CommentException("Error saving comment", e);
        }
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(URL)
                    .path("/" + game)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Comment>>() {
                    });
        } catch (Exception e) {
            throw new CommentException("Error loading comment", e);
        }
    }
}