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
class BattleshipsBrehuvControllerUser {
    @Autowired
    private UserService userService;

    private User loggedUser;


    @RequestMapping("/user")
    public String user(Model model) {
        return "login";
    }


    @RequestMapping("/battleships-brehuv-login")
    public String login(User user, Model model) {
        //user = userService.login(user.getUsername(), user.getPasswd());
        //loggedUser = user;
        return "battleships-brehuv-login";
    }


    @RequestMapping("/battleships-brehuv-register")
    public String register(User user, Model model) {
        //user = userService.register(user.getUsername(), user.getPasswd());
        //loggedUser = user;
        return "battleships-brehuv-register";
    }


    @RequestMapping("/battleships-brehuv-logout")
    public String logout() {
        loggedUser = null;
        return "battleships-brehuv-gamemenu";
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isLogged() {
        return loggedUser != null;
    }
}
