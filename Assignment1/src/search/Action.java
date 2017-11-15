package search;

/**
 * The Action interface specifies the contract of any action that takes
 * a problem from one state to another.
 */
public interface Action {

    /**
     * This method returns the cost of executing the action to move from
     * Node n1 to Node n2.
     *
     * @param n1
     *         The starting node before the action
     *
     * @param n2
     *         The ending node after the action
     *
     * @return The cost for executing the action
     */
    public int cost(Node n1, Node n2);
}
