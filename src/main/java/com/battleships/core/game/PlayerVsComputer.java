package com.battleships.core.game;

import com.battleships.core.board.Board;

/**
 * Created by Kubo Brehuv with <3 (1.3.2018)
 */

public class PlayerVsComputer {

    private Board userBoard;
    private Board computerBoard;

    public PlayerVsComputer(){
        userBoard = new Board(10,10);
        computerBoard = new Board(10,10);

        userBoard.setUpBoardRandom();
        userBoard.setUpBoardRandom();
    }





}
