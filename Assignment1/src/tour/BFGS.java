package tour;

import search.BreadthFirstFrontier;
import search.Frontier;
import search.GoalTest;
import search.GraphSearch;
import search.Node;

/**
 * Class to execute Breadth First Graph Search for the Romania tour problem.
 */
public class BFGS {
    public static void main(String[] args) {
        System.out.println("This is the execution of breadth-first graph search on Romania tour problem");
        System.out.println("-----------------------------------------------------------------");
        
        Cities romania = SetUpRomania.getRomaniaMap();
        City startCity = romania.getState("Bucharest");
        
        GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
        GraphSearch bfgs = new GraphSearch(new BreadthFirstFrontier());
        Node solution = bfgs.getSolution(new Node(null, null, new TourState(startCity), 0), goalTest);
        new TourPrinting().printSolution(solution);
        Frontier frontier = bfgs.getFrontier();

        // Printing performance metrics
        System.out.println("Total number of nodes generated during BFGS: " + bfgs.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + frontier.maxNodeNum());
    }
}