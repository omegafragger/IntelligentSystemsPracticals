package tour;

import search.GoalTest;
import search.IterativeDeepeningTreeSearch;
import search.Node;

/**
 * Class to execute Iterative Deepening Tree Search for the Romania tour problem.
 */
public class IDTS {
    public static void main(String[] args) {
        System.out.println("This is the execution of iterative deepening tree search on Romania tour problem");
        System.out.println("-----------------------------------------------------------------");

        Cities romania = SetUpRomania.getRomaniaMapSmall();
        City startCity = romania.getState("Bucharest");

        GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
        IterativeDeepeningTreeSearch idts = new IterativeDeepeningTreeSearch();
        Node solution = idts.getSolution(new Node(null, null, new TourState(startCity), 0), goalTest);
        new TourPrinting().printSolution(solution);

        // Printing performance metrics
        System.out.println("Total number of nodes generated during IDTS: " + idts.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + idts.maxNumNodesInFrontier());
    }
}