package sk.tuke.gamestudio.server.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

//http://localhost:8080/battleships-brehuv-instructions
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BattleshipsBrehuvControllerInstructions {

    @RequestMapping("/battleships-brehuv-instructions")
    public String mines() {
        return "battleships-brehuv-instructions";
    }
}
