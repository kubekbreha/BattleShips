package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import sk.tuke.gamestudio.entity.User;
import sk.tuke.gamestudio.game.battleships.brehuv.webui.WebUISinglePlayer;
import sk.tuke.gamestudio.service.UserService;

import javax.validation.Valid;

//http://localhost:8080/user
@Controller
//@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class BattleshipsBrehuvControllerUser extends WebMvcConfigurerAdapter {

    @Autowired
    private UserService userService;

    private WebUISinglePlayer webUISinglePlayer = BattleshipsBrehuvControllerSinglePlayerSetup.getUIClass();

    private static User loggedUser;
    private User formUser = new User();
    private boolean alreadyRegistered = false;


    @RequestMapping("/battleships-brehuv-logout")
    public String logout( Model model) {
        model.addAttribute("webUI", webUISinglePlayer);
        loggedUser = null;
        formUser = new User();
        alreadyRegistered = false;
        return "battleships-brehuv-logout";
    }

    @RequestMapping(value="/battleships-brehuv-login", method=RequestMethod.POST)
    public String loginSubmit(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.err.println("FORM HAS ERRORS");
            model.addAttribute("user", formUser);
            return "battleships-brehuv-login";
        }
        user = userService.login(user.getUsername(), user.getPassword());
        loggedUser = user;

        model.addAttribute("user", formUser);
        return loggedUser == null ? "battleships-brehuv-login" : "battleships-brehuv-login";
    }


    @RequestMapping(value="/battleships-brehuv-register", method=RequestMethod.POST)
    public String registerSubmit(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        model.addAttribute("user", formUser);

        if (bindingResult.hasErrors()) {
            System.err.println("FORM HAS ERRORS");
            return "battleships-brehuv-gamemenu";
        }
        user = userService.register(user.getUsername(), user.getPassword());
        loggedUser = user;
        model.addAttribute("user", formUser);
        if(loggedUser == null) {
            this.alreadyRegistered = true;
        }
        return loggedUser == null ? "battleships-brehuv-register" : "battleships-brehuv-register";
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static boolean isLogged() {
        return loggedUser != null;
    }

    public boolean isAlreadyRegistered() {
        return alreadyRegistered;
    }

    @RequestMapping(value="/battleships-brehuv-login", method=RequestMethod.GET)
    public String loginShow(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        model.addAttribute("user", formUser);
        return "battleships-brehuv-login";
    }

    @RequestMapping(value="/battleships-brehuv-register", method=RequestMethod.GET)
    public String registerShow(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        model.addAttribute("user", formUser);
        this.alreadyRegistered = false;
        return "battleships-brehuv-register";
    }
}