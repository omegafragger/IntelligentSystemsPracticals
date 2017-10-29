package npuzzle;

import npuzzle.NPuzzlePrinting;
import npuzzle.Tiles;
import npuzzle.TilesGoalTest;
import search.BreadthFirstFrontier;
import search.Frontier;
import search.GoalTest;
import search.Node;
import search.TreeSearch;

/**
 * Class to execute Breadth First Tree Search for the npuzzle problem.
 */
public class BFTS {
    public static void main(String[] args) {
        System.out.println("This is the execution of breadth-first tree search on 8-puzzle");
        System.out.println("-----------------------------------------------------------------");
        
        Tiles initialConfiguration = new Tiles(new int[][] {
            { 7, 4, 2 },
            { 8, 1, 3 },
            { 5, 0, 6 }
        });
        
        GoalTest goalTest = new TilesGoalTest();
        TreeSearch bfts = new TreeSearch(new BreadthFirstFrontier());
        Node solution = bfts.getSolution(new Node(null, null, initialConfiguration, 0), goalTest);
        new NPuzzlePrinting().printSolution(solution);
        Frontier frontier = bfts.getFrontier();

        // Printing performance metrics
        System.out.println("Total number of nodes generated during BFTS: " + bfts.numExploredNodes());
        System.out.println("Maximum number of nodes in frontier: " + frontier.maxNodeNum());
    }
}