package search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The class contains a simple utility method to search for a solution
 * of a problem using any search algorithm, i.e. either graph search or
 * tree search. The choice of graph or tree is parameterised.
 */
public class SearchUtilities {

    public static final String GOAL_NODE = "GoalNode";
    public static final String NUMBER_OF_EXPLORED_NODES = "NumExploredNodes";
    public static final String MAX_NUM_NODES_IN_FRONTIER = "MaxFrontierNodes";
    private static final int MAX_DEPTH_LIMIT = 100000;

    /**
     * The set of visited states to be used for graph search algorithms.
     */
    Set<State> visitedStates;

    /**
     * Method to perform either graph search or tree search to search for
     * the solution of a problem and return it if one exists. The method
     * takes the parameter isGraphSearch to know if it has to perform graph
     * search. If the value is not true, then it performs tree search. The
     * method also takes the frontier as a parameter which contains information
     * on whether it has to perform breadth first or depth first search.
     *
     * @param startNode
     *         Start node from where the algorithm will start searching
     *
     * @param goalTest
     *         Test to check if the current node under consideration contains
     *         a goal state
     *
     * @param frontier
     *         Frontier to be used for the search problem.
     *
     * @param isGraphSearch
     *         This parameter if set to true, causes graph search to run. If
     *         set to false, this causes tree search to be executed.
     *
     * @return a map where the first entry corresponds to the goal Node or null
     *         and the second entry contains the number of nodes explored.
     */
    public Map<String, Object> search (Node startNode, GoalTest goalTest, Frontier frontier, boolean isGraphSearch) {
        // Initialising the frontier with the start node
        frontier.add(startNode);
        int numNodesExplored = 0;
        if (isGraphSearch) {
            visitedStates = new HashSet<>();
        }

        // Loop of the search algorithm
        while(!(frontier.isEmpty())) {
            Node currentNode = frontier.remove();
            
            // If we are performing graph search we don't expand a visited state.
            if (isGraphSearch && visitedStates.contains(currentNode.state)) {
                continue;
            }

            numNodesExplored++;

            if (isGraphSearch) {
                visitedStates.add(currentNode.state);
            }

            // If the node has a goal state, return the node as solution
            if (goalTest.isGoal(currentNode.state)) {
                return getResultMap(currentNode, numNodesExplored);
            }
            // Else expand the node and add in the new nodes to the frontier
            else {
                State currentState = currentNode.state;
                Set<? extends Action> actions = currentState.getApplicableActions();
                for (Action action : actions) {
                    State newState = currentState.getActionResult(action);
                    Node newNode = new Node(currentNode, action, newState, currentNode.depth + 1);
                    frontier.add(newNode);
                }
            }
        }

        // The entire search tree has been searched and no solution was found.
        return getResultMap(null, numNodesExplored);
    }

    /**
     * Method to perform the depth limited search algorithm given the start node,
     * goal test and the depth limit.
     *
     * @param startNode
     *         Node from which the algorithm begins its search
     *
     * @param goalTest
     *         Test to check if the current node under consideration contains a
     *         goal state.
     *
     * @param depthLimit
     *         Limit of the depth till which the search will be performed.
     *         The search will include all nodes from depth 0 (root node or start node)
     *         till depth = depthLimit. Any node whose depth is greater than depth limit
     *         will not be considered by the algorithm.
     *
     * @return a map where the first entry corresponds to the goal Node or null
     *         and the second entry contains the number of nodes explored.
     */
    private Map<String, Object> depthLimitedSearch (Node startNode, GoalTest goalTest, int depthLimit) {
        Frontier frontier = new DepthFirstFrontier();
        if (startNode.depth != 0) {
            startNode = new Node(null, null, startNode.state, 0);
        }
        frontier.add(startNode);
        int numNodesExplored = 0;
        
        // Loop of the search algorithm
        while(!(frontier.isEmpty())) {
            Node currentNode = frontier.remove();

            // We do not explore or process nodes whose depth is greater than the depthLimit
            if (currentNode.depth > depthLimit) {
                continue;
            }

            numNodesExplored++;
            // If the node has a goal state, return the node as solution
            if (goalTest.isGoal(currentNode.state)) {
                Map<String, Object> resultMap = getResultMap(currentNode, numNodesExplored);
                resultMap.put(MAX_NUM_NODES_IN_FRONTIER, frontier.maxNodeNum());
                return resultMap;
            }

            // Else expand the node and add in the new nodes to the frontier
            else {
                State currentState = currentNode.state;
                Set<? extends Action> actions = currentState.getApplicableActions();
                for (Action action : actions) {
                    State newState = currentState.getActionResult(action);
                    Node newNode = new Node(currentNode, action, newState, currentNode.depth + 1);
                    frontier.add(newNode);
                }
            }
        }
        
        // The entire search tree has been searched and no solution was found.
        Map<String, Object> resultMap = getResultMap(null, numNodesExplored);
        resultMap.put(MAX_NUM_NODES_IN_FRONTIER, frontier.maxNodeNum());
        return resultMap;
    }

    /**
     * Method to perform the iterative deepening depth limited search given the start node
     * and the goal test.
     *
     * @param startNode
     *         Node from which the algorithm begins its search
     *
     * @param goalTest
     *         Test to check if the current node under consideration contains a
     *         goal state.
     *
     * @return a map where the first entry corresponds to the goal Node or null
     *         and the second entry contains the number of nodes explored.
     */
    public Map<String, Object> iterativeDeepeningDLS (Node startNode, GoalTest goalTest, int depthLimitIncrement) {
        // We are choosing to set the initial depth limit to 3 for all cases here.
        int depthLimit = 3;

        int numNodesExplored = 0;
        int maxNumNodesInFrontier = 0;
        boolean goalNodeFound = false;
 
        while(!goalNodeFound) {
            Map<String, Object> resultMap = depthLimitedSearch(startNode, goalTest, depthLimit);
            numNodesExplored += (Integer) resultMap.get(NUMBER_OF_EXPLORED_NODES);
            int maxFrontier = (Integer) resultMap.get(MAX_NUM_NODES_IN_FRONTIER);
            maxNumNodesInFrontier = (maxNumNodesInFrontier > maxFrontier) ? maxNumNodesInFrontier : maxFrontier;
            Node goalNode = (Node) resultMap.get(GOAL_NODE);

            if (goalNode != null) {
                goalNodeFound = true;
                Map<String, Object> resultMapFinal = getResultMap(goalNode, numNodesExplored);
                resultMapFinal.put(MAX_NUM_NODES_IN_FRONTIER, maxNumNodesInFrontier);
                return resultMapFinal;
                
            }
            else {
                depthLimit += depthLimitIncrement;
                if (depthLimit > MAX_DEPTH_LIMIT) {
                    break;
                }
            }
        }
        Map<String, Object> resultMapFinal = getResultMap(null, numNodesExplored);
        resultMapFinal.put(MAX_NUM_NODES_IN_FRONTIER, maxNumNodesInFrontier);
        return resultMapFinal;
    }

    /**
     * Utility method to create a map whose first entry is the goal node and
     * the second entry is the number of nodes explored.
     *
     * @param goalNode
     *         {@link Node} object representing the goal node
     *
     * @param numNodesExplored
     *         number of nodes explored by the search algorithm
     *
     * @return a map whose first entry is the goal node and the second entry is
     *         the number of nodes explored
     */
    private Map<String, Object> getResultMap(Node goalNode, int numNodesExplored) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(GOAL_NODE, goalNode);
        resultMap.put(NUMBER_OF_EXPLORED_NODES, numNodesExplored);
        return resultMap;
    }
}