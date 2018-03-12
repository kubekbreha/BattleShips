import com.gamestudio.entity.Score;
import com.gamestudio.service.ScoreService;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static com.gamestudio.game.battleships.core.board.Board.GAME_NAME;
import static junit.framework.TestCase.*;

public class ScoreServiceTest {
    protected ScoreService scoreService;

    @Test
    public void testDbInit() throws Exception {
        assertEquals(0, scoreService.getBestScores(GAME_NAME).size());
    }

    @Test
    public void testAddScore() throws Exception {
        Score score = new Score(GAME_NAME, "miska", 15, new Date());
        scoreService.addScore(score);
        assertEquals(1, scoreService.getBestScores(GAME_NAME).size());
    }

    @Test
    public void testGetBestScores() throws Exception {
        Score s1 = new Score(GAME_NAME, "janko", 150, new Date());
        Score s2 = new Score(GAME_NAME, "hrasko", 300, new Date());

        scoreService.addScore(s1);
        scoreService.addScore(s2);

        List<Score> scores = scoreService.getBestScores(GAME_NAME);
        assertEquals(s2.getPoints(), scores.get(0).getPoints());
        assertEquals(s2.getPlayer(), scores.get(0).getPlayer());
    }
}
