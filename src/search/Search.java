package search;

/**
 * Interface to specify the contract followed by any search kind of search
 * algorithm.
 */
public interface Search {

    /**
     * This method is used to find a solution to a search problem given
     * the start node and the goal test which can be applied on the state.
     *
     * @param startNode
     *         Start node which is provided to the search algorithm
     *
     * @param goalTest
     *         Goal test which checks if a given node contains a goal state
     *         or not.
     *
     * @return the goal node is returned if found. Otherwise null is returned.
     */
    public Node getSolution(Node startNode, GoalTest goalTest);

    /**
     * This method returns the number of nodes explored in the last execution
     * of the getSolution method, i.e., during the last search performed. It is
     * useful for the purpose of measuring performance of a search algorithm.
     *
     * @return the number of nodes explored in the last run of getSolution() 
     */
    public int numExploredNodes();
}