package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.game.battleships.brehuv.webui.WebUIMultiPlayer;
import sk.tuke.gamestudio.game.battleships.brehuv.webui.WebUISinglePlayer;
import sk.tuke.gamestudio.service.ScoreService;

//http://localhost:8080/battleships-brehuv-multiplayer
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BattleshipsBrehuvControllerMultiPlayer {

    private WebUIMultiPlayer webUIMultiPlayer = new WebUIMultiPlayer();

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/battleships-brehuv-multiplayer")
    public String mines(@RequestParam(value = "command", required = false) String command,
                        @RequestParam(value = "row", required = false) String row,
                        @RequestParam(value = "column", required = false) String column, Model model) {
        webUIMultiPlayer.processCommand(command, row, column);

        model.addAttribute("webUI", webUIMultiPlayer);

        model.addAttribute("scores", scoreService.getBestScores("battleships-brehuv"));

        return "battleships-brehuv-multiplayer"; //same name as the template
    }
}
