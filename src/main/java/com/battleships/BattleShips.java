package com.battleships;

import com.battleships.consoleui.ConsoleUI;

import java.io.FileNotFoundException;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class BattleShips {

    //TODO: hint for user vs PC
    //TODO: create play modes

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        ConsoleUI consoleGUI = new ConsoleUI();
        consoleGUI.setUpBoardRandom();
        consoleGUI.startGame();



        /*List list = consoleGUI.getDaoubleArrayOfBoardWithShips();
        DataSetForPerceptron data = new DataSetForPerceptron();
        //data.addDataToCsv(list);
        DataSet trainingSet = data.readFromCsv();
        Perceptron perceptron = new Perceptron();
        perceptron.run(trainingSet);*/
    }
}
