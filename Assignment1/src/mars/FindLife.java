package mars;

import java.util.ArrayList;
import java.util.List;

import search.Node;

/**
 * Main class which performs the task of finding life on Mars.
 */
public class FindLife {

    public static final int INITIAL_BATTERY = 20;

    public static void main(String args[]) {
        System.out.println("Executing life detection programme in Mars in : ..... 3....2....1....");
        // Creating initial state
        Position initialPosition = new Position(4,4);
        List<Position> traversedPosition = new ArrayList<>();
        
        RobotState initialState = new RobotState(initialPosition, traversedPosition, INITIAL_BATTERY);
        TreeSearch dfts = new TreeSearch();
        List<Node> solution = dfts.getSolution(new Node(null, null, initialState, 0));
        new PrintSolution().printSol(solution);
    }
}