package com.gamestudio;

import com.gamestudio.game.battleships.consoleui.GameMenu;
import com.gamestudio.game.battleships.consoleui.PlayerVsComputer;
import com.gamestudio.game.battleships.core.util.Util;

import java.io.FileNotFoundException;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        GameMenu gameMenu = new GameMenu();


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
