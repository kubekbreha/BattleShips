package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.service.ScoreService;

//http://localhost:8080/battleships-brehuv-scoreboard
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BattleshipsBrehuvControllerScoreboard {

    @RequestMapping("/battleships-brehuv-scoreboard")
    public String mines() {
        return "battleships-brehuv-scoreboard"; //same name as the template
    }
}
