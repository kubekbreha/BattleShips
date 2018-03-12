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
        System.out.println("4. Show comments");
        System.out.println("5. Show rating");
        System.out.println("6. Rate this game");

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

            case 4:
                Util.printComments();
                showMenu();
                break;

            case 5:
                Util.printRating();
                showMenu();
                break;

            case 6:
                Util.addRating(1);
                showMenu();
                break;
        }
    }
}