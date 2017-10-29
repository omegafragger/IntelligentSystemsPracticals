package tour;

import search.BreadthFirstFrontier;
import search.Frontier;
import search.GoalTest;
import search.Node;
import search.TreeSearch;

/**
 * Class to execute Breadth First Tree Search for the Romania tour problem.
 */
public class BFTS {
    public static void main(String[] args) {
        System.out.println("This is the execution of breadth-first tree search on Romania tour problem");
        System.out.println("-----------------------------------------------------------------");
        
        Cities romania = SetUpRomania.getRomaniaMapSmall();
        City startCity = romania.getState("Bucharest");
        
        GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
        TreeSearch bfts = new TreeSearch(new BreadthFirstFrontier());
        Node solution = bfts.getSolution(new Node(null, null, new TourState(startCity), 0), goalTest);
        new TourPrinting().printSolution(solution);
        Frontier frontier = bfts.getFrontier();

        // Printing performance metrics
        System.out.println("Total number of nodes generated during BFTS: " + bfts.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + frontier.maxNodeNum());
    }
}