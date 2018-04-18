
import org.junit.Test;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Board;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Ship;
import sk.tuke.gamestudio.game.battleships.brehuv.core.game.GameController;
import sk.tuke.gamestudio.game.battleships.brehuv.core.game.GameState;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Human;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Player;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Kubo Brehuv with <3 (14.3.2018)
 */
public class GameStateTest {

    private ArrayList<Ship> ships = new ArrayList<>();

    /**
     * Test game win state after destroying ship.
     */
    @Test
    public void isGameWon(){
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 3, 3 , 'H' );

        GameController gameController = new GameController(board);
        Player player = new Human();
        player.shoot(board.getPlayBoard(), 3, 3);
        player.shoot(board.getPlayBoard(), 3, 4);

        ships.add(ship);
        gameController.isGameWon(ships);

        assertEquals(gameController.getGameState(), GameState.WON);
    }

    /**
     * Test if game is in playing mode after one ship touched.
     */
    @Test
    public void isGameStillPlaying(){
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 3, 3 , 'H' );

        GameController gameController = new GameController(board);
        Player player = new Human();
        player.shoot(board.getPlayBoard(), 3, 3);

        ships.add(ship);
        gameController.isGameWon(ships);

        assertEquals(gameController.getGameState(), GameState.PLAYING);
    }
}
