package npuzzle;

import npuzzle.NPuzzlePrinting;
import npuzzle.Tiles;
import npuzzle.TilesGoalTest;
import search.DepthFirstFrontier;
import search.Frontier;
import search.GoalTest;
import search.Node;
import search.TreeSearch;

/**
 * Class to execute Depth First Tree Search for the npuzzle problem.
 */
public class DFTS {
    public static void main(String[] args) {
        System.out.println("This is the execution of depth-first tree search on 8-puzzle");
        System.out.println("-----------------------------------------------------------------");
        
        Tiles initialConfiguration = new Tiles(new int[][] {
            { 7, 4, 2 },
            { 8, 1, 3 },
            { 5, 0, 6 }
        });
        
        GoalTest goalTest = new TilesGoalTest();
        TreeSearch dfts = new TreeSearch(new DepthFirstFrontier());
        Node solution = dfts.getSolution(new Node(null, null, initialConfiguration, 0), goalTest);
        new NPuzzlePrinting().printSolution(solution);
        Frontier frontier = dfts.getFrontier();

        // Printing performance metrics
        System.out.println("Total number of nodes generated during DFTS: " + dfts.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + frontier.maxNodeNum());
    }
}