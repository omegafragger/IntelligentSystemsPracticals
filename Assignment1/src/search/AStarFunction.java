package search;

/**
 * Implementation of the Node function which provides the AStar value for each
 * node.
 */
public class AStarFunction implements NodeFunction {

    /**
     * Heuristic function which is going to be added with the node cost.
     */
    NodeFunction heuristicFunction;

    /**
     * Constructor to initialise the {@link AStarFunction} object with 
     * a heuristic function.
     *
     * @param heuristicFunction
     *         {@link NodeFunction} object containing the heuristic function.
     */
    public AStarFunction(NodeFunction heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    /**
     * Method to return the node value for A* (f(n) = g(n) + h(n))
     */
    @Override
    public int getNodeValue(Node n) {
        return heuristicFunction.getNodeValue(n) + n.costFromRoot;
    }
}