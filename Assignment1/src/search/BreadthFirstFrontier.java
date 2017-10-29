package search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ms17jm2
 *
 * This class implements the frontier and contains specific
 * implementation for the Breadth First Search algorithm.
 * The frontier in this case is a queue where as each node
 * is expanded, its children are stored at the end of the
 * queue and will be expanded at last.
 */
public class BreadthFirstFrontier implements Frontier {

    private Queue<Node> queue;
    int maxNumberOfNodes;

    /**
     * Constructor to initialise the queue representing the
     * Breadth First Frontier.
     */
    public BreadthFirstFrontier() {
        queue = new LinkedList<>();
    }

    /**
     * Method to add a new node to the Breadth First Frontier.
     * Node is added to the end of the queue.
     *
     * @param newNode
     *        The new {@link Node} object to be added to the
     *        end of the Breadth First Frontier.
     */
    @Override
    public void add(Node newNode) {
        if (newNode != null) {
            queue.offer(newNode);
        }
        if (queue.size() > maxNumberOfNodes) {
            maxNumberOfNodes = queue.size();
        }
    }

    /**
     * Method to clear the Breadth First Frontier. It removes all
     * elements from the queue.
     */
    @Override
    public void clear() {
        queue.clear();
    }

    /**
     * Method to check if the Breadth First Frontier is empty
     * or not.
     *
     * @return true if queue is empty
     *         false otherwise
     */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Method to remove a node from the Breadth First Frontier.
     * It essentially returns the first node in the queue.
     *
     * @return the first {@link Node} object in the queue
     *         representing the Breadth First Frontier.
     */
    @Override
    public Node remove() {
        return queue.poll();
    }

    /**
     * Method to return the maximum number of nodes contained
     * in the frontier since it was initialised.
     *
     * @return maximum number of nodes contained in frontier
     */
    @Override
    public int maxNodeNum() {
        return maxNumberOfNodes;
    }
}
