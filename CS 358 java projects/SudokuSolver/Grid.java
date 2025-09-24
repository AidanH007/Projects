import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Grid {
    // Class fields
    private final int size;        // size of grid (4 or 9)
    private final int box;         // size of subgrid (2 or 3)
    private final Cell[][] cells;  // 2D array of cells
    private final Path source;     // optional reference to source file

    // Constructor - initializes empty grid
    public Grid(int size, Path source) {
        this.size = size;
        this.box = (int) Math.sqrt(size);
        if (box * box != size) throw new IllegalArgumentException("Unsupported puzzle size: " + size);

        this.cells = new Cell[size][size];
        this.source = source;

        // Fill all cells with 0 (empty)
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                cells[r][c] = new Cell(0);
    }

    // Load a puzzle from a file
    public static Grid loadFromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        if (lines.isEmpty()) throw new IllegalArgumentException("Empty file");

        // First line defines size
        String sizeLine = lines.get(0).trim().toLowerCase();
        int size;
        if ("9x9".equals(sizeLine)) size = 9;
        else if ("4x4".equals(sizeLine)) size = 4;
        else throw new IllegalArgumentException("First line must be '9x9' or '4x4'");

        Grid grid = new Grid(size, path);

        // Parse each filled cell
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue; // skip empty lines

            String[] parts = line.split(",");
            if (parts.length != 3) continue; // skip malformed lines

            int r = Integer.parseInt(parts[0].trim()) - 1; // 0-based index
            int c = Integer.parseInt(parts[1].trim()) - 1;
            int v = Integer.parseInt(parts[2].trim());

            grid.setInitialValue(r, c, v); // validate and set value
        }
        return grid;
    }

    // ---------------- Accessors ----------------
    public int getSize() { return size; }
    public Cell getCell(int r, int c) { return cells[r][c]; }

    // Set initial cell value
    public void setInitialValue(int r, int c, int val) {
        if (r < 0 || r >= size || c < 0 || c >= size)
            throw new IllegalArgumentException("Row/column out of range");
        if (val < 1 || val > size)
            throw new IllegalArgumentException("Value out of range");
        cells[r][c].setValue(val);
    }

    // Print the grid in human-readable format
    public void print() {
        for (int r = 0; r < size; r++) {
            // Print horizontal separator for subgrids
            if (r % box == 0 && r != 0) {
                for (int i = 0; i < size + box - 1; i++) System.out.print("--");
                System.out.println("-");
            }
            for (int c = 0; c < size; c++) {
                // Print vertical separator for subgrids
                if (c % box == 0 && c != 0) System.out.print("| ");
                // Print value (0 as ".")
                System.out.print((cells[r][c].getValue() == 0 ? ". " : cells[r][c].getValue() + " "));
            }
            System.out.println();
        }
    }

    // ---------------- Solve puzzle using backtracking ----------------
    public boolean solve() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (cells[r][c].getValue() == 0) { // empty cell
                    for (int v = 1; v <= size; v++) { // try all possible values
                        if (isValid(r, c, v)) {       // check if value is valid
                            cells[r][c].setValue(v);  // place value
                            if (solve()) return true; // recurse
                            cells[r][c].setValue(0);  // backtrack
                        }
                    }
                    return false; // no valid value found for this cell
                }
            }
        }
        return true; // puzzle solved
    }

    // Check if placing value in cell is valid
    private boolean isValid(int r, int c, int val) {
        // Check row and column
        for (int i = 0; i < size; i++) {
            if (cells[r][i].getValue() == val || cells[i][c].getValue() == val) return false;
        }
        // Check subgrid/box
        int br = (r / box) * box, bc = (c / box) * box;
        for (int rr = 0; rr < box; rr++)
            for (int cc = 0; cc < box; cc++)
                if (cells[br + rr][bc + cc].getValue() == val) return false;
        return true;
    }
}

