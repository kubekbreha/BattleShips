import com.gamestudio.game.battleships.core.board.Board;
import com.gamestudio.game.battleships.core.board.Ship;
import com.gamestudio.game.battleships.core.game.GameController;
import com.gamestudio.game.battleships.core.game.GameState;
import com.gamestudio.game.battleships.core.player.*;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Kubo Brehuv with <3 (14.3.2018)
 */
public class AITest {

    private ArrayList<Ship> ships = new ArrayList<>();

    /**
     * Test if can be won by AIexpert algorithm.
     */
    @Test
    public void expertToEnd() {
        Board board = new Board(10, 10);

        addShips(board);

        GameController gameController = new GameController(board);
        Player player = new Computer();
        ((Computer) player).setAiState(new ComputerExpert(board.getBoardRows(), board.getBoardCols()));

        while (!gameController.isGameWon(ships)){
            player.shootAI(board.getPlayBoard());
        }

        boolean result = gameController.getGameState() == GameState.WON;
        assertEquals(true, result);
    }


    /**
     * Test if can be won by AIbeginner algorithm.
     */
    @Test
    public void beginnerToEnd() {
        Board board = new Board(10, 10);

        addShips(board);

        GameController gameController = new GameController(board);
        Player player = new Computer();
        ((Computer) player).setAiState(new ComputerBegginer());

        while (!gameController.isGameWon(ships)){
            player.shootAI(board.getPlayBoard());
        }

        boolean result = gameController.getGameState() == GameState.WON;
        assertEquals(true, result);
    }

    /**
     * Test if can be won by AImedium algorithm.
     */
    @Test
    public void mediumToEnd() {
        Board board = new Board(10, 10);

        addShips(board);

        GameController gameController = new GameController(board);
        Player player = new Computer();
        ((Computer) player).setAiState(new ComputerMedium());

        while (!gameController.isGameWon(ships)){
            player.shootAI(board.getPlayBoard());
        }

        boolean result = gameController.getGameState() == GameState.WON;
        assertEquals(true, result);
    }

    /**
     * Test if can be won by AIhard algorithm.
     */
    @Test
    public void hardToEnd() {
        Board board = new Board(10, 10);

        addShips(board);

        GameController gameController = new GameController(board);
        Player player = new Computer();
        ((Computer) player).setAiState(new ComputerHard(board.getBoardRows(), board.getBoardCols()));

        while (!gameController.isGameWon(ships)){
            player.shootAI(board.getPlayBoard());
        }

        boolean result = gameController.getGameState() == GameState.WON;
        assertEquals(true, result);
    }

    /**
     * Add ships to board.
     * @param board where ships will be placed.
     */
    private void addShips(Board board){
        Ship ship = new Ship(2);
        Ship ship2 = new Ship(4);
        Ship ship3 = new Ship(2);
        Ship ship4 = new Ship(1);

        ships.add(ship);
        ships.add(ship2);
        ships.add(ship3);
        ships.add(ship4);
        ship.placeShip(board.getPlayBoard(), 4, 4, 10, 10, 'H');
        ship2.placeShip(board.getPlayBoard(), 2, 2, 10, 10, 'V');
        ship3.placeShip(board.getPlayBoard(), 0, 7, 10, 10, 'H');
        ship4.placeShip(board.getPlayBoard(), 9, 8, 10, 10, 'H');
    }
}
