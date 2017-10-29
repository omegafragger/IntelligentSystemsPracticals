package tour;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import search.Action;
import search.State;

public class TourState implements State {
    protected final Set<City> visitedCities;
    protected final City currentCity;

    public TourState(City startCity) {
        this.visitedCities = Collections.emptySet();
        this.currentCity = startCity;
    }

    public TourState(Set<City> visitedCities, City currentCity) {
        this.visitedCities = visitedCities;
        this.currentCity = currentCity;
    }

    public Set<Road> getApplicableActions() {
        return currentCity.outgoingRoads;
    }

    public State getActionResult(Action action) {
        Road road = (Road) action;
        Set<City> newVisitedCities = new LinkedHashSet<City>(visitedCities);
        newVisitedCities.add(road.targetCity);
        return new TourState(newVisitedCities, road.targetCity);
    }

    /**
     * Getter method to return the set of visited cities for this
     * {@link TourState} object.
     *
     * @return a {@link Set} of visited cities
     */
    public Set<City> getVisitedCities() {
        return visitedCities;
    }

    /**
     * Getter method to return the current city for this {@link TourState}
     * object.
     *
     * @return a {@link City} which represents the current city for this tour
     *         state.
     */
    public City getCurrentCity() {
        return currentCity;
    }

    /**
     * Method to check if two states in the route finding algorithm are
     * identical. This method checks if the set of already visited cities and
     * the current city are the same for the two objects. Note: If the passed
     * {@link State} object is not a {@link TourState} object, then this method
     * throws a {@link ClassCastException}.
     *
     * @param tourState
     *            A {@link TourState} object representing a state in the tour
     *            finding algorithm. It has a set of visited cities and a
     *            current city.
     *
     * @return true if the configuration for both objects is the same false
     *         otherwise
     */
    @Override
    public boolean equals(Object tourState) {
        TourState otherTourState = (TourState) tourState;
        if (!(visitedCities.equals(otherTourState.getVisitedCities()))) {
            return false;
        }
        if (!(currentCity.equals(otherTourState.getCurrentCity()))) {
            return false;
        }
        return true;
    }

    /**
     * Method to return the hashCode of this object. This method just adds the
     * individual hashCodes of its data members.
     *
     * @return hashCode value for this {@link TourState} object.
     */
    @Override
    public int hashCode() {
        return visitedCities.hashCode() + currentCity.hashCode();
    }
}
