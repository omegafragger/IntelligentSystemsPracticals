package search;

/**
 * This interface provides the contract of the function f(n) for a node
 * which can be used for any general best first search algorithms like
 * best first greedy search or A* search.
 */
public interface NodeFunction {

    /**
     * A method which returns the f(n) value for a given node n.
     * This method can be used for implementing Best first search algorithms.
     *
     * @param n
     *        {@link Node} object whose value is to be computed
     *
     * @return the integer value representing f(n)
     */
    public int getNodeValue(Node n);
}