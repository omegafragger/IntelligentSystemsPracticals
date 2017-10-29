package search;

import java.util.Map;

/**
 * Class to implement the graph search algorithm and can be parameterised with
 * the appropriate frontier type to get the search behaviour required.
 */
public class GraphSearch implements Search {

    /**
     * The frontier represents the kind of search algorithm (BFS or DFS)
     * to be performed.
     */
    private Frontier frontier;
    private SearchUtilities searchUtilities;

    int numNodesExplored;

    /**
     * Constructor to initialize the frontier type for graph search.
     * The frontier type can be Depth First Frontier or Breadth First Frontier.
     *
     * @param frontier
     *         The frontier to be used for the graph search algorithm.
     */
    public GraphSearch (Frontier frontier) {
        this.frontier = frontier;
        searchUtilities = new SearchUtilities();
    }

    /**
     * Getter method to get the search algorithm's frontier.
     *
     * @return {@link Frontier} of this object.
     */
    public Frontier getFrontier() {
        return frontier;
    }

    /**
     * Setter method to set the frontier of this search algorithm.
     *
     * @param frontier
     *         {@link Frontier} object to be used by this object.
     */
    public void setFrontier(Frontier frontier) {
        this.frontier = frontier;
    }

    /**
     * Method to compute a solution to a problem given the starting node
     * and a goal test using graph search algorithms.
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
        Map<String, Object> resultMap = searchUtilities.search(startNode, goalTest, frontier, true);
        numNodesExplored = (Integer) resultMap.get(SearchUtilities.NUMBER_OF_EXPLORED_NODES);
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
}