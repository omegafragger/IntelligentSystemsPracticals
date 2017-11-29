package mars;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import search.Action;
import search.State;

/**
 * This class represents a state in the search problem.
 * The state of the problem includes the current position
 * of the robot and all the positions which have been traversed.
 * It also contains the count of the traversed positions just
 * for convenience.
 */
public class RobotState implements State {

    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = -2;

    // Current position on the crater
    public Position currentPosition;
    
    // List of positions which have been traversed by the robot
    public List<Position> traversedPosition;
    
    // Number of traversed positions - just the size of the above list
    public int numTraversedPositions;

    // Remaining battery life of the robot
    public int battery;

    /**
     * Parameterized constructor for creating a {@link RobotState} object.
     *
     * @param currentPosition
     *         Current position of the robot
     *
     * @param traversedPosition
     *         List of all positions which have been already traversed by the robot
     *
     * @param battery
     *         remaining battery life of the robot
     */
    public RobotState(Position currentPosition, List<Position> traversedPosition, int battery) {
        this.currentPosition = currentPosition;
        this.traversedPosition = traversedPosition;
        Set<Position> uniqueCells = new HashSet<>(traversedPosition);
        this.numTraversedPositions = uniqueCells.size();
        this.battery = battery;
    }

    /**
     * Method to get the set of applicable actions at this state.
     */
    @Override
    public Set<? extends Action> getApplicableActions() {
        // If the battery is over, then there can't be any more actions.
        if (battery <= 0) {
            return new HashSet<>();
        }
        Planet planet = new Planet();
        Set<Action> applicableMoves = new HashSet<>();

        if (checkAccessible(currentPosition, LEFT, planet)) {
            applicableMoves.add(Move.LEFT);
        }
        if (checkAccessible(currentPosition, RIGHT, planet)) {
            applicableMoves.add(Move.RIGHT);
        }
        if (checkAccessible(currentPosition, UP, planet)) {
            applicableMoves.add(Move.UP);
        }
        if (checkAccessible(currentPosition, DOWN, planet)) {
            applicableMoves.add(Move.DOWN);
        }
        
        return applicableMoves;
    }

    /**
     * Method to get the new state which results from application of Action
     * to this state.
     */
    @Override
    public State getActionResult(Action action) {
        Move move = (Move) action;
        Position newPosition = new Position(
                currentPosition.getRow() + move.deltaRow,
                currentPosition.getColumn() + move.deltaColumn);
        List<Position> newTraversedPosition = new ArrayList<>(traversedPosition);
        newTraversedPosition.add(currentPosition);
        RobotState newState = new RobotState(newPosition, newTraversedPosition, battery-1);
        return newState;
    }

    /**
     * Method to check if a neighbouring position is accessible or not.
     *
     * @param currentPosition
     *         Current position of the robot
     *
     * @param direction
     *         Direction to check for accessibility
     *
     * @param planet
     *         {@link Planet} object to check in
     *
     * @return true if the cell in the given direction is accessible
     *         false otherwise
     */
    private boolean checkAccessible(Position currentPosition, int direction, Planet planet) {
        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();
        if (direction == LEFT) {
            return planet.isAccessible(currentRow, currentColumn - 1);
        }
        else if (direction == RIGHT) {
            return planet.isAccessible(currentRow, currentColumn + 1);
        }
        else if (direction == UP) {
            return planet.isAccessible(currentRow - 1, currentColumn);
        }
        else if (direction == DOWN) {
            return planet.isAccessible(currentRow + 1, currentColumn);
        }
        else {
            return false;
        }
    }

    /**
     * Two robot states are equal if their current position, list of traversed
     * positions and battery life are equal. This method overrides the equals
     * method of {@link Object} to implement this.
     */
    @Override
    public boolean equals(Object rState) {
        RobotState robotState = (RobotState) rState;
        return ((robotState.currentPosition.equals(this.currentPosition))
                && (robotState.traversedPosition.equals(this.traversedPosition))
                && (robotState.battery == this.battery));
    }

    @Override
    public int hashCode() {
        return (this.currentPosition.hashCode() + this.traversedPosition.hashCode() + this.battery);
    }
}