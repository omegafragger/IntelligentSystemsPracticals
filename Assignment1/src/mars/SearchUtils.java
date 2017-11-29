package mars;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import search.Action;
import search.Frontier;
import search.Node;
import search.State;

/**
 * Class containing depth first search functionality for the Mars problem.
 */
public class SearchUtils {

    /**
     * Method to perform depth first tree search to search for the Mars
     * problem.
     * 
     * @param startNode
     *         Start node from where the algorithm will start searching
     *
     * @param frontier
     *         Typically the frontier should be a depth first frontier.
     *
     * @return a list of {@link Node} objects which have maximum cells covered
     */
    public List<Node> search (Node startNode, Frontier frontier) {
        // Initialising the frontier with the start node
        frontier.add(startNode);

        List<Node> maxCellNodes = new ArrayList<>();
        int maxCellsCovered = 0;
        
        // Loop of the search algorithm
        while(!(frontier.isEmpty())) {
            Node currentNode = frontier.remove();
            RobotState robotState = (RobotState) currentNode.state;
            
            /**List<Position> traversedPosition = robotState.traversedPosition;
            for (Position pos : traversedPosition) {
                System.out.print("(" + pos.getRow() + "," + pos.getColumn() + ")" + " --- > ");
            }
            System.out.println();**/
            
            if (robotState.numTraversedPositions > maxCellsCovered) {
                maxCellNodes.clear();
                maxCellsCovered = robotState.numTraversedPositions;
                maxCellNodes.add(currentNode);
            }
            else if (robotState.numTraversedPositions == maxCellsCovered) {
                maxCellNodes.add(currentNode);
            }

            // Else expand the node and add in the new nodes to the frontier
            State currentState = currentNode.state;
            Set<? extends Action> actions = currentState.getApplicableActions();

            for (Action action : actions) {
                State newState = currentState.getActionResult(action);
                Node newNode = new Node(currentNode, action, newState, currentNode.depth + 1);
                frontier.add(newNode);
            }
        }

        return maxCellNodes;
    }
}
