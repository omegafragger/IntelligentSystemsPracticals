package search;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author ms17jm2
 *
 * This class implements the frontier and contains specific
 * implementation for the Depth First Search algorithm.
 * The frontier in this case is a stack where as each node
 * is expanded, its children are stored on top of the stack
 * and will be expanded first.
 */
public class DepthFirstFrontier implements Frontier {

    private Stack<Node> stack;
    int maxNumberOfNodes;

    /**
     * Constructor to initialise the stack representing the
     * Depth First Frontier.
     */
    public DepthFirstFrontier() {
        stack = new Stack<>();
    }

    /**
     * Method to add a new node to the Depth First Frontier.
     * Node is added to the top of the stack.
     *
     * @param newNode
     *        The new {@link Node} object to be added to the
     *        top of the Depth First Frontier.
     */
    @Override
    public void add(Node newNode) {
        if (newNode != null) {
            stack.push(newNode);
        }
        if (stack.size() > maxNumberOfNodes) {
            maxNumberOfNodes = stack.size();
        }
    }

    /**
     * Method to clear the Depth First Frontier. It removes all
     * elements from the stack.
     */
    @Override
    public void clear() {
        stack.clear();
    }

    /**
     * Method to check if the Depth First Frontier is empty
     * or not.
     *
     * @return true if stack is empty
     *         false otherwise
     */
    @Override
    public boolean isEmpty() {
        return stack.empty();
    }

    /**
     * Method to remove a node from the Depth First Frontier.
     * It essentially pops the top node from the stack.
     *
     * @return the top {@link Node} object in the stack
     *         representing the Depth First Frontier.
     */
    @Override
    public Node remove() {
        try {
            return stack.pop();
        }
        catch (EmptyStackException e) {
            return null;
        }
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