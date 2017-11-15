package search;

import java.util.Comparator;

/**
 * The purpose of this class is to compare two {@link Node} objects.
 * The class returns the {@link Node} object having lower value of
 * the nodeValue function.
 */
public class NodeComparator implements Comparator<Node> {

    /**
     * Method to comparing two {@link Node} objects. We are trying to
     * prioritise nodes which have lower node value. Hence, if node n1's
     * value is lower than node n2's value, then we say that n1 is greater
     * than n2 and vice versa.
     */
    @Override
    public int compare(Node n1, Node n2) {
        if (n1.value > n2.value) {
            return 1;
        }
        else if (n1.value < n2.value) {
            return -1;
        }
        else {
            return 0;
        }
    }
}