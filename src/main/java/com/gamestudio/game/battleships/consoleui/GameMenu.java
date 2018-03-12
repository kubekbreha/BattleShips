package com.gamestudio.game.battleships.consoleui;

import com.gamestudio.game.battleships.core.board.Util;

import java.util.Scanner;

public class GameMenu {

    public GameMenu(){
      showMenu();
    }

    private void showMenu(){
        GameMode gameMode;
        System.out.println("Welcome to BATTLESHIPS game.");
        System.out.println("1. Player vs Computer");
        System.out.println("2. Player vs Player");
        System.out.println("3. Show best scores");

        Scanner reader = new Scanner(System.in);
        int pick = reader.nextInt();
        switch (pick) {
            case 1:
                gameMode = new PlayerVsComputer();
                break;

            case 2:
                gameMode = new PlayerVsPlayer();
                break;

            case 3:
                Util.printScore();
                showMenu();
                break;
        }
    }
}
