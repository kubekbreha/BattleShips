package com.battleships;

import com.battleships.consoleui.ConsoleUI;

/**
 * Created by Kubo Brehuv with <3 (18.2.2018)
 */
public class BattleShips {

    public static void main(String[] args) throws InterruptedException {
        ConsoleUI consoleGUI = new ConsoleUI();
        consoleGUI.setUpBoardRandom();
        consoleGUI.startGame ();

    }
}
