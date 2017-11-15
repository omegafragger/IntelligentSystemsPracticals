package tour;

import search.Action;
import search.Node;

public class Road implements Action {
    public final City sourceCity;
    public final City targetCity;
    public final int length;

    public Road(City sourceCity, City targetCity, int length) {
        this.sourceCity = sourceCity;
        this.targetCity = targetCity;
        this.length = length;
    }

    public int cost() {
        return length;
    }

    /**
     * Method to return the cost of moving from one node
     * to another. Each node has a state and in a tour state there is
     * a current city. Now we can just return the cost as the shortest
     * distance between the current cities of the two nodes.
     */
    @Override
    public int cost(Node n1, Node n2) {
        TourState state1 = (TourState) n1.state;
        TourState state2 = (TourState) n2.state;
        City currentCity1 = state1.currentCity;
        City currentCity2 = state2.currentCity;
        return currentCity1.getShortestDistanceTo(currentCity2);
    }
}
