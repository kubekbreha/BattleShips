package sk.tuke.gamestudio.game.battleships;

import org.springframework.context.annotation.Bean;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.battleships.consoleui.GameMenu;
import sk.tuke.gamestudio.service.*;

import java.io.FileNotFoundException;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        CommentService commentService = new CommentServiceFile();
        ScoreService scoreService = new ScoreServiceFile();
        RatingService ratingService = new RatingServiceFile();

        GameMenu gameMenu = new GameMenu(scoreService, commentService, ratingService);
        gameMenu.showMenu();


        /*GameController controller = new GameController();
        Board board = new Board(10,10);
        board.setUpBoardRandom(controller);
        ConsoleBoard consoleUI = new ConsoleBoard();
        consoleUI.printPlayBoardTesting(board);

        List list = board.getDaoubleArrayOfBoardWithShips();
        DataSetForPerceptron data = new DataSetForPerceptron();
        data.addDataToCsv(list);
        DataSet trainingSet = data.readFromCsv();
        Perceptron perceptron = new Perceptron();
        perceptron.run(trainingSet);*/

    }
}
