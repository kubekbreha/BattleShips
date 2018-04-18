import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.*;
import static sk.tuke.gamestudio.game.battleships.brehuv.core.board.Board.GAME_NAME;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class RatingServiceTest {

    protected RatingService ratingService;

    @Test
    public void testAddRating() throws Exception {
        Rating rating = new Rating(GAME_NAME, "miska", 3, new Date());
        ratingService.setRating(rating);
        assertEquals(3, ratingService.getAverageRating(GAME_NAME));
    }
}