package mars;

import java.util.ArrayList;
import java.util.List;

import search.AStarFunction;
import search.BestFirstFrontier;
import search.GoalTest;
import search.TreeSearch;
import search.Node;

public class FindLifeWithHigherBattery {

    public static final int INITIAL_BATTERY = 40;

    public static void main(String args[]) {
        System.out.println("Executing life finding with higher battery .....");
        // Creating initial state
        Position initialPosition = new Position(4,4);
        List<Position> traversedPosition = new ArrayList<>();
        GoalTest robotGoalTest = new RobotGoalTest();
        
        PrintSolution printSolution = new PrintSolution();
        
        RobotState initialState = new RobotState(initialPosition, traversedPosition, INITIAL_BATTERY);
        TreeSearch ats = new TreeSearch(new BestFirstFrontier(new AStarFunction(new MarsHeuristicFunction())));
        System.out.println("Starting A* search ...");
        Node solution = ats.getSolution(new Node(null, null, initialState, 0), robotGoalTest);
        if (solution != null) {
            // Print solution
            System.out.println("Solution obtained: ---");
            printSolution.printSingleSol(solution);
            System.out.println();
        }
        System.out.println("Completed A* search.");
    }
}