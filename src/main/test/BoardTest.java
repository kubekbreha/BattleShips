import sk.tuke.gamestudio.game.battleships.core.board.Board;
import sk.tuke.gamestudio.game.battleships.core.game.GameController;
import sk.tuke.gamestudio.game.battleships.core.game.GameState;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Kubo Brehuv with <3 (10.3.2018)
 */
public class BoardTest {

    /**
     * Check if board is set correctly.
     */
    @Test
    public void istBoardSetCorrectly(){
        Board board = new Board(10, 10);
        GameController gameController = new GameController(board);
        board.setUpBoardRandom();
        gameController.isGameSetUp(board.getShipSizes());
        assertEquals(gameController.getGameState(), GameState.SETTEDUP);
    }
}