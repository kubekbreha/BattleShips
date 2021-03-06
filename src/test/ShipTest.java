
import org.junit.Test;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Board;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Ship;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Human;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Player;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Kubo Brehuv with <3 (14.3.2018)
 */
public class ShipTest {

    /**
     * Test if ship can be sunk.
     */
    @Test
    public void isShipSunked() {
        Board board;
        board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 5, 2,  'H');

        Player player = new Human();
        player.shoot(board.getPlayBoard(), 5, 3);
        player.shoot(board.getPlayBoard(), 5, 2);

        assertEquals(true, ship.isShipSunk(board.getPlayBoard()));
    }


    /**
     * Test if ship is touched after shoot.
     */
    @Test
    public void isShipTouched() {
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 5, 2,  'H');

        Player player = new Human();
        player.shoot(board.getPlayBoard(), 5, 3);

        assertEquals(true, ship.isShipTouched(board.getPlayBoard()));
    }


    /**
     * Test if ship is not touched after shoot.
     */
    @Test
    public void isShipNotTouched() {
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 5, 2,  'H');

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
