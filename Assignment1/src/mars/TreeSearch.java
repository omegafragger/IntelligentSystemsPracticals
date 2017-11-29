package mars;

import java.util.List;

import search.DepthFirstFrontier;
import search.Frontier;
import search.Node;

/**
 * Class to implement the tree search algorithm and can be parameterised with
 * the appropriate frontier type to get the search behaviour required.
 */
public class TreeSearch {

    private Frontier frontier;
    private SearchUtils searchUtilities;

    /**
     * Constructor to initialize the frontier type for tree search.
     * The frontier type can be Depth First Frontier.
     */
    public TreeSearch () {
        this.frontier = new DepthFirstFrontier();
        searchUtilities = new SearchUtils();
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
     * and a goal test using tree search.
     *
     * @param startNode
     *         Start node from where the algorithm will start searching
     *
     * @return the node containing the goal state if found is returned.
     */
    public List<Node> getSolution(Node startNode) {
        return searchUtilities.search(startNode, frontier);
    }
}