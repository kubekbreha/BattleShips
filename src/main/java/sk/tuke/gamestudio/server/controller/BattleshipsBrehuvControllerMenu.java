package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.game.battleships.brehuv.core.util.DatabaseUtil;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;

//http://localhost:8080/battleships-brehuv-gamemenu
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BattleshipsBrehuvControllerMenu {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/battleships-brehuv-gamemenu")
    public String mines(@RequestParam(value = "mode", required = false) String mode, Model model) {
        if(mode != null) {
            if (mode.equals("singleplayer")) {
                return "battleships-brehuv-singleplayer"; //same name as the template
            } else if (mode.equals("multiplayer")) {
                return "battleships-brehuv-multiplayer"; //same name as the template
            } else {
                return "battleships-brehuv-gamemenu"; //same name as the template
            }
        }
        return "battleships-brehuv-gamemenu";
    }



    @PostMapping("/battleships-brehuv-gamemenu")
    public String mines (@RequestParam(value = "comment", required = false) String comment, @RequestParam(value = "rating", required = false) String rating) {
        if(comment != null) {
            DatabaseUtil.addComment(comment, commentService);
        }
        if(rating != null){
            DatabaseUtil.setRating(Integer.parseInt(rating), ratingService);
        }
        return "battleships-brehuv-gamemenu";
    }
}
