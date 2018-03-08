package com.boardgames;

import com.boardgames.game.battleships.consoleui.PlayerVsComputer;

import java.io.FileNotFoundException;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        PlayerVsComputer game = new PlayerVsComputer();
        //PlayerVsPlayer game = new PlayerVsPlayer();

        /*GameController controller = new GameController();
        Board board = new Board(10,10);
        board.setUpBoardRandom(controller);
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.printPlayBoardTesting(board);

        List list = board.getDaoubleArrayOfBoardWithShips();
        DataSetForPerceptron data = new DataSetForPerceptron();
        data.addDataToCsv(list);
        DataSet trainingSet = data.readFromCsv();
        Perceptron perceptron = new Perceptron();
        perceptron.run(trainingSet);*/

    }
}
