// Cell class represents a single cell in the Sudoku grid
public class Cell {

    // Field to store the value of the cell
    // 0 = empty, 1-size = filled
    private int value;

    // Constructor - initialize cell with a value
    public Cell(int value) {
        this.value = value;
    }

    // Get the current value of the cell
    public int getValue() {
        return value;
    }

    // Set the value of the cell
    public void setValue(int value) {
        this.value = value;
    }
}

