package tour;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class City {
	protected final String name;
	protected final Set<Road> outgoingRoads;
	protected final Map<City,Integer> shortestDistanceByCity;

	public City(String name) {
		this.name = name;
		this.outgoingRoads = new LinkedHashSet<>();
		this.shortestDistanceByCity = new LinkedHashMap<>();
       	}
	public String getName() {
		return name;
	}
	public Set<Road> getOutgoingRoads() {
		return outgoingRoads;
	}
	public int getShortestDistanceTo(City city) {
		Integer distance = shortestDistanceByCity.get(city);
		if (distance == null)
			return Integer.MAX_VALUE;
		else
			return distance.intValue();
	}

    /**
     * Overriding the equals method of {@link Object} class to check
     * for equality between two cities. Since we are solving with the
     * cities in the AIMA book, it is sufficient to check  if the names
     * of the two cities are equal to check for equality.
     */
    @Override
    public boolean equals(Object city) {
        City otherCity = (City) city;
        if (!(name.equals(otherCity.getName()))) {
            return false;
        }
        return true;
    }

    /**
     * Overriding the hashCode method of {@link Object} class.
     * It returns the hashCode of the "name" string.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
