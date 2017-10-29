package search;

import java.util.Set;

public interface State {
	Set<? extends Action> getApplicableActions();
	State getActionResult(Action action);

    /**
     * Method to check if two states are equal or not.
     *
     * @param s
     *        The state which is to be compared with this
     *        state.
     *
     * @return true if both the states are identical or equal
     *         false otherwise
     */
	@Override
    boolean equals(Object s);

    /**
     * Method to return the hashCode of this {@link State}
     * object.
     * 
     * @return hashcode value of this {@link State} object.
     */
	@Override
    int hashCode();
}
