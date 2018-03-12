import com.gamestudio.entity.Rating;
import com.gamestudio.service.RatingService;
import org.junit.Test;

import java.util.Date;

import static com.gamestudio.game.battleships.core.board.Board.GAME_NAME;
import static junit.framework.TestCase.*;

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