package tour;

import search.DepthFirstFrontier;
import search.Frontier;
import search.GoalTest;
import search.GraphSearch;
import search.Node;

/**
 * Class to execute Depth First Graph Search for the Romania tour problem.
 */
public class DFGS {
    public static void main(String[] args) {
        System.out.println("This is the execution of depth-first graph search on Romania tour problem");
        System.out.println("-----------------------------------------------------------------");
        
        Cities romania = SetUpRomania.getRomaniaMapSmall();
        City startCity = romania.getState("Bucharest");
        
        GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
        GraphSearch dfgs = new GraphSearch(new DepthFirstFrontier());
        Node solution = dfgs.getSolution(new Node(null, null, new TourState(startCity), 0), goalTest);
        new TourPrinting().printSolution(solution);
        Frontier frontier = dfgs.getFrontier();

        // Printing performance metrics
        System.out.println("Total number of nodes generated during DFGS: " + dfgs.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + frontier.maxNodeNum());
    }
}