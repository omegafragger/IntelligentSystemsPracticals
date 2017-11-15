package search;

public class Node {
    public final Node parent;
    public final Action action;
    public final State state;
    public final int depth;
    public int value;
    public final int costFromRoot;

    public Node(Node parent, Action action, State state, int depth) {
        this.parent = parent;
        this.action = action;
        this.state = state;
        this.depth = depth;
        if (parent == null) {
            costFromRoot = 0;
        }
        else {
            costFromRoot = parent.costFromRoot + action.cost(parent, this);
        }
    }
}