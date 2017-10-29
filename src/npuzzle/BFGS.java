package npuzzle;

import npuzzle.NPuzzlePrinting;
import npuzzle.Tiles;
import npuzzle.TilesGoalTest;
import search.BreadthFirstFrontier;
import search.Frontier;
import search.GoalTest;
import search.GraphSearch;
import search.Node;

/**
 * Class to execute Breadth First Graph Search for the npuzzle problem.
 */
public class BFGS {
    public static void main(String[] args) {
        System.out.println("This is the execution of breadth-first graph search on 8-puzzle");
        System.out.println("-----------------------------------------------------------------");
        
        Tiles initialConfiguration = new Tiles(new int[][] {
            { 7, 4, 2 },
            { 8, 1, 3 },
            { 5, 0, 6 }
        });
        
        GoalTest goalTest = new TilesGoalTest();
        GraphSearch bfgs = new GraphSearch(new BreadthFirstFrontier());
        Node solution = bfgs.getSolution(new Node(null, null, initialConfiguration, 0), goalTest);
        new NPuzzlePrinting().printSolution(solution);
        Frontier frontier = bfgs.getFrontier();

        // Printing performance metrics
        System.out.println("Total number of nodes generated during BFGS: " + bfgs.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + frontier.maxNodeNum());
    }
}