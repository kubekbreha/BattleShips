package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.game.battleships.brehuv.webui.WebUISinglePlayer;
import sk.tuke.gamestudio.service.ScoreService;

//http://localhost:8080/battleships-brehuv-singleplayer-setup
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BattleshipsBrehuvControllerSinglePlayerSetup {

    private static WebUISinglePlayer webUISinglePlayer = new WebUISinglePlayer();

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/battleships-brehuv-singleplayer-setup")
    public String battleships(@RequestParam(value = "command", required = false) String command,
                        @RequestParam(value = "row", required = false) String row,
                        @RequestParam(value = "column", required = false) String column, Model model) {
        webUISinglePlayer.processCommand(command, row, column);

        model.addAttribute("webUI", webUISinglePlayer);

        return "battleships-brehuv-singleplayer-setup"; //same name as the template
    }

    static WebUISinglePlayer getUIClass(){
        return webUISinglePlayer;
    }
}
