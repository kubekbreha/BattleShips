package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.game.battleships.brehuv.webui.WebUI;
import sk.tuke.gamestudio.service.ScoreService;

//http://localhost:8080/battleships-brehuv
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BattleshipsBrehuvController {

    private WebUI webUI = new WebUI();

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/battleships-brehuv")
    public String mines(@RequestParam(value = "row", required = false) String row,
                        @RequestParam(value = "column", required = false) String column, Model model) {
        webUI.processCommand(row, column);
        model.addAttribute("webUI", webUI);

        model.addAttribute("scores", scoreService.getBestScores("battleships-brehuv"));


        return "battleships-brehuv"; //same name as the template

    }
}
