package sk.tuke.gamestudio.game.core.board;


/**
 * Created by Kubo Brehuv with <3 (28.2.2018)
 */
public class Tile {

    private TileState tileState;

    /**
     * Give state to tile from enum TileState.
     *
     * @param state which will be set to tile.
     */
    public Tile(TileState state) {
        this.tileState = state;
    }

    /**
     * Get tile state.
     */
    public TileState getTileState() {
        return tileState;
    }

    /**
     * Set tile state.
     */
    public void setTileState(TileState tileState) {
        this.tileState = tileState;
    }
}
