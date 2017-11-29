package mars;

import search.Action;
import search.Node;

/**
 * This class represents an action in the world of Mars.
 * There are four possible steps the robot can take. 
 * Left, Right, Up or Down.
 */
public enum Move implements Action {
    UP(-1, 0), LEFT(0, -1), DOWN(1, 0), RIGHT(0, 1);

    public final int deltaRow;
    public final int deltaColumn;

    private Move(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    /**
     * Overriding the cost function. The cost is essentially
     * the battery life and it is reduced by 1 every time a move
     * is made.
     */
    @Override
    public int cost(Node n1, Node n2) {
        return 1;
    }
}