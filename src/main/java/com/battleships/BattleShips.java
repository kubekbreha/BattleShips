package com.battleships;

import com.battleships.consoleui.ConsoleUI;
import com.battleships.consoleui.PlayerVsComputer;
import com.battleships.consoleui.PlayerVsPlayer;
import com.battleships.core.board.Board;
import com.battleships.core.game.GameController;
import com.battleships.core.player.neural.DataSetForPerceptron;
import com.battleships.core.player.neural.Perceptron;
import org.neuroph.core.data.DataSet;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class BattleShips {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        //PlayerVsComputer game = new PlayerVsComputer();
        PlayerVsPlayer game = new PlayerVsPlayer();

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
