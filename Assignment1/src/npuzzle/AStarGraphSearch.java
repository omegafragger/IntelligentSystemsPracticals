package npuzzle;

import npuzzle.NPuzzlePrinting;
import npuzzle.Tiles;
import npuzzle.TilesGoalTest;
import search.AStarFunction;
import search.BestFirstFrontier;
import search.Frontier;
import search.GoalTest;
import search.GraphSearch;
import search.Node;

/**
 * Class to execute A* Graph Search for the npuzzle problem.
 */
public class AStarGraphSearch {
    public static void main(String[] args) {
        System.out.println("This is the execution of A* graph search on 8-puzzle");
        System.out.println("-----------------------------------------------------------------");
        
        Tiles initialConfiguration = new Tiles(new int[][] {
            { 7, 4, 2 },
            { 8, 1, 3 },
            { 5, 0, 6 }
        });
        
        GoalTest goalTest = new TilesGoalTest();
        GraphSearch aStargs = new GraphSearch(new BestFirstFrontier(
                new AStarFunction(new MisplacedTilesHeuristicFunction())));
        Node solution = aStargs.getSolution(new Node(null, null, initialConfiguration, 0), goalTest);
        new NPuzzlePrinting().printSolution(solution);
        Frontier frontier = aStargs.getFrontier();

        // Printing performance metrics
        System.out.println("Total number of nodes generated during A* Graph Search: " + aStargs.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + frontier.maxNodeNum());
    }
}