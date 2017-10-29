package npuzzle;

import java.util.LinkedHashSet;
import java.util.Set;

import search.Action;
import search.State;
import tour.TourState;

/**
 * This class represents a single state in the n-puzzle game.
 */
public class Tiles implements State {
    public static final int EMPTY_TILE = 0;

    // We are playing on a width x width grid.
    protected final int width;
    protected final int[] tiles;
    protected final int emptyTileRow;
    protected final int emptyTileColumn;

    public Tiles(int[][] tiles) {
        width = tiles.length;
        this.tiles = new int[width * width];
        int emptyRow = -1;
        int emptyColumn = -1;
        int index = 0;
        for (int row = 0; row < width; row++)
            for (int column = 0; column < width; column++) {
                int tile = tiles[row][column];
                if (tile == EMPTY_TILE) {
                    emptyRow = row;
                    emptyColumn = column;
                }
                this.tiles[index++] = tile;
            }
        emptyTileRow = emptyRow;
        emptyTileColumn = emptyColumn;
    }

    protected Tiles(int width, int[] tiles, int emptyTileRow,
            int emptyTileColumn) {
        this.width = width;
        this.tiles = tiles;
        this.emptyTileRow = emptyTileRow;
        this.emptyTileColumn = emptyTileColumn;
    }

    public int getWidth() {
        return width;
    }

    public int getEmptyTileRow() {
        return emptyTileRow;
    }

    public int getEmptyTileColumn() {
        return emptyTileColumn;
    }

    public int getTile(int row, int column) {
        return tiles[row * width + column];
    }

    public Set<Action> getApplicableActions() {
        Set<Action> actions = new LinkedHashSet<Action>();
        for (Movement movement : Movement.values()) {
            int newEmptyTileRow = emptyTileRow + movement.deltaRow;
            int newEmptyTileColumn = emptyTileColumn + movement.deltaColumn;
            if (0 <= newEmptyTileRow && newEmptyTileRow < width
                    && 0 <= newEmptyTileColumn & newEmptyTileColumn < width)
                actions.add(movement);
        }
        return actions;
    }

    public State getActionResult(Action action) {
        Movement movement = (Movement) action;
        int newEmptyTileRow = emptyTileRow + movement.deltaRow;
        int newEmptyTileColumn = emptyTileColumn + movement.deltaColumn;
        int[] newTiles = tiles.clone();
        newTiles[emptyTileRow * width + emptyTileColumn] = getTile(
                newEmptyTileRow, newEmptyTileColumn);
        newTiles[newEmptyTileRow * width + newEmptyTileColumn] = EMPTY_TILE;
        return new Tiles(width, newTiles, newEmptyTileRow, newEmptyTileColumn);
    }

    /**
     * Method to check if two states in the n-puzzle game are identical. This
     * method essentially checks if the configuration of tiles for this object
     * and the given {@link State} object are the same. Note: If the passed
     * {@link State} object is not a {@link Tiles} object, then this method
     * throws a {@link ClassCastException}.
     *
     * @param tiles
     *            A {@link State} object representing a configuration of tiles
     *            to be checked for equality with this {@link Tiles} object.
     *
     * @return true if the configuration for both objects is the same false
     *         otherwise
     */
    @Override
    public boolean equals(Object tiles) {
        Tiles otherTiles = (Tiles) tiles;

        // Checking if the width is the same
        if (otherTiles.getWidth() != width) {
            return false;
        }

        // Checking if the configuration of tiles is the same
        for (int row = 0; row < width; row++) {
            for (int column = 0; column < width; column++) {
                if (otherTiles.getTile(row, column) != this
                        .getTile(row, column)) {
                    return false;
                }
            }
        }

        // Checking if the emptyTileRow and emptyTileColumn are equal
        if (otherTiles.getEmptyTileRow() != emptyTileRow
                || otherTiles.getEmptyTileColumn() != emptyTileColumn) {
            return false;
        }

        return true;
    }

    /**
     * Method to return the hashCode of this object. This method computes hash
     * codes as follows: 1. A = Sum of the product of each value of the tiles
     * array with its respective index. 2. B = width 3. C = emptyTileRow 4. D =
     * emptyTileColumn
     * 
     * HashCode = A + B + C + D
     *
     * @return hashCode value for this {@link TourState} object.
     */
    @Override
    public int hashCode() {
        int sum = 0;
        for (int index = 0; index < tiles.length; index++) {
            sum += tiles[index] * index;
        }
        return (sum + width + emptyTileRow + emptyTileColumn);
    }
}
