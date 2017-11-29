package mars;

import java.util.HashSet;
import java.util.Set;

import search.GoalTest;
import search.State;

public class RobotGoalTest implements GoalTest {

    Set<Position> emptyPositions;
    Set<Position> traversedPositions;

    /**
     * Constructor to create a set of the different empty spaces
     * in the Mars crater.
     */
    public RobotGoalTest() {
        Planet planet = new Planet();
        emptyPositions = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (planet.isAccessible(i,j)) {
                    emptyPositions.add(new Position(i,j));
                }
            }
        }
        traversedPositions = new HashSet<>();
    }

    /**
     * Detecting if a RobotState is a goal or not.
     */
    @Override
    public boolean isGoal(State state) {
        RobotState robotState = (RobotState) state;
        traversedPositions.clear();
        traversedPositions.addAll(robotState.traversedPosition);
        traversedPositions.add(robotState.currentPosition);
        if (emptyPositions.equals(traversedPositions)) {
            return true;
        }
        return false;
    }
}