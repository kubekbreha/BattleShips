package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.entity.User;
import sk.tuke.gamestudio.service.ScoreException;
import sk.tuke.gamestudio.service.UserService;

import javax.ws.rs.*;


@Path("/user")
class UserServiceRest {
    @Autowired
    private UserService userService;

    //http://localhost:8080/rest/user/register
    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public User register(User user) throws ScoreException {
        return userService.register(user.getUsername(), user.getPassword());
    }

    //http://localhost:8080/rest/user/login
    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public User login(User user) throws ScoreException {
        return userService.login(user.getUsername(), user.getPassword());
    }
}
