import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SudokuSolver {

    public static void main(String[] args) {
        // Create an instance of SudokuSolver and run the program
        new SudokuSolver().run();
    }

    private void run() {
        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);

        /*Set the directory you are using for the solver and create a solvable 4x4 grid or 9x9 grid. 
        Look at the files used for this program as an example. I would generate solvable grids to make life easier on yourself.*/

        // Set base directory for puzzle files
        Path baseDir = Paths.get("C:", "Users", "aidan", "Documents", "CS 358 java projects", "SudokuSolver", "puzzles");
        if (!Files.exists(baseDir)) {
            System.out.println("Directory does not exist: " + baseDir.toAbsolutePath());
            scanner.close();
            return;
        }
        System.out.println("Using base directory: " + baseDir.toAbsolutePath());

        // Prompt user for a valid puzzle file
        Path puzzlePath = getPuzzleFileFromUser(scanner, baseDir);

        // Load the puzzle from file and validate its contents
        Grid grid = loadGridWithFeedback(puzzlePath);

        // Print the puzzle as it was loaded
        System.out.println("\nInput puzzle:");
        grid.print();

        // Solve the puzzle using backtracking
        boolean solved = grid.solve();

        // Print the solved puzzle or unsolvable message
        if (solved) {
            System.out.println("\nSolved puzzle:");
            grid.print();
        } else {
            System.out.println("\nPuzzle is not solvable.");
        }

        // Write the solution (or unsolvable message) to a .sol file
        Path solPath = baseDir.resolve(puzzlePath.getFileName().toString().replaceFirst("\\.[^.]+$", "") + ".sol");
        writeOutputFile(solPath, grid, solved);

        // Close the scanner to free resources
        scanner.close();
    }

    // ---------------- Helper Methods ----------------

    // Prompt user until a valid file is entered
    private Path getPuzzleFileFromUser(Scanner scanner, Path baseDir) {
        while (true) {
            System.out.print("Enter puzzle filename (e.g., puzzle1.txt): ");
            String fileName = scanner.nextLine().trim();
            Path path = baseDir.resolve(fileName);

            // Check if file exists
            if (!Files.exists(path)) {
                System.out.println("File not found: " + path.toAbsolutePath());
                continue; // Ask again
            }
            return path; // Valid file found
        }
    }

    // Load puzzle and handle errors
    private Grid loadGridWithFeedback(Path path) {
        try {
            return Grid.loadFromFile(path); // Calls Grid class to parse file
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error reading/parsing file: " + e.getMessage());
            System.exit(1); // Stop program if puzzle is invalid
        }
        return null; // never reached
    }

    // Write output to .sol file
    private void writeOutputFile(Path outPath, Grid grid, boolean solved) {
        try (BufferedWriter writer = Files.newBufferedWriter(outPath)) {
            if (solved) {
                // Write size on first line
                writer.write(grid.getSize() + "x" + grid.getSize());
                writer.newLine();

                // Write the grid values row by row
                for (int r = 0; r < grid.getSize(); r++) {
                    for (int c = 0; c < grid.getSize(); c++) {
                        writer.write(grid.getCell(r, c).getValue() + ((c < grid.getSize() - 1) ? "," : ""));
                    }
                    writer.newLine();
                }
            } else {
                // Puzzle not solvable
                writer.write("Puzzle is not solvable.");
                writer.newLine();
            }
            System.out.println("Output written to: " + outPath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to write solution file: " + e.getMessage());
        }
    }
}

