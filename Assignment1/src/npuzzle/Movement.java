package npuzzle;

import search.Action;
import search.Node;

public enum Movement implements Action {
    UP(-1, 0), LEFT(0, -1), DOWN(1, 0), RIGHT(0, 1);

    public final int deltaRow;
    public final int deltaColumn;

    private Movement(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    /**
     * Method to return the cost of moving from one node to the other node.
     * For the npuzzle problem, since in one move we just move one tile to
     * its adjacent cell, we can say that the cost of each action is some
     * constant value. In this implementation I am assuming that cost to be 1.
     */
    @Override
    public int cost(Node n1, Node n2) {
        return 1;
    }
}