package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

//http://localhost:8080/battleships-brehuv-rating
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BattleshipsBrehuvControlleRating {


    @Autowired
    private RatingService ratingService;

    @RequestMapping("/battleships-brehuv-rating")
    public String mines(Model model) {


        return "battleships-brehuv-rating";
    }
}
