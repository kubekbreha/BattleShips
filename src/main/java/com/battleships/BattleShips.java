package com.battleships;

import com.battleships.consoleui.ConsoleUI;
import com.battleships.neural.DataSetForPerceptron;
import com.battleships.neural.Perceptron;
import org.neuroph.core.data.DataSet;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class BattleShips {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        ConsoleUI consoleGUI = new ConsoleUI();
        consoleGUI.setUpBoardRandom();
        //consoleGUI.startGame();
        List list = consoleGUI.getDaoubleArrayOfBoardWithShips();

        DataSetForPerceptron data = new DataSetForPerceptron();
        //data.addDataToCsv(list);
        DataSet trainingSet = data.readFromCsv();
        Perceptron perceptron = new Perceptron();
        perceptron.run(trainingSet);
    }
}
