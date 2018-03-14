import com.gamestudio.game.battleships.core.board.Board;
import com.gamestudio.game.battleships.core.board.Ship;
import com.gamestudio.game.battleships.core.game.GameController;
import com.gamestudio.game.battleships.core.game.GameState;
import com.gamestudio.game.battleships.core.player.Human;
import com.gamestudio.game.battleships.core.player.Player;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Kubo Brehuv with <3 (14.3.2018)
 */
public class GameStateTest {

    private ArrayList<Ship> ships = new ArrayList<>();

    @Test
    public void isGameWon(){
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 3, 3 , 10 , 10,'H' );

        GameController gameController = new GameController(board);
        Player player = new Human();
        player.shoot(board.getPlayBoard(), 3, 3);
        player.shoot(board.getPlayBoard(), 3, 4);

        ships.add(ship);
        gameController.isGameWon(ships);

        assertEquals(gameController.getGameState(), GameState.WON);
    }

    @Test
    public void isGameStillPlaying(){
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 3, 3 , 10 , 10,'H' );

        GameController gameController = new GameController(board);
        Player player = new Human();
        player.shoot(board.getPlayBoard(), 3, 3);

        ships.add(ship);
        gameController.isGameWon(ships);

        assertEquals(gameController.getGameState(), GameState.PLAYING);
    }
}
