import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class SlidingPuzzleBoard extends Board{
    private final int ROW_MAX = 50;
    private final int COL_MAX = 50;
    private final int ROW_MIN = 2;
    private final int COL_MIN = 2;
    private int[][] solvedState;
    private int emptyRow;
    private int emptyCol;
    private int displayOffset;
    
    public SlidingPuzzleBoard(int rows, int cols) {
        super(rows, cols);
        if (!validSize(rows, cols)) {
            throw new IllegalArgumentException("Rows and columns must be between " + ROW_MIN + " and " + ROW_MAX);
        }
        this.solvedState = new int[rows][cols];
        makeSolvedState();
        resetBoard();
        shuffle();
        this.displayOffset = calculateDigits(rows*cols-1);
    }

    protected boolean validSize(int rows, int cols) {
        return (rows >= ROW_MIN && cols >= COL_MIN && rows <= ROW_MAX && cols <= COL_MAX);
    }

    public void makeSolvedState() {
        int tile = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                solvedState[r][c] = tile;
                tile++;
            }
        }
        solvedState[rows - 1][cols - 1] = 0;   // empty space
    }

    public void resetBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = solvedState[r][c];
            }
        }
        emptyRow = rows - 1;
        emptyCol = cols - 1;
    }

    public void shuffle() {
        resetBoard();
        int shuffles = rows * cols * rows * cols;

        Random random = new Random();

        // Find all valid adjacent tiles
        for (int i = 0; i < shuffles; i++) {
            List<int[]> adjacent_tiles = new ArrayList<>();

            if (emptyRow > 0) { // Up
                int[] adj = {emptyRow-1, emptyCol};
                adjacent_tiles.add(adj);
            }
            if (emptyRow < rows - 1) { // Down
                int[] adj = {emptyRow+1, emptyCol};
                adjacent_tiles.add(adj);
            }
            if (emptyCol > 0) { // Left
                int[] adj = {emptyRow, emptyCol-1};
                adjacent_tiles.add(adj);
            }
            if (emptyCol < cols - 1) { // Right
                int[] adj = {emptyRow, emptyCol+1};
                adjacent_tiles.add(adj);
            }

            // Randomly pick adjacent tile
            int[] random_pick = adjacent_tiles.get(random.nextInt(adjacent_tiles.size()));

            // Swap with empty tile
            grid[emptyRow][emptyCol] = grid[random_pick[0]][random_pick[1]];
            grid[random_pick[0]][random_pick[1]] = 0;

            // Set empty tile coords
            emptyRow = random_pick[0];
            emptyCol = random_pick[1];
        }
        if (isSolved()) {
            shuffle();
        }
    }

    public void display() {
        StringBuilder divider = new StringBuilder();
        // Build Divider
        for (int c = 0; c < cols; c++) {
            divider.append("+");
            for (int i = 0; i < displayOffset; i++) {
                divider.append("-");
            }
        }
        divider.append("+");

        for (int r = 0; r < rows; r++) {
            System.out.println(divider);
            StringBuilder ln = new StringBuilder();
            for (int c = 0; c < cols; c++) {
                int tile = grid[r][c];
                int offset = displayOffset - calculateDigits(tile);
                StringBuilder empty = new StringBuilder();
                for (int d = 0; d < offset; d++) {
                    empty.append(" ");
                }
                ln.append(String.format("|%s%d", empty, tile));
            }
            ln.append("|");
            System.out.println(ln);
        }
        System.out.println(divider);
    }

    public boolean isSolved() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != solvedState[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean slideTile(int tile) {
        if (tile < 1 || tile > rows * cols - 1) {   // Check if tile is valid
            return false;
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) { // Find the empty space
                    // Check adjacent tiles
                    if (r > 0 && grid[r - 1][c] == tile) { // Up
                        grid[r][c] = tile;
                        grid[r - 1][c] = 0;
                        return true;
                    }
                    if (r < rows - 1 && grid[r + 1][c] == tile) { // Down
                        grid[r][c] = tile;
                        grid[r + 1][c] = 0;
                        return true;
                    }
                    if (c > 0 && grid[r][c - 1] == tile) { // Left
                        grid[r][c] = tile;
                        grid[r][c - 1] = 0;
                        return true;
                    }
                    if (c < cols - 1 && grid[r][c + 1] == tile) { // Right
                        grid[r][c] = tile;
                        grid[r][c + 1] = 0;
                        return true;
                    }
                    return false; // Tile not adjacent to empty space
                }
            }
        }
        return false;
    }

    private static int calculateDigits(int num) {
        if (num == 0) {
            return 1;
        }
        return (int) (Math.log10(num) + 1);
    }
}
