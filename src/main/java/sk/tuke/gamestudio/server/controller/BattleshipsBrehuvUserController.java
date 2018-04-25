package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.User;
import sk.tuke.gamestudio.service.UserService;

//http://localhost:8080/user
@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
class BattleshipsBrehuvUserController {
    @Autowired
    private UserService userService;

    private User loggedUser;

    @RequestMapping("/user")
    public String user(Model model) {
        return "login";
    }

    @RequestMapping("/login")
    public String login(User user, Model model) {
        user = userService.login(user.getUsername(), user.getPasswd());
        loggedUser = user;
        return "login";
    }

    @RequestMapping("/register")
    public String register(User user, Model model) {
        user = userService.register(user.getUsername(), user.getPasswd());
        loggedUser = user;
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        loggedUser = null;
        return "login";
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isLogged() {
        return loggedUser != null;
    }
}
