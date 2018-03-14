import com.gamestudio.game.battleships.core.board.Board;
import com.gamestudio.game.battleships.core.board.Ship;
import com.gamestudio.game.battleships.core.game.GameController;
import com.gamestudio.game.battleships.core.game.GameState;
import com.gamestudio.game.battleships.core.player.Human;
import com.gamestudio.game.battleships.core.player.Player;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Kubo Brehuv with <3 (14.3.2018)
 */
public class ShipTest {

    @Test
    public void isShipSunked() {
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 5, 2, 10, 10, 'H');

        Player player = new Human();
        player.shoot(board.getPlayBoard(), 5, 3);
        player.shoot(board.getPlayBoard(), 5, 2);

        assertEquals(true, ship.isShipSunk(board.getPlayBoard()));
    }


    @Test
    public void isShipTouched() {
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 5, 2, 10, 10, 'H');

        Player player = new Human();
        player.shoot(board.getPlayBoard(), 5, 3);

        assertEquals(true, ship.isShipTouched(board.getPlayBoard()));
    }


    @Test
    public void isShipNotTouched() {
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 5, 2, 10, 10, 'H');

        Player player = new Human();
        player.shoot(board.getPlayBoard(), 1, 2);
        player.shoot(board.getPlayBoard(), 8, 2);
        player.shoot(board.getPlayBoard(), 8, 7);
        player.shoot(board.getPlayBoard(), 8, 9);
        player.shoot(board.getPlayBoard(), 1, 5);
        player.shoot(board.getPlayBoard(), 2, 2);
        player.shoot(board.getPlayBoard(), 2, 4);

        assertEquals(false, ship.isShipTouched(board.getPlayBoard()));
    }
}
