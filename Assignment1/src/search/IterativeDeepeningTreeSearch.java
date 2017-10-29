package search;

import java.util.Map;

/**
 * Class to implement the iterative deepening tree search algorithm.
 */
public class IterativeDeepeningTreeSearch implements Search {

    private SearchUtilities searchUtilities;

    int numNodesExplored;
    int maxNodesInFrontier;

    /**
     * Constructor to initialize the {@link SearchUtilities} object.
     */
    public IterativeDeepeningTreeSearch () {
        searchUtilities = new SearchUtilities();
    }

    /**
     * Method to compute a solution to a problem given the starting node
     * and a goal test using iterative deepening tree search.
     *
     * @param startNode
     *         Start node from where the algorithm will start searching
     *
     * @param goalTest
     *         Test to check if the current node under consideration contains
     *         a goal state
     *
     * @return the node containing the goal state if found is returned.
     */
    @Override
    public Node getSolution(Node startNode, GoalTest goalTest) {
        numNodesExplored = 0;
        Map<String, Object> resultMap = searchUtilities.iterativeDeepeningDLS(startNode, goalTest, 5);
        numNodesExplored = (Integer) resultMap.get(SearchUtilities.NUMBER_OF_EXPLORED_NODES);
        maxNodesInFrontier = (Integer) resultMap.get(SearchUtilities.MAX_NUM_NODES_IN_FRONTIER);
        return (Node) resultMap.get(SearchUtilities.GOAL_NODE);
    }

    /**
     * Method to get the number of nodes explored in the last run of the
     * search algorithm.
     *
     * @return number of explored nodes
     */
    @Override
    public int numExploredNodes() {
        return numNodesExplored;
    }

    /**
     * Method to return the maximum number of nodes present in the frontier
     * during the last execution of the search algorithm.
     *
     * @return maximum number of nodes in the frontier
     */
    public int maxNumNodesInFrontier() {
        return maxNodesInFrontier;
    }
}