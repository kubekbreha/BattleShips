import com.gamestudio.game.battleships.consoleui.PrintBoard;
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

    @Test
    public void expertToEnd() {
        Board board = new Board(10, 10);

        addShips(board);

        GameController gameController = new GameController(board);
        Player player = new Computer();
        ((Computer) player).setAiState(new ComputerExpert());

        int count = 0;
        while (!gameController.isGameWon(ships)){
            player.shootAI(board.getPlayBoard());
            count++;
        }
        System.out.println("Expert steps: " + count);

        boolean result;
        if(gameController.getGameState() == GameState.WON){
            result = true;
        }else{
            result = false;
        }
        assertEquals(true, result);
    }



    @Test
    public void beginnerToEnd() {
        Board board = new Board(10, 10);

        addShips(board);

        GameController gameController = new GameController(board);
        Player player = new Computer();
        ((Computer) player).setAiState(new ComputerBegginer());

        int count = 0;
        while (!gameController.isGameWon(ships)){
            player.shootAI(board.getPlayBoard());
            count++;
        }
        System.out.println("Begginer steps: " + count);

        boolean result;
        if(gameController.getGameState() == GameState.WON){
            result = true;
        }else{
            result = false;
        }
        assertEquals(true, result);
    }


    @Test
    public void mediumToEnd() {
        Board board = new Board(10, 10);

        addShips(board);

        GameController gameController = new GameController(board);
        Player player = new Computer();
        ((Computer) player).setAiState(new ComputerMedium());

        int count = 0;
        while (!gameController.isGameWon(ships)){
            player.shootAI(board.getPlayBoard());
            count++;
        }
        System.out.println("Medium steps: " + count);

        boolean result;
        if(gameController.getGameState() == GameState.WON){
            result = true;
        }else{
            result = false;
        }
        assertEquals(true, result);
    }


    @Test
    public void hardToEnd() {
        Board board = new Board(10, 10);

        addShips(board);

        GameController gameController = new GameController(board);
        Player player = new Computer();
        ((Computer) player).setAiState(new ComputerHard());

        int count = 0;
        while (!gameController.isGameWon(ships)){
            player.shootAI(board.getPlayBoard());
            count++;
        }
        System.out.println("Hard steps: " + count);

        boolean result;
        if(gameController.getGameState() == GameState.WON){
            result = true;
        }else{
            result = false;
        }
        assertEquals(true, result);
    }

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
