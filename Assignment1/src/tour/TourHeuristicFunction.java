package tour;

import java.util.HashSet;
import java.util.Set;

import search.Node;
import search.NodeFunction;

/**
 * Class that implements the {@link NodeFunction} interface and implements
 * a heuristic function for the Romania tour problem.
 */
public class TourHeuristicFunction implements NodeFunction {

    private Cities allCities;
    private City targetCity;
    private Set<City> unvisitedCities;

    /**
     * The constructor requires the set of all cities for getting the heuristic
     * function value. It also requires the target city.
     *
     * @param allCities
     *         The {@link Cities} object containing the set of all cties.
     *
     * @param targetCity
     *         The {@link City} object representing the target city to reach.
     */
    public TourHeuristicFunction (Cities allCities, City targetCity) {
        this.allCities = allCities;
        this.targetCity = targetCity;
    }

    /**
     * Method to return the heuristic function value for the Romania tour problem.
     */
    @Override
    public int getNodeValue(Node n) {
        unvisitedCities = new HashSet<>(allCities.getAllCities());
        // Get the state of the given node
        TourState tourState = (TourState) n.state;
        // Get the set of visited cities and the current city
        Set<City> visitedCities = tourState.getVisitedCities();
        City currentCity = tourState.getCurrentCity();

        // Get the set of unvisited cities
        unvisitedCities.removeAll(visitedCities);
        if (unvisitedCities.size() == 0) {
            if (currentCity.equals(targetCity)) {
                return 0;
            }
            else {
                return currentCity.getShortestDistanceTo(targetCity);
            }
        }

        int farthestDistance = Integer.MIN_VALUE;
        City farthestCity = null;

        // Get the farthest unvisited city
        for (City unvisitedCity : unvisitedCities) {
            int distance = currentCity.getShortestDistanceTo(unvisitedCity);
            if (distance > farthestDistance) {
                farthestCity = unvisitedCity;
                farthestDistance = distance;
            }
        }

        return (currentCity.getShortestDistanceTo(farthestCity) +
                farthestCity.getShortestDistanceTo(targetCity));
    }
}