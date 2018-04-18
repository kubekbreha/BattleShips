
import org.junit.Test;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Board;
import sk.tuke.gamestudio.game.battleships.brehuv.core.board.Ship;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Human;
import sk.tuke.gamestudio.game.battleships.brehuv.core.player.Player;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Kubo Brehuv with <3 (14.3.2018)
 */
public class ShootTest {

    /**
     * Test miss shoot.
     */
    @Test
    public void shootMissed() {
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 5, 2,  'H');

        Player player = new Human();
        player.shoot(board.getPlayBoard(), 5, 8);

        assertEquals(false, ship.isShipTouched(board.getPlayBoard()));
    }


    /**
     * Test hit shoot.
     */
    @Test
    public void shootHitted() {
        Board board = new Board(10, 10);
        Ship ship = new Ship(2);
        ship.placeShip(board.getPlayBoard(), 5, 2,  'H');

        Player player = new Human();
        player.shoot(board.getPlayBoard(), 5, 2);

        assertEquals(true, ship.isShipTouched(board.getPlayBoard()));
    }
}
