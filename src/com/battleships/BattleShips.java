package com.battleships;

import com.battleships.consoleui.ConsoleUI;
import com.battleships.neural.Network;

import java.util.Arrays;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class BattleShips {

    public static void main(String[] args) {
//        ConsoleUI consoleGUI = new ConsoleUI();
//        consoleGUI.setUpBoardRandom();
//        consoleGUI.startGame();

        Network net = new Network(4, 1, 3, 4);

        double[] input = net.calculate(0.1, 0.5, 0.2, 0.9);
        double[] target = net.calculate(0, 1, 0, 0);

        for(int i = 0; i<100000; i++){
            net.train(input, target, 0.3);
        }


        double[] output = net.calculate(input);
        System.out.println(Arrays.toString(output));

    }
}
