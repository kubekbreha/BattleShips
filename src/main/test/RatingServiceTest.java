import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;
import org.junit.Test;
import sk.tuke.gamestudio.service.RatingServiceJDBC;

import java.util.Date;

import static sk.tuke.gamestudio.game.battleships.core.board.Board.GAME_NAME;
import static junit.framework.TestCase.*;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class RatingServiceTest {

    protected RatingService ratingService = new RatingServiceJDBC();

    @Test
    public void testAddRating() throws Exception {
        Rating rating = new Rating("miska",GAME_NAME,  3, new Date());
        ratingService.setRating(rating);
        assertEquals(3, ratingService.getAverageRating(GAME_NAME));
    }
}