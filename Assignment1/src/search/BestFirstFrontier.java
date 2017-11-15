package search;

import java.util.PriorityQueue;

/**
 * This class implements the frontier and contains specific
 * implementation for the Best First Search algorithm.
 * The frontier in this case is a priority queue where nodes are
 * stored in ascending order of their values.
 */
public class BestFirstFrontier implements Frontier {

    private static final int INITIAL_CAPACITY = 1000;

    private PriorityQueue<Node> priorityQueue;
    private NodeFunction nodeFunction;
    int maxNumberOfNodes;

    /**
     * Constructor to initialise the priority queue.
     */
    public BestFirstFrontier(NodeFunction nodeFunction) {
        priorityQueue = new PriorityQueue<>(INITIAL_CAPACITY, new NodeComparator());
        this.nodeFunction = nodeFunction;
    }

    /**
     * Method to add a new node to the BestFirstFrontier.
     */
    @Override
    public void add(Node newNode) {
        // Compute the node function value for the new node.
        if (newNode != null) {
            newNode.value = nodeFunction.getNodeValue(newNode);
            priorityQueue.offer(newNode);
        }
        if (priorityQueue.size() > maxNumberOfNodes) {
            maxNumberOfNodes = priorityQueue.size();
        }
    }

    /**
     * Method to clear the BestFirstFrontier.
     */
    @Override
    public void clear() {
        priorityQueue.clear();
    }

    /**
     * Method to check if the BestFirstFrontier is empty.
     */
    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    /**
     * Method to remove a node from the BestFirstFrontier.
     */
    @Override
    public Node remove() {
        return priorityQueue.poll();
    }

    /**
     * Method to return the maximum number of nodes contained
     * in the BestFirstFrontier since its inception.
     */
    @Override
    public int maxNodeNum() {
        return maxNumberOfNodes;
    }
}