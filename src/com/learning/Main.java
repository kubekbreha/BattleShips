package com.learning;

import com.learning.board.Board;
import com.learning.board.Ship;
import com.learning.player.Player;
import com.learning.player.Human;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        Board board = new Board(10, 10);
        Ship ship = new Ship(3);

        board.printPlayBoard();

        System.out.println();
        System.out.println();
        Player player = new Human();

        for(int i = 0; i < 6; i++)
            player.shoot(board.getPlayBoard(), rand.nextInt(10) , rand.nextInt(10));

        board.printPlayBoard();
        System.out.println(player.getHistorySize());

        for(int i = 0; i < 5; i++)
            player.undo(board.getPlayBoard());

        ship.placeShip(board.getPlayBoard(), 2, 2 , 'V');
        board.printPlayBoard();

        System.out.println(player.getHistorySize());
    }
}
