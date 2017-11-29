package mars;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import search.Node;

/**
 * Class to print the solution obtained from depth first search of 
 * the Mars crater.
 */
public class PrintSolution {

    /**
     * Method to print a single solution instead of enumerating all solutions.
     * 
     * @param solution
     *         solution to be printed
     */
    public void printSingleSol(Node solution) {
        Stack<Node> stack = getStack(solution);
        while(!stack.isEmpty()) {
            Node temp = stack.pop();
            Move move = (Move) temp.action;
            if (move != null) {
                System.out.print(" ---> " + move.name() + " ---> ");
            }
            RobotState robotState = (RobotState) temp.state;
            Position position = robotState.currentPosition;
            System.out.print("(" + position.getRow() + "," + position.getColumn() + ")");
        }
        RobotState robotState = (RobotState) solution.state;
        System.out.println();
        List<Position> traversedPositions = robotState.traversedPosition;
        Set<Position> uniquePositions = new HashSet<>(traversedPositions);
        if (uniquePositions.contains(robotState.currentPosition)) {
            System.out.println("Number of cells covered: " + uniquePositions.size());
        }
        else {
            System.out.println("Number of cells covered: " + (uniquePositions.size() + 1));
        }
        int batteryRemaining = robotState.battery;
        System.out.println("Initial battery life: " + FindLifeWithHigherBattery.INITIAL_BATTERY);
        System.out.println("Battery remaining: " + batteryRemaining);
        System.out.println("Minimum battery required: " + (FindLifeWithHigherBattery.INITIAL_BATTERY - batteryRemaining));
    }

    /**
     * Method to print a given list of solution nodes
     * @param solutionNodes
     *         List of {@link Node} objects containing the solution
     */
    public void printSol(List<Node> solutionNodes) {
        int i = 1;
        for (Node node : solutionNodes) {
            System.out.println("Solution: " + i);
            i++;
            System.out.println();
            Stack<Node> stack = getStack(node);
            while(!stack.isEmpty()) {
                Node temp = stack.pop();
                Move move = (Move) temp.action;
                if (move != null) {
                    System.out.print(" ---> " + move.name() + " ---> ");
                }
                RobotState robotState = (RobotState) temp.state;
                Position position = robotState.currentPosition;
                System.out.print("(" + position.getRow() + "," + position.getColumn() + ")");
            }
            RobotState robotState = (RobotState) node.state;
            System.out.println();
            System.out.println("Total number of cells covered: " + ((robotState.numTraversedPositions) + 1));
        }
    }

    private Stack<Node> getStack(Node solutionNode) {
        Stack<Node> stack = new Stack<>();
        Node temp = solutionNode;
        while(temp.parent != null) {
            stack.push(temp);
            temp = temp.parent;
        }
        stack.push(temp);
        return stack;
    }
}