package tour;

import search.AStarFunction;
import search.BestFirstFrontier;
import search.Frontier;
import search.GoalTest;
import search.Node;
import search.TreeSearch;

/**
 * Class to execute A* Tree Search for the Romania tour problem.
 */
public class AStarTreeSearch {
    public static void main(String[] args) {
        System.out.println("This is the execution of A* tree search on Romania tour problem");
        System.out.println("-----------------------------------------------------------------");
        
        Cities romania = SetUpRomania.getRomaniaMapSmall();
        City startCity = romania.getState("Bucharest");
        
        GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
        TreeSearch asts = new TreeSearch(new BestFirstFrontier(
                new AStarFunction(new TourHeuristicFunction(romania, startCity))));
        Node solution = asts.getSolution(new Node(null, null, new TourState(startCity), 0), goalTest);
        new TourPrinting().printSolution(solution);
        Frontier frontier = asts.getFrontier();

        // Printing performance metrics
        System.out.println("Total number of nodes generated during A Star Tree Search: " + asts.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + frontier.maxNodeNum());
    }
}