package tour;

import search.AStarFunction;
import search.BestFirstFrontier;
import search.Frontier;
import search.GoalTest;
import search.GraphSearch;
import search.Node;

/**
 * Class to execute A* Graph Search for the Romania tour problem.
 */
public class AStarGraphSearch {
    public static void main(String[] args) {
        System.out.println("This is the execution of A* graph search on Romania tour problem");
        System.out.println("-----------------------------------------------------------------");
        
        Cities romania = SetUpRomania.getRomaniaMapSmall();
        City startCity = romania.getState("Bucharest");
        
        GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
        GraphSearch asgs = new GraphSearch(new BestFirstFrontier(
                new AStarFunction(new TourHeuristicFunction(romania, startCity))));
        Node solution = asgs.getSolution(new Node(null, null, new TourState(startCity), 0), goalTest);
        new TourPrinting().printSolution(solution);
        Frontier frontier = asgs.getFrontier();

        // Printing performance metrics
        System.out.println("Total number of nodes generated during A Star Graph Search: " + asgs.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + frontier.maxNodeNum());
    }
}