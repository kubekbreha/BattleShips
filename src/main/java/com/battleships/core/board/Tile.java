package com.battleships.core.board;


/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
public class Tile {

    private TileState tileState;

    public Tile(TileState state){
        this.tileState = state;
    }

    public TileState getTileState() {
        return tileState;
    }

    public void setTileState(TileState tileState) {
        this.tileState = tileState;
    }
}
