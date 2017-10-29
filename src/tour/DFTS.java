package tour;

import search.DepthFirstFrontier;
import search.Frontier;
import search.GoalTest;
import search.Node;
import search.TreeSearch;

/**
 * Class to execute Depth First Tree Search for the Romania tour problem.
 */
public class DFTS {
    public static void main(String[] args) {
        System.out.println("This is the execution of depth-first tree search on Romania tour problem");
        System.out.println("-----------------------------------------------------------------");
        
        Cities romania = SetUpRomania.getRomaniaMapSmall();
        City startCity = romania.getState("Bucharest");
        
        GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
        TreeSearch dfts = new TreeSearch(new DepthFirstFrontier());
        Node solution = dfts.getSolution(new Node(null, null, new TourState(startCity), 0), goalTest);
        new TourPrinting().printSolution(solution);
        Frontier frontier = dfts.getFrontier();

        // Printing performance metrics
        System.out.println("Total number of nodes generated during DFTS: " + dfts.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + frontier.maxNodeNum());
    }
}