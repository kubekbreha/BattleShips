import com.gamestudio.game.battleships.core.board.Board;
import com.gamestudio.game.battleships.core.game.GameController;
import com.gamestudio.game.battleships.core.game.GameState;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class BoardTest {

    @Test
    public void istBoardSetCorrectly(){
        Board board = new Board(10, 10);
        GameController gameController = new GameController(board);
        board.setUpBoardRandom();
        gameController.isGameSetUp(board.getShipSize());
        assertEquals(gameController.getGameState(), GameState.SETTEDUP);
    }
}
