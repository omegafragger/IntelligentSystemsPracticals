package mars;

/**
 * This class abstracts the position of the robot on the crater.
 * Essentially, it contains two variables (x,y). These x and y
 * values are indices on the 2-d array contained in the {@link Planet}
 * class.
 */
public class Position {

    private int row;
    private int column;

    /**
     * Constructor to initialize the row and column of the {@link Position}
     * object.
     *
     * @param r
     *         row of the position
     *
     * @param c
     *         column of the position
     */
    Position(int r, int c) {
        this.row = r;
        this.column = c;
    }

    /**
     * Getter method to get the row of this object.
     *
     * @return row of the object
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Getter method to get the column of this object.
     *
     * @return column value of the object
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Setter method to set the row of the object.
     *
     * @param r row value of the object to be set.
     */
    public void setRow(int r) {
        this.row = r;
    }

    /**
     * Setter method to set the column value of the object.
     *
     * @param c column value of the object to be set.
     */
    public void setColumn(int c) {
        this.column = c;
    }

    /**
     * Overriding the equals method of {@link Object}.
     */
    @Override
    public boolean equals(Object position) {
        Position pos = (Position) position;
        return ((this.row == pos.getRow()) && (this.column == pos.getColumn()));
    }

    /**
     * Overriding the hashCode method of {@link Object}.
     */
    @Override
    public int hashCode() {
        return (this.row * this.column);
    }
}