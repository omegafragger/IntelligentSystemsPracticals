package npuzzle;

import npuzzle.NPuzzlePrinting;
import npuzzle.Tiles;
import npuzzle.TilesGoalTest;
import search.GoalTest;
import search.IterativeDeepeningTreeSearch;
import search.Node;

/**
 * Class to execute Iterative Deepening Tree Search for the npuzzle problem.
 */
public class IDTS {
    public static void main(String[] args) {
        System.out.println("This is the execution of iterative deepening tree search on 8-puzzle");
        System.out.println("-----------------------------------------------------------------");
        
        Tiles initialConfiguration = new Tiles(new int[][] {
            { 7, 4, 2 },
            { 8, 1, 3 },
            { 5, 0, 6 }
        });
        
        GoalTest goalTest = new TilesGoalTest();
        IterativeDeepeningTreeSearch idts = new IterativeDeepeningTreeSearch();
        Node solution = idts.getSolution(new Node(null, null, initialConfiguration, 0), goalTest);
        new NPuzzlePrinting().printSolution(solution);

        // Printing performance metrics
        System.out.println("Total number of nodes generated during IDTS: " + idts.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + idts.maxNumNodesInFrontier());
    }
}