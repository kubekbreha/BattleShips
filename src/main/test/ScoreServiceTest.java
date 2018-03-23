import org.junit.Before;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;
import org.junit.Test;
import sk.tuke.gamestudio.service.ScoreServiceJDBC;

import java.util.Date;
import java.util.List;

import static sk.tuke.gamestudio.game.battleships.core.board.Board.GAME_NAME;
import static junit.framework.TestCase.*;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class ScoreServiceTest {
    protected ScoreService scoreService = new ScoreServiceJDBC();

    @Test
    public void testDbInit() throws Exception {
        assertEquals(0, scoreService.getBestScores(GAME_NAME).size());
    }

    @Test
    public void testAddScore() throws Exception {
        Score score = new Score( "miska",GAME_NAME, 15, new Date());
        scoreService.addScore(score);
        assertEquals(1, scoreService.getBestScores(GAME_NAME).size());
    }

    @Test
    public void testGetBestScores() throws Exception {
        Score s1 = new Score( "janko", GAME_NAME,150, new Date());
        Score s2 = new Score( "hrasko", GAME_NAME, 300, new Date());

        scoreService.addScore(s1);
        scoreService.addScore(s2);

        List<Score> scores = scoreService.getBestScores(GAME_NAME);
        assertEquals(s2.getPoints(), scores.get(0).getPoints());
        assertEquals(s2.getPlayer(), scores.get(0).getPlayer());
    }
}
