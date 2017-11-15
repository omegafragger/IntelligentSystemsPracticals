package npuzzle;

import search.Node;
import search.NodeFunction;

/**
 * Class that implements the {@link NodeFunction} interface and implements
 * a heuristic function for the npuzzle problem.
 */
public class MisplacedTilesHeuristicFunction implements NodeFunction {

    /**
     * Method to get the heuristic function for the npuzzle problem
     */
    @Override
    public int getNodeValue(Node n) {
        Tiles tiles = (Tiles) n.state;
        int misplacedHeuristicValue = 0;
        int lastTileIndex = tiles.width * tiles.width - 1;
        for (int index = lastTileIndex - 1; index >=0; --index)
            if (tiles.tiles[index] != index + 1)
                misplacedHeuristicValue++;
        if (tiles.tiles[lastTileIndex] != 0) {
            misplacedHeuristicValue++;
        }
        return misplacedHeuristicValue;
    }
}