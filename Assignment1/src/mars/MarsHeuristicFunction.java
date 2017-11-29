package mars;

import java.util.HashSet;
import java.util.Set;

import search.Node;
import search.NodeFunction;

/**
 * This class contains a heuristic function which can be used while
 * searching solutions in the Mars problem. Essentially, this heuristic
 * function prefers states where we explore new cells rather than repeat on old ones.
 */
public class MarsHeuristicFunction implements NodeFunction {

    private static final int INFINITE = 10000;

    private int totalEmptyPositions;
    Set<Position> traversedPositions;
    Planet planet;

    public MarsHeuristicFunction() {
        planet = new Planet();
        totalEmptyPositions = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (planet.isAccessible(i,j)) {
                    totalEmptyPositions++;
                }
            }
        }
        traversedPositions = new HashSet<>();
    }

    /**
     * Checking if a cell or a position has only one edge coming out
     * of it.
     */
    private boolean checkIfSoloCell(Position position) {
        int numAdjacentCells = 0;
        int i = position.getRow();
        int j = position.getColumn();
        
        if (planet.isAccessible(i, j-1)) {
            numAdjacentCells++;
        }
        if (planet.isAccessible(i, j+1)) {
            numAdjacentCells++;
        }
        if (planet.isAccessible(i-1, j)) {
            numAdjacentCells++;
        }
        if (planet.isAccessible(i+1, j)) {
            numAdjacentCells++;
        }
        if (numAdjacentCells > 1) {
            return false;
        }
        return true;
    }

    /**
     * This function returns the total number of positions which are left
     * for the state to traverse. This will be lower for better routes
     * thereby leading the search in a better direction.
     * 
     * This is an admissible heuristic because the robot will have to cover
     * at least the total number of remaining empty cells but the cells might
     * be arranged in an odd fashion where the robot will have to branch off
     * to different branches to cover all the cells.
     */
    @Override
    public int getNodeValue(Node n) {
        RobotState robotState = (RobotState) n.state;
        traversedPositions.clear();
        traversedPositions.addAll(robotState.traversedPosition);
        traversedPositions.add(robotState.currentPosition);

        /**
         * Solo position check
         */
        if (robotState.traversedPosition.size() >= 2) {
            Position curPos = robotState.currentPosition;
            Position prevPos = robotState.traversedPosition.get(robotState.traversedPosition.size() - 1);
            Position prevPrevPos = robotState.traversedPosition.get(robotState.traversedPosition.size() - 2);
        
            if ((curPos.equals(prevPrevPos)) && (!checkIfSoloCell(prevPos))) {
                return INFINITE;
            }
        }
        return ((totalEmptyPositions - (traversedPositions.size())));
    }
}