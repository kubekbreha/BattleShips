package com.gamestudio.game.battleships.consoleui;

import com.gamestudio.game.battleships.core.board.Board;

import java.util.Scanner;

public class GameMenu {

    public GameMenu(){
        GameMode gameMode;
        System.out.println("Welcome to BATTLESHIPS game.");
        System.out.println("Pick game mode:");
        System.out.println("1. Player vs Computer");
        System.out.println("2. Player vs Player");

        Scanner reader = new Scanner(System.in);
        int gameModePick = reader.nextInt();
        switch (gameModePick) {
            case 1:
                gameMode = new PlayerVsComputer();
                break;

            case 2:
                gameMode = new PlayerVsPlayer();
                break;
        }
    }

}
