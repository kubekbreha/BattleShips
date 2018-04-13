package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentException;
import sk.tuke.gamestudio.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/comment")
public class CommentServiceRest {

    @Autowired
    private CommentService commentService;

    //http://localhost:8080/rest/comment
    @POST
    @Consumes("application/json")
    public Response addComment(Comment comment) throws CommentException {
        commentService.addComment(comment);
        return Response.ok().build();
    }

    //http://localhost:8080/rest/score/comment
    @GET
    @Path("/{game}")
    @Produces("application/json")
    public List<Comment> getCommenrs(@PathParam("game") String game) throws CommentException {
        return commentService.getComments(game);
    }
}
