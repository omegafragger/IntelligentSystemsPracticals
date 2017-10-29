package search;

/**
 * @author ms17jm2
 *
 * This interface specifies the contract of a Frontier which can
 * be utilised by various search algorithms. The contracts satisfied
 * by this interface are as follows:
 * a. add(Node n): This adds a node to the frontier.
 * b. clear(): This operation removes all the nodes in the frontier.
 * c. isEmpty(): This operation tests if a frontier has any nodes in it
 *               or not.
 * d. remove(): This operation removes a node from the frontier.
 */
public interface Frontier {

    /**
     * Operation to add a new node to the frontier.
     *
     * @param newNode The new {@link Node} object
     *                to be added into the frontier.
     */
    public void add(Node newNode);

    /**
     * Operation to empty the frontier of all its nodes.
     */
    public void clear();

    /**
     * Operation to check if the frontier has any nodes.
     *
     * @return true if the frontier has 0 nodes
     *         false if the frontier has 1 or more nodes
     */
    public boolean isEmpty();

    /**
     * Operation to remove a single node from the frontier.
     * The policy of removal will depend on the algorithm
     * making use of this operation.
     *
     * @return A single {@link Node} object obtained from
     *         the frontier
     */
    public Node remove();

    /**
     * Operation to get the maximum number of nodes contained in
     * the frontier since its creation.
     *
     * @return the maximum number of nodes contained in the frontier
     *         after it is initialised
     */
    public int maxNodeNum();
}
